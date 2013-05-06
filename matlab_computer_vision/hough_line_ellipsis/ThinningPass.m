% implementation of a single pass over the image 
function [im, changed] = ThinningPass(im, pass) 
    
    chk = ones(size(im)); % temporary matrix for tracking changes 
    changed = false; % initially assume no changes are going to happen 
    
    for i=2:size(im,1)-1 
        for j=2:size(im,2)-1 
            if im(i,j) == 1 
                patch = im(i-1:i+1,j-1:j+1); % patch under consideration 
                c = sum(sum(patch)) - 1; % number of non-zero neighbors 
                X = CalculateCrossOvers(patch); % no: of 1-0 or 0-1 crossoversX 
                doupdate = SatisfyPass(patch, pass); % does this satisfy the pass reqt 
               
                if ((c > 1) && (X == 2) && doupdate) 
                    chk(i,j) = 0; % we need to remove this 1 pixel 
                    changed = true; % we changed something. Need to repeat pass 
                end 
            end % for if 
        end % for j 
    end %for i 
    im = im & chk; % update the image with the change 
end 
