//-----------------------
//Function Definitions  |
//-----------------------
//
//alternate()   -- For alternate games in a tournament
//test()	-- Checks for a winner or continues the game
//show()	-- Clears screen & throws output


void ttt::alternate(int count, char menu_choice, int game_no)
{
	if (game_no%2 != 0)				//For odd games Player 1 plays first
	{
		if (count%2 != 0)			//For odd turns Player 1 plays first
		{
			cout << " Player 1's turn.";
			changecursor(cursorX);		//Make the mouse pointer look like an X
			showmouseptr();			//Show the mouse pointer
			get();				//Get the player’s move
			hidemouseptr();			//Hide the mouse pointer

			//Assign the position selected by the player an ‘X’
			t[ip]='X';					
			if(count==1)			//If it’s the first turn enter block
			 { move=ip;}			//Assign the player’s move to ‘move’
			playtwo=ip;			//Assign the player’s move to  ‘playtwo’
		}

		else					//For even turns Player 2 plays
		{
			//If it’s a 1 Player game
			if (menu_choice=='1' || menu_choice=='2')	
			{
			get_computer_move(count,game_no);	//Get the computer’s move
			}

			//If it’s a 2 Player game	
			else						
			{
			cout << " Player 2's turn.";
			changecursor(cursor0);		//Make the mouse pointer look like an ‘O’
			showmouseptr();			//Show the mouse pointer
			get();				//Get the player’s move
			hidemouseptr();			//Hide the mouse pointer
			}
		
			//Assign the position selected by the player an ‘O’
			t[ip]='O';				
		}
	}

	else						//For even games Player 2 goes first
	{
		if (count%2 != 0)			//For odd turns Player 2 goes first
		{
			//If it’s a 1 Player game
			if (menu_choice=='1' || menu_choice=='2')	
			{
			get_computer_move(count,game_no);	//Get the computer’s move
			}

			//If it’s a 2 Player game
			else							
			{
			cout << " Player 2's turn.";
			changecursor(cursor0);		//Make the mouse pointer look like an ‘O’
			showmouseptr();			//Show the mouse pointer
			get();				//Get the player’s move
			hidemouseptr();			//Hide the mouse pointer
			}

			//Assign the position selected by the player an ‘O’	
			t[ip]='O';					
		}

		else					//For even turns Player 1 goes first
		{
			cout << " Player 1's turn.";
			changecursor(cursorX);		//Make the mouse pointer look like an ‘X’
			showmouseptr();			//Show the mouse pointer
			get();				//Get the player’s move
			hidemouseptr();			//Hide the mouse pointer

			//Assign the position selected by the player an ‘X’
			t[ip]='X';	
			//Assign the player’s move to ‘playone’				
			playone=ip;				
		}
	}
}


/***************************************************************************************/


//Checks for a winner or continues the game
int ttt::test(int count, char menu_choice, int game_no)	
{
gotoxy(1,22);
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
	{
	cout << " PLAYER 1 WINS!!";
	if (menu_choice=='2'||menu_choice=='4')		//If tournament
		wins(1);				//Increment Player 1's victories
	return(1);					//End game
	}


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
	{
	if (menu_choice=='1' || menu_choice=='2')	//If 1 Player game
		cout << " THE COMPUTER WINS!!";
	else						//Else if 2 Player game
		cout << " PLAYER 2 WINS!!";	
	if (menu_choice=='2'||menu_choice=='4')		//If tournament
		wins(2);				//Increment Player 2's victories
	return(1);					//End game
	}

//Draw conditions
else if (count == 10)					//If it’s the 10th turn in the game
	{
	cout << " The game was a Draw!";
	if (menu_choice=='2'||menu_choice=='4')		//If tournament
		wins(0);				//Increment no. of ties
	return(1);					//End game
	}

//If alternate game play is on & if it’s a tournament, enter the block
else if ((alterplay=='5') && (menu_choice=='2'||menu_choice=='4'))  
	{
	alternate(count, menu_choice, game_no);
	return(0);					//Game not over yet
	}


else if (count%2 != 0)
		{
		cout << " Player 1's turn.";
		changecursor(cursorX);			//Make the mouse pointer look like an X
		showmouseptr();				//Show the mouse pointer
		get();					//Get the player’s move
		hidemouseptr();				//Hide the mouse pointer

		//Assign the position selected by the player an ‘X’
		t[ip]='X';						
		if(count==1)				//If it’s the first turn enter block
 		 { move=ip;}				//Assign the player’s move to ‘move’
		playtwo=ip;				//Assign the player’s move to  ‘playtwo’
		return(0);				//Game not over yet
		}


else
	{ 
	if (menu_choice=='1' || menu_choice=='2')	//If it’s a 1 Player game
		{
		get_computer_move(count,1);		//Get the computer’s move
		}
	else						//If it’s a 2 Player game	
		{
		cout << " Player 2's turn.";
		changecursor(cursor0);			//Make the mouse pointer look like an ‘O’
		showmouseptr();				//Show the mouse pointer
		get();					//Get the player’s move
		hidemouseptr();				//Hide the mouse pointer
		}


		//Assign the position selected by the player an ‘O’
		t[ip]='O';						
		return(0);				//Game not over yet
	}
}


/***************************************************************************************/


void ttt::show(char menu_choice)			//Clears screen & throws output
{

if (menu_choice=='1' || menu_choice=='2')		//If 1 Player game
	setfillstyle(9,9);				//Set fill-style to pattern=9 & colour=9

//Else set fill-style pattern=9 & colour=12
else	setfillstyle(9,12);			
bar(0,65,639,300);					//Fill around grid

setfillstyle(1,0);	bar(190,75,430,290);		//Background colour of grid
setfillstyle(1,7);					//Thick White bar
bar(0,50,639,64);     					//Top
bar(0,301,639,315);    					//Bottom

rectangle(190,75,430,290);				//Border around grid
line(210,149,410,149);					//Horizontal-Top
line(210,213,410,213);       				//Horizontal-Bottom
line(273,90,273,275);					//Vertical-Left
line(342,90,342,275);					//Vertical-Right

gotoxy(30,8);	cout << t[1];				//Show position 1 on grid
gotoxy(39,8);	cout << t[2];				//Show position 2 on grid
gotoxy(48,8);	cout << t[3];				//Show position 3 on grid

gotoxy(30,12);	cout << t[4]; 				//Show position 4 on grid
gotoxy(39,12);	cout << t[5]; 				//Show position 5 on grid
gotoxy(48,12);	cout << t[6]; 				//Show position 6 on grid

gotoxy(30,16);	cout << t[7]; 				//Show position 7 on grid
gotoxy(39,16);	cout << t[8]; 				//Show position 8 on grid
gotoxy(48,16);	cout << t[9]; 				//Show position 9 on grid

b[move_no]=ip;						//Buffering input
move_no++;						//Incrementing buffer for next time
}


	