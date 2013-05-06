%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Assignment 2
% Detecting a line using Hough Transform
%-------------------------------------------------------------------------

clear all;
close all;

% Load a gray scale image (or converting RGB to GRAY Scale if needed)
originalImg = imread('s1g_original.png');

% We add 2 rows and 2 columns of padding to the array to allow the 5x5
% Sobel operator to take the border rows and columns into parsing as well
originalImg = padarray(originalImg, [2 2],'replicate');

% Converting the image to double values for better accuracy in mathematical
% processing
I = rgb2gray(originalImg);
I = double (I) / 255;

% Creating kernels for detecting diagonal edges and convolving them over
% the original image
K1 = [-1 -2 0 2 1; -2 -3 0 3 2; -3 -5 0 5 3; -2 -3 0 3 2; -1 -2 0 2 1];
K2 = -K1';
imgK1 = conv2(I, K1);
imgK2 = conv2(I, K2);

% Merging the two outputs to get the gradient magnitude
mag = sqrt(imgK1.^2 + imgK2.^2);

%Scale all values in mag between 0 and 1 to ensure that imshow can display
%them correctly
mag = scale(mag);

% Thresholding Magnitude
binMag = threshold(mag, 10); %100
figure(1);
imshow(binMag);

% Dilate to get rid of dual lines
SE = strel('square', 7);
binMagnew = imdilate(binMag, SE);

% Thinning based on 8 connectivity of the magnitude
thinnedImg = thin(binMagnew);
figure;
imshow(thinnedImg);

% Configurable Variables for Hough Transform
thetaSteps = 0.5;%0.1;
noOfPointsForThreshold = 75;%150;

% Get the Accumulator arrary using Hough Transform
accumulator = HTPLine(thinnedImg, thetaSteps);
figure;
imshow(accumulator/255);

% Find the local maximal values
accumulatorLocalMaxima = imregionalmax(accumulator);
% Get indexes of rho and theta values of lines to be plotted above the threshold
[iRho, iTheta] = find(accumulator > noOfPointsForThreshold);

rho = [];
theta = [];

for i=1:length(iRho)
    % If the thresholded index is a local maximal, we want to retain it
    if(accumulatorLocalMaxima(iRho(i), iTheta(i)) == 1)
        rho = [rho; iRho(i)];
        theta = [theta; iTheta(i)];
    end
end

% Normalizing the highest value to 1
% [rho theta] = find(accumulator/max(max(accumulator)) > .4);

% Mapping from polar to cartesian system for plotting
theta = (theta * thetaSteps * pi) / 180;   % this will give theta in radians
c = rho ./ sin(theta);
m = -cot(theta);

[height, width] = size(thinnedImg);
figure(4);
imshow(originalImg);

% Plotting points for every x, y for every m & c
x = 0:height;
hold on;
for i = 1:length(m)
   y = m(i) * x + c(i);
   plot(y, x, 'r');
end
 
hold off