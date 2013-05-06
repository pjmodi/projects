function accumulator = HTEllipse(inputimage, direction)

    %image size
    [rows, columns]=size(inputimage);
    
    %accumulator
    maxA = floor(columns / 2);
    maxB = floor(rows / 2);
    acc = zeros(maxA, maxB, rows, columns);
    
    % Get list of all pixels in thinned image that need to be considered
    [yIndex xIndex] = find(inputimage);
    %image
    for b=1:maxB
        for a=b:maxA
            for pt=1:length(xIndex)
                    tanphi = -1 / tan(direction(yIndex(pt), xIndex(pt)));
                    X = sqrt(1 + (b^2 / (a^2 * tanphi^2) ));
                    Y = sqrt(1 + ((a^2 * tanphi^2) / b^2) );

                    x01 = round(xIndex(pt) + (a / X));
                    x02 = round(xIndex(pt) - (a / X));
                    y01 = round(yIndex(pt) + (b / Y));
                    y02 = round(yIndex(pt) - (b / Y));                 
                    
                    if isreal(x01) && isreal(y01)
                        if ( (x01>0 && x01<columns)  &&  (y01>0 && y01<rows) )
                           acc(a, b, y01, x01) = acc(a, b, y01, x01) + 1;
                        end
                    end
                    
                    if isreal(x02) && isreal(y01)
                        if ( (x02>0 && x02<columns) && (y01>0 && y01<rows) )
                           acc(a, b, y01, x02) = acc(a, b, y01, x02) + 1; 
                        end
                    end
                    
                    if isreal(x01) && isreal(y02)
                        if ( (x01>0 && x01<columns) && (y02>0 && y02<rows) )
                           acc(a, b, y02, x01) = acc(a, b, y02, x01) + 1;
                        end
                    end
                    if isreal(x02) && isreal(y02)
                        if ((x02>0 && x02<columns) && (y02>0 && y02<rows))
                           acc(a, b, y02, x02) = acc(a, b, y02, x02) + 1; 
                        end
                    end
            end   
        end
    end
    
    accumulator = acc;
    
end