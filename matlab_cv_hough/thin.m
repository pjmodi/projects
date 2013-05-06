% Computer vision - Assignment 1 
% Implementation of the thinning algorithm 
% input : the image (after edge detection and thresholding 
% output: the thinned image. 
% Author: Anoop cherian (cher0179 at umn dot edu) 

function X = thin(im) 

    doagain = true; 
    while doagain == true 
        doagain = false; 
        for pass=1:4 % repeat for north, south, east and west passes 
            [im, changed] = ThinningPass(im, pass); 
            
            if changed == true 
            doagain = true; 
            end 
        end 
    end

    X = im; 
    
end