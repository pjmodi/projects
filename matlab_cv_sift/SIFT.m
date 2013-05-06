%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Assignment 3
% Detecting objects in an image using SIFT
%
% Reference:
% David G. Lowe, "Distinctive Image Features from Sacle-Invariant Keypoints",
% accepted for publication in the International Journal of Computer
% Vision, 2004.
%-------------------------------------------------------------------------

function abc()

close all;
clear all;
clc;


noOfOctaves = 4;
s = 2;

I = imread('cir.jpg');
I = im2double(I);
I = rgb2gray(I);
% I = scale(I);


% STEP 1 - DIFFERENCE OF GAUSSIANS
% This step requires that we take an image, blurred at different gaussian
% levels and find the difference between adjacent blurred Gaussian images.
% A set of  blurs on the same scale form an octave.
% We repeat this step for octaves of the same image at different scales.

% Pre-processing Step
gaussianFilter = fspecial('gaussian', [3, 3], 0.5);
blurredI = imfilter(I, gaussianFilter, 'replicate', 'conv');
I = doubleImage(I);

keypointsFromOctaves = buildOctaves(I, s, noOfOctaves);
keypoints = keypointsFromOctaves(1).keypoints;
keypoints =  padarray(keypoints, [1 1], 'post');

for i = 2 : noOfOctaves
    scaledPoints = mapKeypoints(keypointsFromOctaves(i).keypoints, i-1);
    keypoints = keypoints + scaledPoints;
end

figure;
imshow(I);
plotKeypoints(keypoints, 2);



function octave = buildOctaves(I, s, noOfOctaves)
k = 2^(1 / s);
sigma = 1.6;
scalesInOctave = s + 3;

for oct = 1 : noOfOctaves
    
    octave(oct).image = I;
    for i = 1 : scalesInOctave
        
        sigma_g = (k^i) * sigma;
        gaussianFilter = fspecial('gaussian', [3, 3], sigma_g);
        
        if (i==1)
            octave(oct).slice(:, :, i) = scale(imfilter(I, gaussianFilter));
        else
            octave(oct).slice(:, :, i) = scale(imfilter(octave(oct).slice(:, :, i-1), gaussianFilter));
            octave(oct).dog(:, :, i-1) = scale(octave(oct).slice(:, :, i) - octave(oct).slice(:, :, i-1));

%             figure;
%             imshow(octave(oct).dog(:, :, i-1));
        end
    end

    [m, n] = size(I);
    scaleKeyPoints = zeros(m, n);

    for i = 2 : scalesInOctave - 2  %i is the ith DOG image
        for row = 2 : m-1
            for col = 2 : n-1
                M = [];
                a = i - 1;
                b = i + 1;

                M = (octave(oct).dog(row-1:row+1, col-1:col+1, a:b));
                MaxM = max(max(max(M)));
                MinM = min(min(min(M)));

                if (( MaxM == M(2, 2, 2) || MinM == M(2, 2, 2) ) && MaxM ~= MinM)
                    scaleKeyPoints(row, col) = scaleKeyPoints(row, col) + 1;
                end
            end
        end
    end
 
    octave(oct).keypoints = filterKeypoints(scaleKeyPoints, scalesInOctave-1, octave(oct).dog, octave(oct).image);
    figure;
    imshow(octave(oct).image);
    plotKeypoints(octave(oct).keypoints, 1);
    size(find(octave(oct).keypoints))
    
    if(oct < 4)
        I = reduceImage(octave(oct).slice(:, :, 1));
    end
end







function out = filterKeypoints(scaleKeyPoints, noOfDOGs, dog, I)
[m,n] = size(I);
out = zeros(size(scaleKeyPoints));

for i = 2 : noOfDOGs - 1  %i is the ith DOG image
    for row = 2 : m-1
        for col = 2 : n-1

            if (scaleKeyPoints(row, col) ~= 1)
                continue;
            end

            r=row; c=col; d=i;

            for iter=1:5
                if((r<=1 || c<=1) || d<=1)
                    break;
                end
                if((r>=m-1 || c>=n-1) || d>=noOfDOGs)
                    break;
                end

                sp = dog(r-1:r+1, c-1:c+1, d-1:d+1);
                [H] = scale(hessian(sp));

                % If the matrix is not invertible delx set to zero
                if( rcond(H) < 1e-10 )
                    delx = 0;
                else
                    delx = -inv(H)*derivative(sp);
                end

                if(round(norm(delx)) == 0)
                    Dmag = dog(r,c,d) + 0.5 * delx' * derivative(sp);
                    curvature_ratio = getEdgeResponse(sp);

                    if((Dmag > 0.3) & (curvature_ratio < 10))
                        out(r, c) = 1;
                        break;
                    end
                else
                    r = r + round(delx(1));
                    c = c + round(delx(2));
                    d = d + round(delx(3));
                end
            end
        end
    end
end


% This funcion plots keypoints on the original image
function plotKeypoints(scaleKeyPoints, thresh)
hold on;
[xind,yind] = find(scaleKeyPoints >= thresh);
plot(xind, yind, 'r+');



% This function doubles the size of the given image by linear interpolation
function out =  doubleImage(I)
[m, n] = size(I);
[X, Y] = meshgrid( 1: 0.5 : n, 1: 0.5: m );
out = interp2(I, X, Y, '*linear');



% Take the 2nd row and column for the next scale
function out = reduceImage(I)
[m, n] = size(I);
out = I(1:2:m, 1:2:n);



% Here SP is 3x3x3 matrix of candidate keyPoints
function out = hessian(sp)
Dxx = dd1(sp(2,:,2));
Dxy = dd2(sp(:,:,2));
Dyx = Dxy;
Dxd = dd2([sp(2,:,1); sp(2,:,2); sp(2,:,3)]);
Ddx = Dxd;
Dyy = dd1(sp(:,2,2));
Dyd = dd2([sp(:,2,1)  sp(:,2,2)  sp(:,2,3)]);
Ddy = Dyd;
Ddd = dd1([sp(2,2,1) sp(2,2,2) sp(2,2,3)]);
out = [Dxx Dxy Dxd; Dyx Dyy Dyd; Ddx Ddy Ddd];



function out = getEdgeResponse(sp)
Dxx = dd1(sp(2,:,2));
Dyy = dd1(sp(:,2,2));
Dxy = dd2(sp(:,:,2));
traceH = Dxx + Dyy;
detH = (Dxx * Dyy) - (Dxy * Dxy);
if (detH ~= 0)
    ratio = traceH^2 / detH;
else
    ratio = 0;
end
out = ratio;



function out = derivative(sp)
Dy = (sp(3,2,2) - sp(1,2,2)) / 2;
Dx = (sp(2,3,2) - sp(2,1,2)) / 2;
Dd = (sp(2,2,3) - sp(2,2,1)) / 2;
out = [Dx; Dy; Dd];



% Function for single derivative
function out = dd1(f)
i = 2;
out = ( f(i+1) - f(i-1) ) / 2;



% Function for double derivative
function out = dd2(f)
i = 2;
j = 2;
out = ( f(i+1,j-1) + f(i-1,j+1) - f(i-1,j-1) - f(i+1,j+1) ) / 4;


function out = mapKeypoints(D, factor)

for f = 1 : factor
    [m,n] = size(D);
    out = [];
    
    for i=1:m
        A = [];
        for j=1:n
            cells = padarray(D(i,j), [1,1], 'replicate', 'post');
            A = horzcat(A, cells);
        end
        out = vertcat(out, A);
    end
    
    D = out;
end