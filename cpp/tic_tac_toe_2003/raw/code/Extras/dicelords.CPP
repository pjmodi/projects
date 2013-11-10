#include<graphics.h>
#include<iostream.h>
#include<conio.h>
#include<stdlib.h>
#include<dos.h>

void main()
{
int gd=DETECT,gm;
initgraph(&gd,&gm,"d:\\tc\\bgi");	//Graphics drivers initialized
cleardevice();

settextstyle(11, HORIZ_DIR, 0);
outtextxy(240,230,"Dicelords Interactive");
outtextxy(285,250,"presents");
//delay(2000);

cleardevice();

line(210,149,410,149);			 //Horizontal-Top
line(210,213,410,213);       		 //Horizontal-Bottom
line(273,90,273,275);			 //Vertical-Left
line(342,90,342,275);			 //Vertical-Right

settextstyle(10, HORIZ_DIR, 1);

outtextxy(230,95,"T"); outtextxy(305,95,"I"); outtextxy(370,95,"C");

outtextxy(230,155,"T"); outtextxy(300,155,"A"); outtextxy(370,155,"C");

outtextxy(230,220,"T"); outtextxy(300,220,"O"); outtextxy(370,220,"E");

outtextxy(272,275,"2003");
settextstyle(11, HORIZ_DIR, 1);
outtextxy(272,320,"v1.0 Beta");

getch();
}
