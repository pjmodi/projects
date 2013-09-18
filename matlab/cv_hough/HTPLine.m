% ThetaSteps should be between 0.1 and 1
function accumulator = HTPLine(thinnedImg, thetaSteps)

    % Find the dimensions of the image
    [height, width] = size(thinnedImg);
      
    % The maximum value of the rho can be the diagonal of the image
    noOfRhos = floor( sqrt(height^2+width^2) );
    
    % The maximum value of theta will be 180 deg. divided by the steps
    % we would like to take for better accuracy                 
    noOfThetas = 180 / thetaSteps;      % in degrees
    
    % Creating the accumulator array based on the bounds calculated
    acc = zeros(noOfRhos, noOfThetas);
    
    %  Voting - Calculating every rho, theta for every pixel turned on and 
    %  making a corresponding note in the bin (accumulator array).
    for y=1:width
        for x=1:height
            if(thinnedImg(x,y)==1)
                for t=1:noOfThetas      % for ever theta, calculate r
                    tInRadians = (t * thetaSteps * pi) / 180;
                    r = round( x*cos(tInRadians) + y*sin(tInRadians) );
                    
                    % If r is within the bounds of the image then increment
                    % the corresponding bin
                    if(r>0 && r<noOfRhos)
                        acc(r, t) = acc(r, t) + 1; 
                    end
                end
            end
        end
    end
    
    % return accumulator
    accumulator = acc;
    
end