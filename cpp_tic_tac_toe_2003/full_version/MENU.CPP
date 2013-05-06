//-----------------------
//Function Definitions  |
//-----------------------
//
//menu()
//banner()


void menu(void)						//Main Menu
{
cleardevice();						//Clears Screen
gotoxy(1,2);						//Goes to char 1 on line 2

cout  <<"  ** MAIN MENU **\n"
	<<"\n\n 1: 1 Player - Quick Game"
	<<"\n\n 2: 1 Player - Tournament"
	<<"\n\n\n 3: 2 Player - Quick Game"
	<<"\n\n 4: 2 Player - Tournament"
	<<"\n\n\n 5: Settings"
	<<"\n\n 0: Exit the game"
	<<"\n\n\n Please enter an option number from above: ";

char menu_choice;					//Variable for capturing valid menu input
menu_choice=getche();

if (menu_choice < '0' || menu_choice > '5')		//If input not valid
menu();							//Re-call menu() function

eval(menu_choice);					//else pass the value of menu_choice to eval()
}


/***************************************************************************************/


void banner(void)					//Displays huge TTT banner!
{
cleardevice();						//Clears Screen

//Sets font-type=11,alignment as horizontal and font-size=0
settextstyle(11, 0, 0);	    
outtextxy(240,230,"Dicelords Interactive");	//Goes to co-ordinate (x, y and outputs “text”)
outtextxy(285,250,"presents");			//Goes to co-ordinate (x, y and outputs “text”)

delay(2000);						//Waits for 2 seconds before proceeding

cleardevice();

line(210,149,410,149);			 		//Horizontal-Top
line(210,213,410,213);       		 		//Horizontal-Bottom
line(273,90,273,275);					//Vertical-Left
line(342,90,342,275);			 		//Vertical-Right

settextstyle(10, 0, 1);

delay(500);						//Waits for 0.5 seconds before proceeding
outtextxy(230,95,"T");
outtextxy(300,95,"I");
outtextxy(370,95,"C"); 

delay(500);						//Waits for 0.5 seconds before proceeding
outtextxy(230,155,"T");
outtextxy(300,155,"A");
outtextxy(370,155,"C");

delay(500);						//Waits for 0.5 seconds before proceeding
outtextxy(230,220,"T");
outtextxy(300,220,"O");
outtextxy(370,220,"E");

delay(500);						//Waits for 0.5 seconds before proceeding
outtextxy(272,275,"2003");			//Goes to co-ordinate (x, y and outputs “text”)

//Sets font-type=11,alignment as horizontal and font-size=1
settextstyle(11, 0, 1);	    
outtextxy(272,320,"  v 1.0  ");			//Goes to co-ordinate (x, y and outputs “text”)

delay(3000);						//Waits for 3 seconds before exiting
}

 
