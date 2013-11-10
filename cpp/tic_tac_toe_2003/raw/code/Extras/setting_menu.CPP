#include<iostream.h>
#include<conio.h>
#include<stdlib.h>
#include<graphics.h>


void main()
{
int gd=DETECT,gm;
initgraph(&gd,&gm,"d:\\tc\\bgi");	//Graphics drivers initialized
cleardevice();

gotoxy(2,2);	cout <<"** MAIN MENU **";
gotoxy(4,4);    cout <<"1 PLAYER";
gotoxy(6,6);	cout <<"1: Quick Game";
gotoxy(6,7);	cout <<"2: Tournament";

cout	<<"\n\n 3: 2 PLAYER - Quick Game"
	<<" 4:          - Tournament"
	<<" 5: Settings"
	<<" 0: Exit the game"
	<<" Please enter an option number from above: ";

getch();
}