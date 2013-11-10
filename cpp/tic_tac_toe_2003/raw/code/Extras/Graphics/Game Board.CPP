#include<graphics.h>
#include<stdlib.h>
#include<iostream.h>
#include<conio.h>
void main()
{
int gd=DETECT,gm;

initgraph(&gd,&gm,"d:\\tc\\bgi");

outtextxy(100,0,"Game Board");
rectangle(0,10,639,479);

line(250,50,250,350);

moveto(350,50);
lineto(350,350);

line(150,150,450,150);

moveto(150,250);
lineto(450,250);

getch();
closegraph();
restorecrtmode();
}

