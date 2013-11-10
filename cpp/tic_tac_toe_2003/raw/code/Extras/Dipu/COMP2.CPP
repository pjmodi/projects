#include<iostream.h>
#include<conio.h>
#include<stdlib.h>

//-----------------------
//Variable Declarations |
//-----------------------
int count;
int ip=0;
int readip=0;
int check=0;
char t[11]="0123456789";	//TiC Tac Toe Main Board
char b[11]="0000000000"; 	//Buffer
char *ptr=b;			//Pointer to Buffer
char c[2]=" ";
char ic[2]=" ";			//Input String
char *conv;                     //Pointer to Input String
char ch1,ch2;


void init(void)  //For re-initializing the variables
{
extern int count;
extern int ip; ip=0;
extern int readip;readip=0;
extern int check;check=0;
extern char t[11]; t[0]='0'; t[1]='1'; t[2]='2'; t[3]='3'; t[4]='4'; t[5]='5'; t[6]='6'; t[7]='7'; t[8]='8'; t[9]='9';
extern char b[11]; b[0]=' '; b[1]=' '; b[2]=' '; b[3]=' '; b[4]=' '; b[5]=' '; b[6]=' '; b[7]=' '; b[8]=' '; b[9]=' ';
extern char *conv;
extern char *ptr; ptr=b;
}

//*************************************************************************

//----------------------
//Function Definitions  |
//----------------------
void convert(void)
{
conv=c;
ip=atoi(conv);
}

void get(void)
{
cout << "Please enter a number between 1 & 9 - ";
c[0]=getche();		//Allows only one character input
getch();                //Waits for player to confirm continuation

if (c[0]<'1' || c[0]>'9')	//Makes sure i/p is a number between 1-9
	{
	cout << endl;
	get();
	}

for (int i=1; i<=10; i++)       //Dis-allows duplicate entries
	{
	if (c[0]==b[i])
		{
		cout << "\nThis number has already been used. ";
		get();
		}
	}
}

void block(void)
{
if( (t[1]==ch1 && t[2]==ch1) && (t[3]=='3') )
     {ip=3;check=1;}
else if( (t[1]==ch1 && t[3]==ch1) && (t[2]=='2') )
     {ip=2;check=1;}
else if( (t[2]==ch1 && t[3]==ch1) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[4]==ch1 && t[5]==ch1) && (t[6]=='6') )
     {ip=6;check=1;}
else if( (t[4]==ch1 && t[6]==ch1) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch1 && t[6]==ch1) && (t[4]=='4') )
     {ip=4;check=1;}
else if( (t[7]==ch1 && t[8]==ch1) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[7]==ch1 && t[9]==ch1) && (t[8]=='8') )
     {ip=8;check=1;}
else if( (t[8]==ch1 && t[9]==ch1) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[1]==ch1 && t[4]==ch1) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[1]==ch1 && t[7]==ch1) && (t[4]=='4') )
     {ip=4;check=1;}
else if( (t[4]==ch1 && t[7]==ch1) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[2]==ch1 && t[5]==ch1) && (t[8]=='8') )
     {ip=8;check=1;}
else if( (t[2]==ch1 && t[8]==ch1) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch1 && t[8]==ch1) && (t[2]=='2') )
     {ip=2;check=1;}
else if( (t[3]==ch1 && t[6]==ch1) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[6]==ch1 && t[9]==ch1) && (t[3]=='3') )
     {ip=3;check=1;}
else if( (t[3]==ch1 && t[9]==ch1) && (t[6]=='6') )
     {ip=6;check=1;}
else if( (t[1]==ch1 && t[5]==ch1) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[1]==ch1 && t[9]==ch1) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch1 && t[9]==ch1) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[3]==ch1 && t[5]==ch1) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[3]==ch1 && t[7]==ch1) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch1 && t[7]==ch1) && (t[3]=='3') )
     {ip=3;check=1;}


else if( (t[1]==ch2 && t[2]==ch2) && (t[3]=='3') )
     {ip=3;check=1;}
else if( (t[1]==ch2 && t[3]==ch2) && (t[2]=='2') )
     {ip=2;check=1;}
else if( (t[2]==ch2 && t[3]==ch2) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[4]==ch2 && t[5]==ch2) && (t[6]=='6') )
     {ip=6;check=1;}
else if( (t[4]==ch2 && t[6]==ch2) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch2 && t[6]==ch2) && (t[4]=='4') )
     {ip=4;check=1;}
else if( (t[7]==ch2 && t[8]==ch2) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[7]==ch2 && t[9]==ch2) && (t[8]=='8') )
     {ip=8;check=1;}
else if( (t[8]==ch2 && t[9]==ch2) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[1]==ch2 && t[4]==ch2) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[1]==ch2 && t[7]==ch2) && (t[4]=='4') )
     {ip=4;check=1;}
else if( (t[4]==ch2 && t[7]==ch2) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[2]==ch2 && t[5]==ch2) && (t[8]=='8') )
     {ip=8;check=1;}
else if( (t[2]==ch2 && t[8]==ch2) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch2 && t[8]==ch2) && (t[2]=='2') )
     {ip=2;check=1;}
else if( (t[3]==ch2 && t[6]==ch2) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[6]==ch2 && t[9]==ch2) && (t[3]=='3') )
     {ip=3;check=1;}
else if( (t[3]==ch2 && t[9]==ch2) && (t[6]=='6') )
     {ip=6;check=1;}
else if( (t[1]==ch2 && t[5]==ch2) && (t[9]=='9') )
     {ip=9;check=1;}
else if( (t[1]==ch2 && t[9]==ch2) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch2 && t[9]==ch2) && (t[1]=='1') )
     {ip=1;check=1;}
else if( (t[3]==ch2 && t[5]==ch2) && (t[7]=='7') )
     {ip=7;check=1;}
else if( (t[3]==ch2 && t[7]==ch2) && (t[5]=='5') )
     {ip=5;check=1;}
else if( (t[5]==ch2 && t[7]==ch2) && (t[3]=='3') )
     {ip=3;check=1;}
}

/* Get the computer's move */
void get_computer_move(void)
{
check=0;
block();
if(check==0)
	{
	if (count==1)
	ip=2;
	else if(count==3)
		{
		if(t[1]=='O' || t[3]=='O' || t[5]=='O' || t[8]=='O')
		  ip=9;
		else if(t[4]=='O' || t[6]=='O')
		ip=5;
		else if(t[7]=='O')
		ip=1;
		else if(t[9]=='O')
		ip=3;
		}
	else if(count==5)
		{
		switch(readip)
			{
			case 7: if(t[1]=='O')
				  ip=5;
				else if(t[3]=='O')
				  ip=5;
				else if(t[5]=='O')
				  ip=3;
				else if(t[8]=='O')
				  ip=3;
				else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			case 8: if(t[4]=='O' || t[6]=='O')
				  ip=3;
				else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			case 3: if(t[7]=='O')
				  ip=5;
				else if(t[1]=='O')
				  ip=8;
				else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			case 1: ip=5;
				break;
			case 4: if(t[8]=='O')
					  ip=3;
				       else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			case 5: if(t[8]=='O')
					  ip=3;
				       else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			case 6: if(t[8]=='O')
				  ip=1;
				else do
					{
					ip=random(10);
					}while(ip==0);
				break;
			default:do
				{
				ip=random(10);
				}while(ip==0);
				break;
			}
		}
	else if (count==7)
		{
		do
			{
			ip=random(10);
			}
		while(ip==0);
		}
	else if(count==9)
		{
		for (int i=1;i<=9;i++)
			{
			itoa(i,ic,10);
			if(t[i]==ic[0])
				{
				ip=i;
				break;
				}
			}
		}
	}

itoa(ip,c,10);

for (int i=1; i<=10; i++)       //Dis-allows duplicate entries
     {
	if (c[0]==b[i])
		{
		get_computer_move();
		}
     }
}


void test(void)		//Checks for a winner or continues the game
{
//X's winning conditions
if (
	(t[1] == 'X' && t[2] == 'X' && t[3] == 'X') ||
	(t[4] == 'X' && t[5] == 'X' && t[6] == 'X') ||
	(t[7] == 'X' && t[8] == 'X' && t[9] == 'X') ||
	(t[1] == 'X' && t[4] == 'X' && t[7] == 'X') ||
	(t[2] == 'X' && t[5] == 'X' && t[8] == 'X') ||
	(t[3] == 'X' && t[6] == 'X' && t[9] == 'X') ||
	(t[1] == 'X' && t[5] == 'X' && t[9] == 'X') ||
	(t[3] == 'X' && t[5] == 'X' && t[7] == 'X')
   )
	{cout << "\n\nPlayer 1 WINS!!";}

//O's winning conditions
else if (
	(t[1] == 'O' && t[2] == 'O' && t[3] == 'O') ||
	(t[4] == 'O' && t[5] == 'O' && t[6] == 'O') ||
	(t[7] == 'O' && t[8] == 'O' && t[9] == 'O') ||
	(t[1] == 'O' && t[4] == 'O' && t[7] == 'O') ||
	(t[2] == 'O' && t[5] == 'O' && t[8] == 'O') ||
	(t[3] == 'O' && t[6] == 'O' && t[9] == 'O') ||
	(t[1] == 'O' && t[5] == 'O' && t[9] == 'O') ||
	(t[3] == 'O' && t[5] == 'O' && t[7] == 'O')
    )
	{cout << "\n\nPlayer 2 WINS!!";}

//Draw conditions
else if (count == 10)
		{
		cout << "\n\nIt's a Draw! Please try again.";
		}

//For alternate turns of the 2 players
else    {
	if (count%2 != 0)
		{
		cout << "\n\nPlayer 2's turn. ";
		ch1='X';
		get_computer_move();
		t[ip]='X';
		}
	else
		{
		cout << "\n\nPlayer 1's turn. ";
		ch2='O';
		get();
		convert();
		t[ip]='O';
		readip=ip;
		}
	}
}

void show(void)		//Clears screen & throws output
{
	clrscr();
	cout << "\n\n"
	     << "\t\t\t|\t\t|" << endl
	     << "\t\t" << t[1] << "\t|\t" << t[2] << "\t|\t" << t[3] << endl
	     << "\t\t\t|\t\t|" << endl
	     << "            -----------------------------------------\n"
	     << "\t\t\t|\t\t|" << endl
	     << "\t\t" << t[4] << "\t|\t" << t[5] << "\t|\t" << t[6] << endl
	     << "\t\t\t|\t\t|" << endl
	     << "            -----------------------------------------\n"
	     << "\t\t\t|\t\t|" << endl
	     << "\t\t" << t[7] << "\t|\t" << t[8] << "\t|\t" << t[9] << endl
	     << "\t\t\t|\t\t|\n\n";
		*ptr=c[0];
		ptr++;
}


//*************************************************************************

//-------------------------
//Main program starts here |
//-------------------------
void main()
{
	for (count=1; count<=10; count++)
	{
		show();
		test();
	}

cout << "\n\nPress 'Y' to play again or any other key to exit.";
char getc=getch();
if (getc=='Y' || getc=='y')
	{
	init();		//re-initializes the varialbles
	main();		//re-calls the main program
	}
}

//*************************************************************************