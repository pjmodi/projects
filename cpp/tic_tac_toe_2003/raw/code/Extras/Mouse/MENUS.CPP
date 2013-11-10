#include<dos.h>
#include<graphics.h>
#include<conio.h>
#include<alloc.h>
#include<stdio.h>
#include<stdlib.h>

char *menu[]={"samosa","sambarwada","dahiwada","exit"};
union REGS i,o;

void displaymenu(char **,int,int,int);
void savemenu(char **,char **,int,int,int,int);
getresponse(char **,char**,int,int,int,int);
void highlight(char **,int,int,int,int);
void dehighlight(char **,int,int,int,int);
initmouse();
void showmouseptr();
void hidemouseptr();
void getmousepos(int *,int *,int *);

void main()
{
int gd=DETECT,gm,choice=1,bill=0,width=0,i,count;
char **buffer;

initgraph(&gd,&gm,"d:\\tc\\bgi");

if(initmouse()==0)
	{
	printf("\n unable to initialise mouse");
	exit(1);
	}

count=sizeof(menu)/sizeof(char *);
settextstyle(TRIPLEX_FONT,0,3);
displaymenu(menu,count,100,100);

for(i=0;i<count;i++)
	{
	if(textwidth(menu[i])>width)
	   width=textwidth(menu[i]);
	}

/*(char)buffer=(char)malloc(sizeof(menu));
savemenu(menu,buffer,width,count,100,100);*/

while(choice!=4)
	{
	choice=getresponse(menu,buffer,width,count,100,100);
	gotoxy(50,15);
	printf("you selected %s",menu[choice-1]);
	}

getch();
closegraph();
}

void displaymenu(char **menu,int count,int x1,int y1)
{
int i,h;

h=textheight(menu[0]);

for(i=0;i<count;i++)
   outtextxy(x1,y1+1*(h+5),menu[i]);
}

void savemenu(char **menu,char **buffer,int width,int count,int x1,int y1)
{
int i,x2,yy1,yy2,area,h;

h=textheight(menu[0]);

for(i=0;i<count;i++)
	{
	x2=x1+width;
	yy1=y1+i*(h+5);
	yy2=y1+(i+1)*(h+5);

	area=imagesize(x1,yy1,x2,yy2);
	(char)buffer[i]=(char)malloc(area);
	getimage(x1,yy1,x2,yy2,buffer[i]);
	}
}

getresponse(char **menu, char **buffer,int width,int count,int x1,int y1)
{
int choice=1,prevchoice=0,x,y,x2,y2,button;
int in,i,h;

h=textheight(menu[0]);
y2=y1+count*(h+5);
x2=x1+width;
rectangle(x1-5,y1-5,x2+5,y2+5);

while(1)
	{
	getmousepos(&button,&x,&y);

	if(x>=x1 && x<=x2 && y>=y1 &&y<=y2)
		{
		in=1;

		for(i=1;i<=count;i++)
			{
			if(y<=y1+i*(h+5))
				{
				choice=i;
				break;
				}
			}

		if(prevchoice!=choice)
			{
			hidemouseptr();
			highlight(buffer,choice,h,x1,y1);

			if(prevchoice)
			   dehighlight(buffer,prevchoice,h,x1,y1);

			prevchoice=choice;
			showmouseptr();
			}

		if((button & 1)==1)
			{
			while((button & 1)==1)
			   getmousepos(&button,&x,&y);

			if(x>=x1 && x<=x2 && y>=y1 &&y<=y2)
			   return(choice);
			}
		}
	else
		{
		if(in==1)
			{
			in=0;
			prevchoice=0;
			hidemouseptr();
			dehighlight(buffer,choice,h,x1,y1);
			showmouseptr();
			}
		}
	}
}

void highlight(char **buffer,int ch,int h,int x1,int y1)
{
putimage(x1,y1+(ch-1)*(h+5),buffer[ch-1],NOT_PUT);
}

void dehighlight(char **buffer,int ch,int h,int x1,int y1)
{
putimage(x1,y1+(ch-1)*(h+5),buffer[ch-1],COPY_PUT);
}

initmouse()
{
i.x.ax=0;
int86(0x33,&i,&o);
return(o.x.ax);
}

void showmouseptr()
{
i.x.ax=1;
int86(0x33,&i,&o);
}

void getmousepos(int *button, int *x, int *y)
{
i.x.ax=3;
int86(0x33,&i,&o);
*button=o.x.bx;
*x=o.x.cx;
*y=o.x.dx;
}

void hidemouseptr()
{
i.x.ax=2;
int86(0x33,&i,&o);
}