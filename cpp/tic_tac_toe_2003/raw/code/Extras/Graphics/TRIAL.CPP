#include<graphics.h>
#include<conio.h>
#include<iostream.h>
void main()
{
int gd=DETECT,gm,x,y;
int array[]={540,220,590,270,570,320,510,320,490,270,540,220};

initgraph(&gd,&gm,"d:\\tc\\bgi");

x=getmaxx();
y=getmaxy();

cout <<endl<<"x="<<x;
cout <<endl<<"y="<<y;

outtextxy(x/2-30,y/2,"Center");

getch();
}