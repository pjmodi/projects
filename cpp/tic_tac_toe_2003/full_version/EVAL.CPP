//-----------------------
//Function Definitions  |
//-----------------------
//
//eval()   


void eval(char menu_choice)
{

if (menu_choice=='0')					//If user selects “0: Exit” from menu
exit(0);						//Exits from the game
	

else if (menu_choice=='5')				//If user selects “5: Settings” from menu
settings();						//call the settings() function


//If user selects “1 OR 3: Quick Game” from menu enter block
else if (menu_choice=='1' || menu_choice=='3')	
   {
    ttt game;						//Creates object ‘game’ of class ‘ttt’
	
	//Repeat the block below 10 times, count declared for the same
	for (int count=1; count<=10; count++) 		
	{
		cleardevice();				//Clears Screen					
		gotoxy(3,2);	cout << "  ** QUICK GAME **";

		//If user has selected a 1 Player game
		if (menu_choice=='1')			
			{						//Show output
			gotoxy(60,1);	cout << "X = Player 1";
			gotoxy(60,2);	cout << "O = Computer";
			}
		//Else if user has selected a 2 Player game
		else				
			{						//Show output
			gotoxy(60,1);	cout << "X = Player 1";
			gotoxy(60,2);	cout << "O = Player 2";
			}

		
		//Calls function show() of object game by passing value of menu_choice
		game.show(menu_choice);		
		
		//Assigns return value of game.test() to ‘flag’
		int flag=game.test(count, menu_choice, 0);	
		
		//If flag=1 it means that the game is over so exit for-loop
		if (flag==1)					
		break;
	}

   }


//If user selects “2 OR 4: Tournament” from menu enter block
else if (menu_choice=='2' || menu_choice=='4')	
   {
	char tour[2]="00";				//Variable for taking valid tournament no
	char *tour_ptr=tour;				//Pointer to string tour[]
	do
	{
		cleardevice();				//Clears screen
		gotoxy(2,3);
		tour[1]='a';				//Assign invalid value to variable
		cout << "How many games would you like to have in the tournament?"
		     << "\n Minimum 1, Maximum 9 : ";
		tour[1]=getche();		//Get single character and assign to ‘tour[1]’
	}
	while(tour[1]<'1'||tour[1]>'9');	//If invalid choice repeat do-while loop
	
	//Convert string ‘tour[2]’ & assign to int ‘no_of_games’
	int no_of_games=atoi(tour_ptr);   		
	
	//Variables for storing tournament wins / draws
	extern int p1wins, p2wins, draw;			
	p1wins=0; p2wins=0; draw=0;			//Values when tournament begins

	//Repeat block below for ‘no_of_games’
	for (int game_no=1; game_no<=no_of_games; game_no++)	
	{
		ttt game;				//Creates object ‘game’ of class ‘ttt’

		//Repeat the block below 10 times, count declared for the same
		for (int count=1; count<=10; count++) 	
		{
		cleardevice();				//Clears screen
		gotoxy(1,2);	cout << "  ** TOURNAMENT GAME "<<game_no<<" OF "<<no_of_games<<" **";

		//If user has selected a 1 Player game
		if (menu_choice=='2')				
			{						//Show output
			gotoxy(60,1);	cout << "X = Player 1";
			gotoxy(60,2);	cout << "O = Computer";
			}
		// Else if user has selected a 2 Player game
		else				
			{						//Show output
			gotoxy(60,1);	cout << "X = Player 1";
			gotoxy(60,2);	cout << "O = Player 2";
			}

		//Calls function show() of object game by passing value of menu_choice
		game.show(menu_choice);	

		//Assigns return value of game.test() to ‘flag’	
		int flag=game.test(count, menu_choice, game_no);

		//If flag=1 it means that the game is over so exit for-loop	
		if (flag==1)					
		break;
		}

		//If not the last game in tournament enter block
		if (game_no != no_of_games)			
		{						
		cout << "\n\n Press any key to continue with game " << game_no+1;

		//Wait for the user to hit a key before playing next game
		getch();		
		}
	}
	cout << "\n\n Press any key to see the Final Tournament Scorecard";

	//Wait for the user to hit a key before showing scorecard
	getch();			

	//calls score() and passes value ‘menu_choice’ & ‘no_of_games’
	score(menu_choice, no_of_games);
   }
}

