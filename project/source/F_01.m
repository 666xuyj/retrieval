%{
[x,y,z]=sphere(50);
colormap(winter);
subplot(1,3,1);
surf(x,y,z);
axis equal
subplot(1,3,2);
surf(x,y,z);shading flat;
axis equal
subplot(1,3,3);
surf(x,y,z);shading interp;
axis equal
%}
%{
[x,y]=meshgrid(-5:0.1:5);
z=cos(x).*cos(y).*exp(-sqrt(x.^2+y.^2)/4);
surf(x,y,z);shading interp;
pause
i=find(x<=0 & y<=0);
z1=z;z1(i)=NaN;
surf(x,y,z);shading interp;
%}
%{
x=-50:0.1:50;
y=x-x.^3/factorial(3);
plot(x,y)
%}
%{
t=-pi:0.5:pi;
y=1./(1+exp(-t));
subplot(3,2,1);bar(t,y,'g');
title('条形图');
subplot(3,2,2);stairs(t,y,'b');
title('阶梯图');
subplot(3,2,3);stem(t,y,'k');
title('杆图');
subplot(3,2,4);semilogx(t,y,'r');
title('对数坐标图semilogx');
subplot(3,2,5);semilogy(t,y,'y');
title('对数坐标图semilogy');
subplot(3,2,6);loglog(t,y,'c');
title('对数坐标图loglog');
%}
%{
t=-pi/3:0.1:pi/3;
r=5.*(sin(t).^2)./cos(t);
polar(t,r,'-*')
title('polar({\phi},{\gamma},"-*")');
%}
%{
t=0:pi/100:2*pi;
x=exp(-t./20).*cos(t);
y=exp(-t./20).*sin(t);
z=t;
plot3(x,y,z);
xlabel('X');
ylabel('Y');
zlabel('Z');
grid on
%}

[x,y]=meshgrid(-3:0.1:3);
z=-5./(1+x.^2+y.^2);
mesh(x,y,z);
xlabel('X');
ylabel('Y');
zlabel('Z');
grid on







