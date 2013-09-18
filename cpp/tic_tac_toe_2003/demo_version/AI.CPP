//----------------------
//Function Definitions  |
//----------------------
//
//easy()
//get_computer_move()



void ttt::easy(void)				//For picking a move randomly
{
	do
	{
		ip = random(10);    	     	//Assigns a random number between 0-9 to ‘ip’
	}
	while(ip==0);				//If ‘ip’=0 recall do-while loop
}


/***************************************************************************************/

void ttt::get_computer_move(void)	// Get the computer's move
{
easy();

for (int i=0; i<=9; i++)		
	{
	if (ip==b[i])				//Check if ip=b[i]; if true it is an invalid move
	get_computer_move();			//Thus ask the computer to play again
	}
}

