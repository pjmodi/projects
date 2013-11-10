#include<graphics.h>
#include<conio.h>
#include<stdlib.h>
void main()
{
int gd=DETECT,gm,maxy,x,style;
char str[40];
struct linesettingstype ls;

initgraph(&gd,&gm,"d:\\tc\\bgi");

maxy=getmaxy();

setcolor(WHITE);
outtextxy(10,20,"Normal Width");

x=20;
for(style=0;style<=4;style++)
	{
	setlinestyle(style,0,1);

	if(style==0)
	   getlinesettings(&ls);

	if(style==4)
	   {
	   setlinestyle(4,15,1);
	   line(x,50,x,maxy-50);
	   }
	else
	   line(x,50,x,maxy-50);

	itoa(style,str,10);
	outtextxy(x,maxy-20,str);
	x=x+50;
	}

outtextxy(375,20,"Thick Width");

x=375;
for(style=0;style<=4;style++)
	{
	setlinestyle(style,0,3);

	if(style==4)
	   {
	   setlinestyle(4,35,3);
	   line(x,50,x,maxy-50);
	   }
	else
	   line(x,50,x,maxy-50);

	itoa(style,str,10);
	outtextxy(x,maxy-20,str);
	x=x+50;
	}

setlinestyle(ls.linestyle,ls.upattern,ls.thickness);

getch();
closegraph();
restorecrtmode();
}
