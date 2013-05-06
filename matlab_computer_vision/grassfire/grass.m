%-------------------------------------------------------------------------
% Pushkar Modi
% Computer Vision - Extra Credit 2
% Grassfire Transform
%-------------------------------------------------------------------------
function grassfire()

close all;
clear all;
clc;

I = imread('gf2.bmp');
I = rgb2gray(I);
I = scale(I);
I = imadjust(I, [0 1], [1 0]);

[rows, cols] = size(I);

% North Pass
for r = 2:rows
    for c = 2:cols-1

        if (I(r,c) ~= 0)
                        
            nw = I( r-1, c-1);
            n = I( r-1, c);
            ne = I( r-1, c+1);
            w = I( r, c-1);

            minValue = min([nw n ne w]);
            I(r, c) = minValue + 1;
            
        end

    end
end

% South Pass
for r = rows-1:-1:1
    for c = cols-1:-1:1

        if (I(r,c) ~= 0)
                        
            se = I( r+1, c+1);
            s = I( r+1, c);
            sw = I( r+1, c-1);
            e = I( r, c+1);

            minValue = min([se s sw e]);
            I(r, c) = min([I(r,c)  minValue+1]);
            
        end
    end
end

imshow(I,[0 75]);