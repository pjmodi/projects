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
		0xffff,0xbffd,0xdffb,0xeff7,
		0xf7ef,0xfbdf,0xfdbf,0xfe7f,
		0xfe7f,0xfdbf,0xfbdf,0xf7ef,
		0xeff7,0xdffb,0xbffd,0xffff,

		/*mouse pointer bitmap*/
		0x0000,0xC003,0x6006,0x300C,
		0x1818,0x0C30,0x0660,0x03C0,
		0x03C0,0x0660,0x0C30,0x1818,
		0x300C,0x6006,0xC003,0x0000,
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
