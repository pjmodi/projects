%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Assignment 2
% Detecting an ellipse using Hough Transform
%-------------------------------------------------------------------------

clear all;
close all;

% Load a gray scale image (or converting RGB to GRAY Scale if needed)
originalImg = imread('ellipseb.jpg');
I = rgb2gray(originalImg);

% We add 2 rows and 2 columns of padding to the array to allow the 5x5
% Sobel operator to take the border rows and columns into parsing as well
originalImg = padarray(I, [2 2],'replicate');

% Converting the image to double values for better accuracy in mathematical
% processing
I = double (I) / 255;

% Creating kernels for detecting diagonal edges and convolving them over
% the original image
K1 = [-1 -2 0 2 1; -2 -3 0 3 2; -3 -5 0 5 3; -2 -3 0 3 2; -1 -2 0 2 1];
K2 = -K1';
imgK1 = conv2(I, K1);
imgK2 = conv2(I, K2);

% Merging the two outputs to get the gradient magnitude
mag = sqrt(imgK1.^2 + imgK2.^2);

% Calculating magnitude direction
dir = atan2(imgK2, imgK1);

%Scale all values in mag between 0 and 1 to ensure that imshow can display
%them correctly
mag = scale(mag);

% Thresholding Magnitude
binMag = threshold(mag, 100);
figure;
imshow(binMag);

% Thinning based on 8 connectivity of the magnitude
thinnedImg = thin(binMag);
figure;
imshow(thinnedImg);

% Get the Accumulator arrary using Hough Transform
accumulator = HTEllipse(thinnedImg, dir);

% Filtering by Local Maxima and Threshold
accumulatorLocalMaxima = imregionalmax(accumulator);
threshNoOfPoints = 23;

A=[]; B=[]; X0=[]; Y0=[];
[rows, columns]=size(thinnedImg);
maxA = floor(columns / 2);
maxB = floor(rows / 2);

for b=1:maxB
    for a=b:maxA
        for y=1:rows
            for x=1:columns
               if (accumulatorLocalMaxima(a,b,y,x) == 1) && (accumulator(a,b,y,x) > threshNoOfPoints)
                    A = [A;a];
                    B = [B;b];
                    Y0 = [Y0;y];
                    X0 = [X0;x];
               end
            end
        end
    end
end        

% Plotting the detected ellipses on the original image
figure;
imshow(originalImg);
PlotEllipse(A, B, X0, Y0);