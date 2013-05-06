//-------------------------
//Preprocessor Directives |
//-------------------------
#include<iostream.h>			//Required for cout, cin, etc.
#include<conio.h>			//Required for getch(), etc.
#include<stdlib.h>			//Required for atoi(), etc.
#include<graphics.h>			//Required for graphics mode and all related functions
#include<dos.h>				//Required for mouse initialization
	
//-----------------------
//Global Variables	   |
//-----------------------
union REGS i,o;                         //Used by mouse functions
struct SREGS s;                         //Used by changecusor() function
int gd=DETECT,gm,button,x,y;		//Used for graphics and mouse initialization

//The following 2 arrays hold what the 2 cursors look like.
int cursorX[32]={						//cursor 1: X

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

int cursor0[32]={						//cursor 2: 0

		//screen mask
		0xfc3f,0xf42f,0xe7e7,0xcff3,
		0x9ff9,0xffff,0x3ffc,0x3ffc,
		0x3ffc,0x3ffc,0xffff,0x9ff9,
		0xcff3,0xe7e7,0xf42f,0xfc3f,

		//mouse pointer bitmap
		0x03c0,0x0bd0,0x1818,0x300c,
		0x6006,0x0000,0xc003,0xc003,
		0xc003,0xc003,0x0000,0x6006,
		0x300c,0x1818,0x0bd0,0x03c0,
		};

class ttt
{
//-----------------------
//Variable Declarations |
//-----------------------

private:
 int ip;					//Move Input
 char t[11];					//Tic Tac Toe Main Board
 int b[10];					//Buffer of all inputs
 int move_no;					//Records move number
 
//-----------------------
//Function Declarations |
//-----------------------
public:
 ttt(void);                             	//Constructor for initializing data members
 void get(void);                        	//Gets Player's move
 void show(void);                       	//Displays the board
 int test(int);              			//Checks for a winner or continues game
 void easy(void);                       	//Computer picks a random number
 void get_computer_move(void);      		//Gets Computer's move
};

//-----------------------
//File Includes		    |
//-----------------------
#include"AI.CPP"		//Holds Computer AI related functions
#include"MOUSE.CPP"		//Holds functions for working of the mouse
#include"TESTSHOW.CPP"		//Holds functions for playing, winning & the gameboard. etc.
#include"GETCONST.CPP"		//Holds the constructor and the get() function
#include"EVAL.CPP"	  	//Holds eval() that evaluates input by user in the main menu.
#include"BANNER.CPP"		//Holds the function for showing the banner 

//--------------------------
//Main program starts here |
//--------------------------
void main()
{
 initgraph(&gd,&gm,"d:\\tc\\bgi");				//Graphics drivers initialized

 //If Mouse drivers NOT initialized enter block
 if(initmouse()==0)				
	{	
	closegraph();						//Close graphics mode
	cout << " ERROR: Unable to initialize mouse drivers.";
	exit(1);						//Exit from the game
	}
 
 banner();  							//Introduction Banner

 char getc='0';						
 do
 {
	eval();         			
	//Makes the user select Y if he wants to play again
	cout << "\n\n Press 'Y' to play again or any other key to exit.";
	getc=getche();						//Gets user’s choice
  }
 while (getc=='Y' || getc=='y');		//Repeats do-while loop if true else continues

 lastpage();
 closegraph();							//Closes graphics mode
 restorecrtmode();						//Restores CRT mode
}



 
  
 