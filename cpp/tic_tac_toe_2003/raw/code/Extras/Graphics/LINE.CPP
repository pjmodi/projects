#include<graphics.h>
#include<stdlib.h>
#include<iostream.h>
#include<conio.h>
void main()
{
int gd=DETECT,gm;
char msg[80];

initgraph(&gd,&gm,"d:\\tc\\bgi");

outtextxy(100,0,"Demonstration");
rectangle(0,10,639,479);

line(100,50,100,350);

moveto(300,50);
lineto(300,350);

moverel(200,-300);
linerel(0,300);

outtextxy(104,50,"(100,50)");
outtextxy(104,350,"(100,350)");
outtextxy(90,375,"Line");

outtextxy(304,50,"(300,50)");
outtextxy(304,350,"(300,350)");
outtextxy(280,375,"Moveto,Lineto");

outtextxy(504,50,"(500,50)");
outtextxy(504,350,"(500,350)");
outtextxy(480,375,"Moverel,Linerel");

getch();
closegraph();
restorecrtmode();
}
