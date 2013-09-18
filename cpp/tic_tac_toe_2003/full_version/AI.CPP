//-----------------------
//Function Definitions  |
//-----------------------
//
//easy()
//block()
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


void ttt::block(char check)			//Checks for a blocking or winning condition
{
 if(	(flag==0) && (t[1]==check)	)		//When ‘t[1]’ = X or O enter block
	{
	     if(	(t[2]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[3]==check) && (t[2]==' ')	)	{ip=2; flag=1;}
	else if(	(t[4]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[7]==check) && (t[4]==' ')	)	{ip=4; flag=1;}
	else if(	(t[5]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[9]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[2]==check)	)		//When ‘t[2]’ = X or O
	{
	     if(	(t[1]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[3]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	else if(	(t[5]==check) && (t[8]==' ')	)	{ip=8; flag=1;}
	else if(	(t[8]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[3]==check)	)		//When ‘t[3]’ = X or O
	{
	     if(	(t[1]==check) && (t[2]==' ')	)	{ip=2; flag=1;}
	else if(	(t[2]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	else if(	(t[6]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[9]==check) && (t[6]==' ')	)	{ip=6; flag=1;}
	else if(	(t[5]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[7]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[4]==check)	)		//When ‘t[4]’ = X or O
	{
	     if(	(t[1]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[7]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	else if(	(t[5]==check) && (t[6]==' ')	)	{ip=6; flag=1;}
	else if(	(t[6]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[5]==check)	)		//When ‘t[5]’ = X or O
	{
	     if(	(t[1]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[2]==check) && (t[8]==' ')	)	{ip=8; flag=1;}
	else if(	(t[3]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[4]==check) && (t[6]==' ')	)	{ip=6; flag=1;}
	else if(	(t[6]==check) && (t[4]==' ')	)	{ip=4; flag=1;}
	else if(	(t[7]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[8]==check) && (t[2]==' ')	)	{ip=2; flag=1;}
	else if(	(t[9]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	}

 if(	(flag==0) && (t[6]==check)	)		//When ‘t[6]’ = X or O
	{
	     if(	(t[3]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[9]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[5]==check) && (t[4]==' ')	)	{ip=4; flag=1;}
	else if(	(t[4]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[7]==check)	)		//When ‘t[7]’ = X or O
	{
	     if(	(t[4]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	else if(	(t[1]==check) && (t[4]==' ')	)	{ip=4; flag=1;}
	else if(	(t[8]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[9]==check) && (t[8]==' ')	)	{ip=8; flag=1;}
	else if(	(t[5]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[3]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[8]==check)	)		//When ‘t[8]’ = X or O
	{
	     if(	(t[7]==check) && (t[9]==' ')	)	{ip=9; flag=1;}
	else if(	(t[9]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[5]==check) && (t[2]==' ')	)	{ip=2; flag=1;}
	else if(	(t[2]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 if(	(flag==0) && (t[9]==check)	)		//When ‘t[9]’ = X or O
	{
	     if(	(t[3]==check) && (t[6]==' ')	)	{ip=6; flag=1;}
	else if(	(t[6]==check) && (t[3]==' ')	)	{ip=3; flag=1;}
	else if(	(t[7]==check) && (t[8]==' ')	)	{ip=8; flag=1;}
	else if(	(t[8]==check) && (t[7]==' ')	)	{ip=7; flag=1;}
	else if(	(t[5]==check) && (t[1]==' ')	)	{ip=1; flag=1;}
	else if(	(t[1]==check) && (t[5]==' ')	)	{ip=5; flag=1;}
	}

 //If win or block not possible play a random number
 if(	(check=='X') && (flag==0)	)		
	{
		if(difficulty=='1' || difficulty=='2' || difficulty=='3')
		     easy();
	}
}


/***************************************************************************************/


void ttt::get_computer_move(int count, int game_no)	// Get the computer's move
{
flag=0;					//Will be set to 1 when computer plays a proper move

if (difficulty=='1')					//If Difficulty set to Easy
easy();

else if (difficulty=='2')				//If Difficulty set to Medium
block('X');						//Try and stop Player from winning

else if (difficulty=='3' )				//If Difficulty set to Advanced
{
	block('O');					//Check if winning is possible
	if(flag==0)					//If no win possible
	block('X');					//Try and stop Player from winning
}

else if(difficulty=='4')	 			// If Difficulty set to Expert
  expert(count,game_no);

//Check if position exists for values 0-9 in buffer ‘b[i]’
for (int i=0; i<=9; i++)		
	{
	if (ip==b[i])				//Check if ip=b[i]; if true it is an invalid move
	get_computer_move(count, game_no);	//Thus ask the computer to play again
	}
}

