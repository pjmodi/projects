function [flag] = checkConditions(img, p, q)

flag = 0;
conditions = [0 0 0; 0 1 0; 0 0 0];

% padding the original image with row and col containing 0's
img = padarray(img, [1 1]);
p = p + 1;
q = q + 1;

% The 6 kernels
k1 = [0 0 0; 1 1 1; 0 0 0];
k2 = [0 1 0; 0 1 0; 0 1 0];
k3 = [0 0 0; 0 1 0; 1 0 0];
k4 = [0 0 1; 0 1 0; 0 0 0];
k5 = [0 0 0; 0 1 0; 0 0 1];
k6 = [1 0 0; 0 1 0; 0 0 0];

for m=1:3
    for n=1:3
        conditions(m,n) = img(p-2+m, q-2+n);
    end
end

if isequal(conditions, k1) || isequal(conditions, k2) || isequal(conditions, k3) || isequal(conditions, k4) || isequal(conditions, k5) || isequal(conditions, k6) 
    flag = 1;
end