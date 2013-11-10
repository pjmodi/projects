#include<graphics.h>
#include<conio.h>
void main()
{
int gd=DETECT,gm,maxx,maxy,x=600,y=450;
int array[]={350,180,400,80,450,180,350,180};

initgraph(&gd,&gm,"d:\\tc\\bgi");

maxx=getmaxx();
maxy=getmaxy();
rectangle(0,20,maxx,maxy);

setcolor(WHITE);
outtextxy(150,10,"Fill figures using different functions");

ellipse(x/4,10*y/35,0,360,100,50);
outtextxy(x/4-textwidth("Ellipse")/2,10*y/24+10,"Ellipse");
setfillstyle(SOLID_FILL,RED);
fillellipse(x/4,10*y/35,100,50);

drawpoly(4,array);
fillpoly(4,array);
outtextxy(370,200,"Polygon");

circle(280,320,70);
line(190,350,370,350);
moveto(190,350);
linerel(90,-120);
linerel(90,120);
outtextxy(210,410,"User-defined figure");

floodfill(280,320,WHITE);

setfillstyle(SOLID_FILL,BLUE);
floodfill(192,349,WHITE);
floodfill(368,349,WHITE);
floodfill(280,231,WHITE);

setfillstyle(SOLID_FILL,DARKGRAY);
floodfill(235,289,WHITE);
floodfill(330,289,WHITE);
floodfill(280,351,WHITE);

getch();
closegraph();
restorecrtmode();
}

