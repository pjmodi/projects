function output = thin(inputImg)

% clear all;
% close all;
% inputImg = [1 1 0 0; 0 1 1 0; 0 0 1 1; 0 1 0 0];

mask = ones(size(inputImg));
[rows,cols] = size(inputImg);

complete = 0;
noOfParses = 1;
previousScan = inputImg;

while(complete == 0) 
    % North Scan
    for i=1:rows
        for j=1:cols
            if inputImg(i,j) == 1
                noOfNeighbors = countNeighbors(inputImg, i, j);

                % If no. of neighbors is greater than 1
                if (noOfNeighbors > 1)
                    flagMatch = checkConditions(inputImg, i, j);

                    % If no patterns matched & this is not the first row
                    if (i == 1)
                        mask(i,j) = 0;
                    elseif (flagMatch == 0) && (inputImg(i-1,j) ~= 1)
                        mask(i,j) = 0;
                    end
                end
            end
        end
    end

    inputImg = inputImg .* mask;

    % South Scan
    for i=rows:1
        for j=cols:1
            if inputImg(i,j) == 1
                noOfNeighbors = countNeighbors(inputImg, i, j);

                % If no. of neighbors is greater than 1
                if (noOfNeighbors > 1)
                    flagMatch = checkConditions(inputImg, i, j);

                    % If no patterns matched & this is not the first row
                    if (i == rows)
                        mask(i,j) = 0;
                    elseif (flagMatch == 0) && (inputImg(i+1,j) ~= 1)
                        mask(i,j) = 0;
                    end
                end
            end
        end
    end

    inputImg = inputImg .* mask;

    % West Scan
    for j=1:cols
        for i=rows:1
            if inputImg(i,j) == 1
                noOfNeighbors = countNeighbors(inputImg, i, j);

                % If no. of neighbors is greater than 1
                if (noOfNeighbors > 1)
                    flagMatch = checkConditions(inputImg, i, j);

                    % If no patterns matched & this is not the first row
                    if (j == 1)
                        mask(i,j) = 0;
                    elseif (flagMatch == 0) && (inputImg(i,j-1) ~= 1)
                        mask(i,j) = 0;
                    end
                end
            end
        end
    end

    inputImg = inputImg .* mask;

    % East Scan
    for j=cols:1
        for i=1:rows
            if inputImg(i,j) == 1
                noOfNeighbors = countNeighbors(inputImg, i, j);

                % If no. of neighbors is greater than 1
                if (noOfNeighbors > 1)
                    flagMatch = checkConditions(inputImg, i, j);

                    % If no patterns matched & this is not the first row
                    if (j == cols)
                        mask(i,j) = 0;
                    elseif (flagMatch == 0) && (inputImg(i,j+1) ~= 1)
                        mask(i,j) = 0;
                    end
                end
            end
        end
    end

    inputImg = inputImg .* mask;
    
    if isequal(previousScan, inputImg)
        complete = 1;
    else
        previousScan = inputImg;
        noOfParses = noOfParses + 1;
    end
end

output = inputImg;

noOfParses