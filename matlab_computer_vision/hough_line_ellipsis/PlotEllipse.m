% This function plots ellipses for every combination of a, b, x0 and y0
function PlotEllipse(A, B, X0, Y0)
    
    hold on;
    noOfX = 200;
    
    for i=1:length(A)
        for j=1:noOfX
            x(j)= (X0(i)-A(i))+ (2*A(i)*(j-1)) / (noOfX - 1);
            ypos(j)= Y0(i)+ B(i)*sqrt(1-((x(j)-X0(i))/A(i))^2);
            yneg(j)= Y0(i)- B(i)*sqrt(1-((x(j)-X0(i))/A(i))^2);
        end
        
        plot(x, ypos, 'r');
        plot(x, yneg, 'r');
    end
    
end