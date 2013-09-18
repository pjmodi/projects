//----------------------
//Function Definitions  |
//----------------------
//
//eval()


void eval()
{
ttt game;						//Creates object ‘game’ of class ‘ttt’
	//Repeat the block below 10 times, count declared for the same
	for (int count=1; count<=10; count++)
	{
		cleardevice();				//Clears Screen	
		gotoxy(3,2);	cout << "  ** QUICK GAME **";	
		
		//Show output
		gotoxy(60,1);	cout << "X = Player 1";		
		gotoxy(60,2);	cout << "O = Computer";

		//Calls function show() of object game by passing value of menu_choice			
		game.show();

		//Assigns return value of game.test() to ‘flag’
		int flag=game.test(count);

		//If flag=1 it means that the game is over so exit for-loop
		if (flag==1)
		break;
	}

}






