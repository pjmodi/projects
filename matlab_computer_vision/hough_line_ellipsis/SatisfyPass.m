
% this routine checks for a pass and sees if a given pixel satisfies the 
% pass requirement. ie in a north pass, does this pixel satisfy the 
% requirement of removal during a north pass. A pixel will satisfy the 
% north pass removal requirement if it has the following template: 
% x 0 x 
% x 1 x 
% x 1 x 
% similar templates for other passes. 
function doupdate = SatisfyPass(patch, pass) 
    doupdate = false; 
   
    if ((pass == 1) && (patch(1,2) == 0) && (patch(3,2) == 1)) ||... 
    ((pass == 2) && (patch(1,2) == 1) && (patch(3,2) == 0)) ||... 
    ((pass == 3) && (patch(2,1) == 0) && (patch(2,3) == 1)) ||... 
    ((pass == 4) && (patch(2,1) == 1) && (patch(2,3) == 0)) 
        doupdate = true; 
    end 
end 