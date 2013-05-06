%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Assignment 1
%-------------------------------------------------------------------------

% This function accepts an image and returns no. of neighbors a given pixel
% has
function [number] = countNeighbors(img, a, b)

count = 0;
img = padarray(img, [1 1]);

a = a + 1;
b = b + 1;

for m=a-1:a+1
    for n=b-1:b+1
        if img(m,n) == 1
            count = count+1;
        end
    end
end

number = count - 1;