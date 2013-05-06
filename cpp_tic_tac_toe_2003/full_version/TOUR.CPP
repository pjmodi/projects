//-----------------------
//Function Definitions  |
//-----------------------
//
//wins()
//score()


void wins(int i)
{
extern int p1wins, p2wins, draw;			//Variables for storing tournament wins
if (i==1)						//If Player 1 has won
    p1wins++;						//Increments player 1's no. of wins by 1
else if (i==2)						//If Player 2 or the computer has won
    p2wins++;						//Increments player 2's no. of wins by 1
else if (i==0)						//If the game was a draw
    draw++;						//Increments no. of draws by 1
}


/***************************************************************************************/


void score(char menu_choice, int tour_no)
{
extern int p1wins, p2wins, draw;			//Variables for storing tournament wins
cleardevice();						//Clears screen
gotoxy(1,1);

/*The following code displays a small cartoon of a boy holding a scorecard which displays the number of wins Player 1 & Player 2 had in the tournament*/

cout << endl << endl
     << endl << "\t\t                    \\\\\\///		"
     << endl << "\t\t                   (  oo  )		"
     << endl << "\t\t    +--------o00o----(..)----o00o--------+	"
     << endl << "\t\t    |                                    |	"
     << endl << "\t\t    |     ** TOURNAMENT SCORECARD **     |	"
     << endl << "\t\t    |                                    |	"

     << endl << "\t\t    |  Player 1: "<<p1wins<<"          ";
     if(menu_choice=='2')cout<<"Computer: "; else cout << "Player 2: ";
     cout<<p2wins<<"  |	"

     << endl << "\t\t    |                                    |	"
     << endl << "\t\t    +---------------ooo0-----------------+	"
     << endl << "\t\t                    (  )    0ooo		"
     << endl << "\t\t                     \\ (    (  )	"
     << endl << "\t\t                      \\_)   ) /		"
     << endl << "\t\t                           (_/		"
    << "\n\n The tournament consisted of "<<tour_no<<" game(s) out of which "<<draw<<" game(s) were ties.\n\n";
	
if(p1wins>p2wins)					//If Player 1 beat Player 2 enter block
	{
		cout << " Thus PLAYER 1 beat ";
		if(menu_choice=='2')			//If 1 player game
			cout<<"the COMPUTER, ";
		else 					//Else if 2 Player game
			cout <<"PLAYER 2, ";	
		cout << p1wins << " - " << p2wins << ".\n\n";
	}
		
else if (p2wins>p1wins)					//If Player 2 beat Player 1 enter block	
	{
		if(menu_choice=='2')			//If 1 Player game
			cout<<" Thus the COMPUTER";		
		else 						//Else if 2 Player game
			cout <<" Thus PLAYER 2";
		cout << " beat PLAYER 1, " << p2wins << " - " << p1wins << ".\n\n";
	}

else if (p1wins==p2wins)				//If the tournament was a tie
cout << " Thus the tournament was a draw as both the players won " << p1wins << " game(s).\n\n";
}

 
