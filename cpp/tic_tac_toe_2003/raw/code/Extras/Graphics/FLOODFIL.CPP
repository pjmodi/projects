#include<graphics.h>
#include<conio.h>
void main()
{
int gd=DETECT,gm,maxx,maxy;
char pattern[]={0x00,0x70,0x20,0x27,0x24,0x24,0x07,0x00};

initgraph(&gd,&gm,"d:\\tc\\bgi");

maxx=getmaxx();
maxy=getmaxy();
rectangle(0,10,maxx,maxy);

setcolor(WHITE);
outtextxy(175,0,"color shapes and patterns");

setcolor(WHITE);
rectangle(80,150,180,250);

setfillstyle(SOLID_FILL,RED);
floodfill(81,151,WHITE);
outtextxy(100,300,"floodfill");

setfillstyle(BKSLASH_FILL,RED);
bar(250,150,350,250);
rectangle(250,150,350,250);
outtextxy(250,300,"setfillstyle");
outtextxy(230,320,"(using std. pattern)");

setfillpattern(pattern,RED);
bar(420,150,520,250);
rectangle(420,150,520,250);
outtextxy(420,300,"setfillpattern");
outtextxy(420,320,"(using user-defined)");

getch();
closegraph();
restorecrtmode();
}