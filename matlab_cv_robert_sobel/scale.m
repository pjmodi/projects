
% This function scales the values of an image to between 0 & 1
function output = scale(inputImage)

minValue = min(inputImage);             % returns row as a vector
minValue = min(minValue);               % returns max value from the row
inputImage = inputImage - minValue;     % This will scale the lowest value to 0

maxValue = max(inputImage);             % returns row as a vector
maxValue = max(maxValue);               % returns max value from the row
inputImage = inputImage / maxValue;     % This will scale the highest value to 1

output = inputImage;