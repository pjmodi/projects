#include<dos.h>
#include<math.h>
#include<conio.h>
#include<stdlib.h>
#include<stdio.h>
#include<stdarg.h>
#include<graphics.h>

char *fonts[]={
		"DefaultFont","TripplexFont","SmallFont",
		"SansSerifFont","GothicFont","ScriptFont",
		"SimplexFont","TriplexScriptFont",
		"ComplexFont","EuropeanFont","BoldFont"
	      };

char *linestyles[]={
			"SolidLn","DottedLn","CenterLn",
			"DashedLn","UserBitLn"
		   };

char *fillstyles[]={
			"EmptyFill","SolidFill","LineFill",
			"LtSlashFill","SlashFill","BkSlashFill",
			"LtBkSlashFill","HatchFill","XHatchFill",
			"InterleaveFill","WideDotFill","CloseDotFill"
		   };

char *textdirect[]={
			"HorizDir","VertDir"
		   };

char *horizontal[]={
			"LeftText","CenterText","RightText"
		   };

char *vertical[]={
		    "BottomText","CenterText","TopText"
		 };

struct pts
{
int x,y;
};

int gprintf(int *xloc,int *yloc,char *fmt,...);

void main()
{
struct viewporttype viewinfo;
struct linesettingstype lineinfo;
struct fillsettingstype fillinfo;
struct textsettingstype textinfo;
struct palettetype palette,far *pal;

float aspectratio;
int maxx,maxy,maxcolors,errorcode,xasp,yasp,low,high;

char *driver,*mode;
int x,y,gd=DETECT,gm,i;

initgraph(&gd,&gm,"d:\\tc\\bgi");

rectangle(0,20,639,479);
outtextxy(200,10,"System Metrics");

getviewsettings(&viewinfo);
getlinesettings(&lineinfo);
getfillsettings(&fillinfo);
gettextsettings(&textinfo);
getpalette(&palette);
getaspectratio(&xasp,&yasp);
aspectratio=xasp/yasp;
driver=getdrivername();
mode=getmodename(gm);
getmoderange(gd,&low,&high);
gm=getgraphmode();
maxcolors=getmaxcolor()+1;
pal=getdefaultpalette();

x=20;
y=30;

settextjustify(LEFT_TEXT,TOP_TEXT);

gprintf(&x,&y,"Graphics device :%-20s(%d)",driver,gd);
gprintf(&x,&y,"Graphics mode :%-20s(%d)",mode,gm);
gprintf(&x,&y,"MaxMode Number :%d",getmaxmode());
gprintf(&x,&y,"Mode Range is :%d to %d",low,high);
gprintf(&x,&y,"Screen Resolution :(0,0,%d,%d)",getmaxx(),getmaxy());
gprintf(&x,&y,"Current view port :(%d,%d,%d,%d)",viewinfo.left,viewinfo.top,
					    viewinfo.right,viewinfo.bottom);
gprintf(&x,&y,"Clipping :%s",viewinfo.clip?"ON":"OFF");
gprintf(&x,&y,"Aspect Ratio :%f",aspectratio);
gprintf(&x,&y,"Current position :(%d,%d)",getx(),gety());
gprintf(&x,&y,"Colors Available :%d",maxcolors);
gprintf(&x,&y,"Current color :%d",getcolor());
gprintf(&x,&y,"Current BkColor :%d",getbkcolor());
gprintf(&x,&y,"Line Style :%s",linestyles[lineinfo.linestyle]);
gprintf(&x,&y,"Line Thickness :%d",lineinfo.thickness);
gprintf(&x,&y,"Current fill style :%s",fillstyles[fillinfo.pattern]);
gprintf(&x,&y,"Current fill color :%d",fillinfo.color);
gprintf(&x,&y,"Current Font :%s",fonts[textinfo.font]);
gprintf(&x,&y,"Text direction :%s",textdirect[textinfo.direction]);
gprintf(&x,&y,"Character size :%d",textinfo.charsize);
gprintf(&x,&y,"Horizontal justify :%s",horizontal[textinfo.horiz]);
gprintf(&x,&y,"Vertical justify :%s",vertical[textinfo.vert]);
gprintf(&x,&y,"Palette size :%d",getpalettesize());

for(i=0;i<=15;i++)
       gprintf(&x,&y,"Value of color[%d]=%d",i,pal->colors[i]);

getch();
closegraph();
restorecrtmode();
}

int gprintf(int *x,int *y,char *fmt,...)
{
va_list ptr;
char str[140];

va_start(ptr,fmt);
vsprintf(str,fmt,ptr);
outtextxy(*x,*y,str);
*y=*y+textheight("H")+2;
va_end(ptr);
}

