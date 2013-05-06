%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Assignment 1
% Reference: http://homepages.inf.ed.ac.uk/rbf/HIPR2/roberts.htm
%-------------------------------------------------------------------------

% Implementation of Roberts Operator for cross edge detection
clear all;
close all;

% Load a gray scale image
I = imread('cameraman.jpg');
% I = rgb2gray(I);

% Converting the image to double values for better accuracy in mathematical
% processing
%I = im2double(I); 
I = double (I) / 255;


% Creating kernels for detecting diagonal edges and convolving them over
% the original image
K1 = [0 1; -1 0];
K2 = [1 0; 0 -1];
imgK1 = conv2(I, K1);
imgK2 = conv2(I, K2);

% Merging the two outputs to get the gradient magnitude
mag = sqrt(imgK1.^2 + imgK2.^2);

% Calculating the gradient direction
dir = atan2(imgK2, imgK1) - ((3*pi)/4);


%Scale all values in mag between 0 and 1 to ensure that imshow can display
%them correctly
mag = scale(mag);
figure;
imshow(mag);

%Scale all values in dir between 0 and 1 to ensure that imshow can display
%them correctly
dir = scale(dir);
figure;
imshow(dir);


% Thresholding Magnitude
binMag = threshold(mag, 100);
figure;
imshow(binMag);

% Thresholding Direction
binDir = threshold(dir, 100);
figure;
imshow(binDir);


% Thinning based on 8 connectivity of the magnitude
thinnedImg = thin(binMag);
figure;
imshow(thinnedImg);