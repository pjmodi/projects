#include<graphics.h>
#include<dos.h>
#include<stdio.h>
#include<conio.h>
#include<stdlib.h>

union REGS i,o;
struct SREGS s;

initmouse();
void showmouseptr();
void changecursor(int *);

int cursor[32]={
		/*screen mask*/
		0xfc3f,0xf7ef,0xeff7,0xdffb,
		0xbffd,0xffff,0x7ffe,0x7ffe,
		0x7ffe,0x7ffe,0xffff,0xbffd,
		0xdffb,0xeff7,0xf7ef,0xfc3f,

		/*mouse pointer bitmap*/
		0x03c0,0x0810,0x1008,0x2004,
		0x4002,0x0000,0x8001,0x8001,
		0x8001,0x8001,0x0000,0x4002,
		0x2004,0x1008,0x0810,0x03c0,
	       };

void main()
{
int gd=DETECT,gm;

initgraph(&gd,&gm,"d:\\tc\\bgi");

if(initmouse()==0)
	{
	closegraph();
	printf("Mouse not installed");
	exit(1);
	}

gotoxy(10,1);
printf("Press any key to exit");
changecursor(cursor);
showmouseptr();
getch();
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
