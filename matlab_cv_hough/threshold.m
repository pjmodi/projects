
% This function creates a binary image based on thresholded value of a given image
function output = threshold(inputImg, value)

% Converting the image back to uint8 format for thresholding 
inputImg = im2uint8(inputImg);

% Create an array that holds index of all pixels above the threshold
list = find(inputImg > value);

% Create a binary image of the same size as the original with all pixels
% turned OFF
binaryImg = zeros(size(inputImg));

% Switch ON all pixels which are greater than the threshold
binaryImg(list) = 1;

output = binaryImg;