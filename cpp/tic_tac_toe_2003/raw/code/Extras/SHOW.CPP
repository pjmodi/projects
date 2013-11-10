#include<graphics.h>
#include<stdlib.h>
#include<iostream.h>
#include<conio.h>
#include<dos.h>

union REGS i,o;
struct SREGS s;

int cursorX[32]={	//cursor 1: X

		//screen mask
		0xffff,0x3ffc,0x9ff9,0xcff3,
		0xe7e7,0xf3cf,0xf99f,0xfc3f,
		0xfc3f,0xf99f,0xf3cf,0xe7e7,
		0xcff3,0x9ff9,0x3ffc,0xffff,

		//mouse pointer bitmap
		0x0000,0xc003,0x6006,0x300c,
		0x1818,0x0c30,0x0660,0x03c0,
		0x03c0,0x0660,0x0c30,0x1818,
		0x300c,0x6006,0xc003,0x0000,
		};

#include"MOUSE.CPP"

char t[11]="0123456789";

void main()
{
int gd=DETECT,gm;
initgraph(&gd,&gm,"d:\\tc\\bgi");

initmouse();
changecursor(cursorX);
showmouseptr();


gotoxy(3,2);	cout << "  ** QUICK GAME **";
gotoxy(60,2);	cout << "X = Player 1";
gotoxy(60,3);	cout << "O = Computer";



//setbkcolor(8);

setfillstyle(9,12);	bar(0,65,639,300);	//Fill around grid
setfillstyle(1,0);	bar(190,75,430,290);	//Background colour of playarea
setfillstyle(1,7);	//Thick White bar
bar(0,50,639,64);     //Top
bar(0,301,639,315);     //Bottom


rectangle(190,75,430,290);			//Border around grid


line(210,149,410,149);				//Horizontal-Top
line(210,213,410,213);       			//Horizontal-Bottom
line(273,90,273,275);				//Vertical-Left
line(342,90,342,275);				//Vertical-Right

setfillstyle(1,4);
bar(200,85,266,142);	//1
bar(200,150,266,206);	//4
bar(200,214,266,280);	//7

bar(274,85,335,142);	//2
bar(274,150,335,206);	//5
bar(274,214,335,280);	//8

bar(343,85,420,142);	//3
bar(343,150,420,206);	//6
bar(343,214,420,280);	//9

gotoxy(30,8);	cout << t[1];
gotoxy(39,8);	cout << t[2];
gotoxy(48,8);	cout << t[3];

gotoxy(30,12);	cout << t[4];
gotoxy(39,12);	cout << t[5];
gotoxy(48,12);	cout << t[6];

gotoxy(30,16);	cout << t[7];
gotoxy(39,16);	cout << t[8];
gotoxy(48,16);	cout << t[9];

gotoxy(2,22);
cout << "Player 1's turn.";
cout << "\n Click on the appropriate place.";

getch();
closegraph();
restorecrtmode();
}