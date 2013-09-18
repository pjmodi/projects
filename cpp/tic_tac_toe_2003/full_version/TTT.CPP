	
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
int p1wins=0, p2wins=0, draw=0; 	//Records numbers of wins/draws in a tournament 

char difficulty='2';			//Checks default difficulty level
char alterplay='5';			//Checks alternate game play ON/OFF
int prevgame=0;         		//Stores the no. of the previous game played by the computer


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
 int move;					//Move of Player in single player game
 int move_no;					//Records move number
 int flag;					//Stops the game if someone wins
 int playone, playtwo;                 		//Move of player in odd and even games
 int newgame;                          		//New game
 int select;					//Selects which game should be played in AI

//-----------------------
//Function Declarations |
//-----------------------
public:
 ttt(void);                             	//Constructor for initializing data members
 void get(void);                        	//Gets Player's move
 void show(char);                       	//Displays the board
 int test(int, char, int);              	//Checks for a winner or continues game
 void alternate(int, char, int);        	//For alternate games in a tournament
 void playfirst(int);                  		//When the player has to play first
 void playsecond(int);                  	//When the player has to play second
 void easy(void);                       	//Computer picks a random number
 void expert(int, int);				//Computer plays according to strategy
 void block(char);                      	//Checks for winning possibilities
 void get_computer_move(int, int);      	//Gets Computer's move
};

//-----------------------
//File Includes		    |
//-----------------------
#include"AI.CPP"		//Holds Computer AI related functions
#include"TOUR.CPP"		//Holds functions related to tournament gameplay
#include"MOUSE.CPP"		//Holds functions for working of the mouse
#include"TESTSHOW.CPP"		//Holds functions for playing, winning & the gameboard. etc.
#include"GETCONST.CPP"		//Holds the constructor and the get() function
#include"SETTINGS.CPP"		//Holds the functions related to the settings of the game.
#include"EVAL.CPP"	  	//Holds eval() that evaluates input by user in the main menu.
#include"MENU.CPP"		//Holds the functions for showing the banner & the menu
#include"EXPERT.CPP"		//Holds the functions that select the computer’s moves by strategy.


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
	menu();         			
	//Makes the user select something from the menu
	cout << "\n\n Press 'M' to go back to the menu or any other key to exit.";
	getc=getche();						//Gets user’s choice
  }
 while (getc=='M' || getc=='m');				//Repeats do-while loop if true else continues
 closegraph();							//Closes graphics mode
 restorecrtmode();						//Restores CRT mode
}

