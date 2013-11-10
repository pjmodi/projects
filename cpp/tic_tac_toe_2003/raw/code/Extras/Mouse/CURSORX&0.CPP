#include<graphics.h>
#include<dos.h>
#include<alloc.h>
#include<conio.h>
#include<stdlib.h>
#include<stdio.h>

union REGS i,o;
struct SREGS s;

initmouse();
void showmouseptr();
void hidemouseptr();
void getmousepos(int *,int *,int *);
void disp(int,char *);
void changecursor(int *);


int c[][32]={
		/*cursor 1. X*/
		/*screen mask*/
		0xffff,0x3ffc,0x9ff9,0xcff3,
		0xe7e7,0xf3cf,0xf99f,0xfc3f,
		0xfc3f,0xf99f,0xf3cf,0xe7e7,
		0xcff3,0x9ff9,0x3ffc,0xffff,

		/*mouse pointer bitmap*/
		0x0000,0xc003,0x6006,0x300c,
		0x1818,0x0c30,0x0660,0x03c0,
		0x03c0,0x0660,0x0c30,0x1818,
		0x300c,0x6006,0xc003,0x0000,

		
		/*cursor 2. 0*/
		/*screen mask*/
		0xfc3f,0xf42f,0xe7e7,0xcff3,
		0x9ff9,0xffff,0x3ffc,0x3ffc,
		0x3ffc,0x3ffc,0xffff,0x9ff9,
		0xcff3,0xe7e7,0xf42f,0xfc3f,

		/*mouse pointer bitmap*/
		0x03c0,0x0bd0,0x1818,0x300c,
		0x6006,0x0000,0xc003,0xc003,
		0xc003,0xc003,0x0000,0x6006,
		0x300c,0x1818,0x0bd0,0x03c0,
		
	      };

void main()
{
int gd=DETECT,gm,button,x,y,area,i,choice;
char *p;

initgraph(&gd,&gm,"d:\\tc\\bgi");

if(initmouse()==0)
	{
	closegraph();
	puts("Mouse not installed");
	exit(1);
	}

for(i=0;i<2;i++)
	{
	changecursor(c[i]);
	showmouseptr();
	getmousepos(&button,&x,&y);
	area=imagesize(x-15,y-7,x+32,y+24);
	(char)p=(char)malloc(area);
	getimage(x-15,y-7,x+32,y+24,p);
	putimage(i*48+1,1,p,COPY_PUT);
	rectangle(i*60,0,(i+1)*60,45);
	}

gotoxy(10,25);
printf("click on the rectangles to see the cursors \n \n \npress any key to exit");
choice=1;
disp(choice,p);
changecursor(c[choice-1]);

while(!kbhit())
	{
	getmousepos(&button,&x,&y);

	if((button & 1)==1)
		{
		for(i=0;i<4;i++)
			{
			if(choice-1==i)
			   continue;

			if(y>0 && y<33)
				{
				if(x>i*48 && x<(i+1)*48)
					{
					hidemouseptr();
					disp(choice,p);
					choice=i+1;
					disp(choice,p);
					changecursor(c[choice-1]);
					showmouseptr();
					}
				}

			}
		}
	}

getch();
}

void disp(int choice, char *p)
{
getimage((choice-1)*48+1,1,choice*48,32,p);
putimage((choice-1)*48+1,1,p,NOT_PUT);
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

void changecursor(int *shape)
{
i.x.ax=9;
i.x.bx=0;
i.x.cx=0;
i.x.dx=(unsigned)shape;
segread(&s);
s.es=s.ds;
int86x(0x33,&i,&i,&s);
}