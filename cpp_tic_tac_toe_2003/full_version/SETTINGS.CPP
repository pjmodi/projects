//-----------------------
//Function Definitions  |
//-----------------------
//
//settings()


void settings(void)
{
void menu(void);						//Forward Declaration
cleardevice();							//Clears screen

gotoxy(1,2); 	cout << "  ** LEVEL OF DIFFICULTY **";
gotoxy(50,2);	cout << "Current Status: ";	

     if (difficulty=='1') cout << "EASY";
else if (difficulty=='2') cout << "MEDIUM";
else if (difficulty=='3') cout << "ADVANCED";
else if (difficulty=='4') cout << "EXPERT";

cout	<<"\n\n 1: Easy"
	<<"\n\n 2: Medium"
	<<"\n\n 3: Advanced"
	<<"\n\n 4: Expert";

gotoxy(1,14);	cout <<"  ** ALTERNATE GAME PLAY **";
gotoxy(50,14);	cout << "Current Status: ";
     if (alterplay=='5') cout << "ON";
else if (alterplay=='6') cout << "OFF";

cout	<<"\n\n 5: On"
	<<"\n\n 6: Off"
	<<"\n\n\n\n 0: Save & Exit"
	<<"\n\n\n Please enter an option number from above: ";

char set_choice;				//Variable for capturing valid settings input				
set_choice=getche();				//Gets user’s choice

if (set_choice < '0' || set_choice > '6')	//If input is in-valid
settings();					//Re-call the settings() function

else if (set_choice >= '1' && set_choice <='4')	//If input for changing difficulty levels
	{
	difficulty=set_choice;			//Assign the selection to ‘difficulty’
	settings();				//Re-call the settings() function
	}

else if (set_choice >= '5' && set_choice <='6')	//If input for switching alternate play ON or OFF
	{
	alterplay=set_choice;			//Assign the selection to ‘alterplay’
	settings();				//Re-call the settings() function
	}

else if (set_choice=='0')			//If input is to save & exit
menu();						//Re-call the menu() function
}

