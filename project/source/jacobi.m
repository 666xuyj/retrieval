function [y,n]=jacobi(A,b,x0,ep)
if nargin==3
    ep=1.0e-6;
elseif nargin<3
    error
    return
end
D=diag(diag(A));%求A的对角矩阵
L=-tril(A,-1);%求A的下三角矩阵
U=-triu(A,1);%求A的上三角矩阵
B=D\(L+U);
f=D\b;
y=B*x0+f;
n=1;%迭代次数
while norm(y-x0)>=ep
    x0=y;
    y=B*x0+f;
    n=n+1;
end
end

