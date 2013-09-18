//-----------------------
//Function Definitions  |
//-----------------------
//
//playfirst()
//playsecond()
//expert()


//Decides which game the computer should play when it is playing first.
void ttt::playfirst(int count)
{
if(flag==0)
	{
	
	//If it is a new game then, enter block
	if(newgame==0)			   
		{
		do				//Selects randomly the game to be played
			{
			select=random(5);  	//assigns the selected game no to select
			}
		//Continue till no. selected is 0 or equal to previous game 
		while(select==0 || select==prevgame); 

		newgame=1;		//This block should not be executed for the other moves
		prevgame=select;	//Used for the next game
		}	


	switch(select)            //chooses from the five games using select
		{
		//1st game
		case 1:	if (count==1)        						ip=7; 
			//When count is 1, play at position 7
                   	
			else if(count==3)
				{
				if(t[1]=='X' || t[9]=='X') 			  	ip=3;
				else if(t[3]=='X' || t[4]=='X')			  	ip=9;
				else if(t[2]=='X' || t[8]=='X' || t[6]=='X')	  	ip=1;
				else if(t[5]=='X')				  	ip=6;
				else easy();
				}

			else if(count==5)
				{
				//playone contains the move that the player played
				switch(playone) 
					{
					case 5: if(t[1]=='X') 				ip=9;
						else if(t[9]=='X')			ip=1;
						else easy();
						break; 

					case 8: if(t[3]=='X')				ip=1;
						else if(t[4]=='X')			ip=3;
						else easy();
						break;

					case 4: if(t[2]=='X' || t[6]=='X' || t[8]=='X')	ip=5;
						else if(t[5]=='X')             		ip=9;    
						else easy();
						break;

					case 1: ip=9;
						break;

					default:easy();		//Pick a random number
						break;

					}
				}

			else if (count==7)
				easy();		//Pick a random number

			else if(count==9)
				{
				for (int i=1;i<=9;i++)
					{
					if(t[i]==' ')	//Play at whichever position is empty
						{
						ip=i;
						break;
						}
					}
				}
			break;	
	
		

		//2nd game
		case 2: if (count==1)                  ip=2;
			//When count is 1, play at position 2		
			  
			else if(count==3)
				{
				if(t[1]=='X' || t[3]=='X' || t[5]=='X' || t[8]=='X')	ip=9;  
				else if(t[4]=='X' || t[6]=='X')				ip=5;
				else if(t[7]=='X')					ip=1;
				else if(t[9]=='X')					ip=3;
				else easy();
				}

			else if(count==5)
				{
				//playone contains the move that the player played
				switch(playone)
					{
					case 7: if(t[1]=='X' || t[3]=='X')		ip=5;
						else if(t[5]=='X' || t[8]=='X')		ip=3;	
						else easy();
						break;

					case 8: if(t[4]=='X' || t[6]=='X' || t[5]=='X')	ip=3;
						else if(t[3]=='X')                	ip=5;
						else easy();
						break;

					case 3: if(t[7]=='X')				ip=5;
						else if(t[1]=='X')			ip=8;	
						else easy();
						break;

					case 1: if(t[3]=='X')                      	ip=8;
						
						else easy();
						break;

					case 4: if(t[8]=='X')				ip=3;
						else if(t[3]=='X')			ip=8;
						else easy();
						break;

					case 5: if(t[8]=='X')				ip=3;
						else easy();
						break;

					case 6: if(t[8]=='X')				ip=1;
						else if(t[3]=='X' || t[1]=='X')		ip=8;
						else easy();
						break;

					default: easy();	//Pick a random number
						 break;
					}	
				}
	
			else if (count==7)
				{
				//playone contains the move that the player played
				switch(playone)
					{
					case 8: if(t[5]=='X')                           ip=4;
						else easy();
						break;
				
					default:easy();		//Pick a random number
						break;
					}
				}


			else if (count==9)
				{
				for (int i=1;i<=9;i++)
					{
					if(t[i]==' ')	//Play at whichever position is empty
						{
						ip=i;
						break;
						}
					}
				}
			break;


			
		//3rd game
		case 3: if (count==1)                 					ip=9;
			//When count is 1, play at position 9
			      	 
			else if(count==3)
				{
				if(t[1]=='X' || t[2]=='X' || t[4]=='X' || t[8]=='X')	ip=3;    
				else if(t[3]=='X' || t[6]=='X')				ip=7;
				else if(t[5]=='X' || t[7]=='X')				ip=1;
				else easy();
				}

			else if(count==5)
				{
				//playone contains the move that the player played
				switch(playone)
					{
					case 6: if(t[1]=='X' || t[2]=='X')		ip=7;
						else if(t[4]=='X')			ip=5;
						else if(t[8]=='X')			ip=1;
						else easy();
						break;

					case 8: if(t[3]=='X' || t[6]=='X')	  	ip=1;
						else easy();
						break;

					case 7: if(t[5]=='X')			  	ip=3;
						else easy();
						break;

					case 5: if(t[7]=='X')			  	ip=3;
						else easy();
						break;

					default: easy();		//Pick a random number
						 break;
					}
				}

			else if (count==7)
				easy();					//Pick a random number

			else if(count==9)
				{
				for (int i=1;i<=9;i++)
					{
					if(t[i]==' ')	//Play at whichever position is empty
						{
						ip=i;	
						break;
						}
					}
				}
			break;



		//4th game
		case 4:	if (count==1)						  	ip=1;
			//When count is 1, play at position 1		
			
			else if(count==3)
				{
				if(t[3]=='X' || t[7]=='X')			 	ip=9;
				else if(t[2]=='X' || t[8]=='X')			 	ip=7;
				else if(t[4]=='X' || t[6]=='X' || t[9]=='X')	 	ip=3;
				else if(t[5]=='X')				 	ip=6;
				else easy();
				}

			else if(count==5)
				{
				//playone contains the move that the player played
				switch(playone)
					{
					case 4: if(t[2]=='X')			  	ip=9;
						else if(t[8]=='X')		  	ip=3;
						else if(t[4]=='X')                      ip=3;
						else easy();
						break;

					case 2: if(t[4]=='X')			  	ip=9;
						else if(t[6]=='X' || t[9]=='X')	  	ip=7;
						else easy();
						break;

					case 9: if(t[5]=='X')                       	ip=2;
						else easy();
						break;

					default: easy();		//Pick a random number
						 break;
					}
				}

			else if (count==7)
				easy();					//Pick a random number

			else if(count==9)
				{
				for (int i=1;i<=9;i++)
					{
					if(t[i]==' ')	//Play at whichever position is empty
						{
						ip=i;
						break;
						}
					}
				}
			break;

				

		//5th game
		
		case 5:	if (count==1)						  	ip=3;
			//When count is 1, play at position 1			
	
			else if(count==3)
				{
				if(t[1]=='X' || t[2]=='X' || t[4]=='X')		  	ip=9;
				else if(t[5]=='X' || t[9]=='X')       		  	ip=7;
				else if(t[6]=='X' || t[8]=='X' || t[7]=='X')	  	ip=1;
				else easy();
				}

			else if(count==5)
				{
				//playone contains the move that the player played
				switch(playone)
					{
					case 6: if(t[1]=='X' || t[2]=='X')	  	ip=7;
						else easy();
						break;

					case 2: if(t[6]=='X')			  	ip=7;
						else if(t[7]=='X')		  	ip=9;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}

			else if (count==7)
				easy();				//Pick a random number

			else if(count==9)
				{
				for (int i=1;i<=9;i++)
					{
					if(t[i]==' ')	//Play at whichever position is empty
						{
						ip=i;	
						break;
						}
					}
				}
			break;
		}
	}
}


/***************************************************************************************/


//Decides which game the computer should play when it is playing second.

void ttt::playsecond(int count)
{
if(flag==0)
	{
	switch(move)	//slects the game to be played based on what the computer has played
		{
		//1st game
		case 1: if (count==2)						  	ip=5;
			//When count is 2, play at 5
    
			else if(count==4)
				{
				if(t[3]=='X' || t[6]=='X')			  	ip=2;
				else if(t[4]=='X')				  	ip=7;
				else if(t[7]=='X' || t[9]=='X')			  	ip=4;
				else if(t[2]=='X')				  	ip=3;
				else if(t[8]=='X')				  	ip=6;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 8: if(t[1]=='X' || t[6]=='X')	 	 ip=7;
						else easy();
						break;

					case 6: if(t[7]=='X')			  	ip=2;
						else if(t[9]=='X')		  	ip=3;
						else easy();
						break; 

					case 4: if(t[8]=='X')			  	ip=7;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}

			else if (count==8)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 6: if(t[2]=='X')			  	ip=8;
						else easy();
						break;

					case 8: if(t[4]=='X')			  	ip=9;
						else easy();
						break;
					default: easy();		//Pick a random number
						 break;
					}
				}
			break;



		//2nd game
		case 2: if (count==2)						  	ip=5;
			//When count is 2, play at 5

			else if(count==4)
				{
				if(t[1]=='X' || t[6]=='X' || t[8]=='X' || t[9]=='X')  	ip=3;
				else if(t[4]=='X' || t[3]=='X' || t[7]=='X')	  	ip=1;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 9: if(t[4]=='X')			  	ip=3;
						else easy();
						break;

					case 7: if(t[6]=='X')			 	ip=1;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}
			else if (count==8)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 6: if(t[1]=='X')			  	ip=8;
						else easy();
						break;	

					case 4: if(t[3]=='X')			  	ip=7;
						else easy();
						break;

					default:easy();		//Pick a random number
						break;
					}	
				}
			break;



		//3rd game
		case 3: if (count==2)							ip=7;
			//When count is 2, play at 7

			else if(count==4)
				{
				if(t[2]=='X' || t[5]=='X' || t[8]=='X')			ip=1;
				else if(t[1]=='X')					ip=2;
				else if(t[6]=='X')					ip=9;
				else if(t[4]=='X')					ip=5;
				else if(t[9]=='X')					ip=6;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 4: if(t[2]=='X')				ip=9;
						else if(t[8]=='X')		 	ip=5;
						else if(t[1]=='X')              	ip=8;
						else easy();
						break;

					case 8: if(t[6]=='X')				ip=1;
						else easy();
						break;
					
					default:easy();			//Pick a random number
						break;
					}
				}

			else if (count==8)
				easy();					//Pick a random number

			break;

				

		//4th game
		case 4:  if (count==2)							ip=6;
			//When count is 2, play at 6

			else if(count==4)
				{
				if(t[1]=='X' || t[3]=='X' || t[9]=='X')		 	ip=7;
				else if(t[2]=='X' || t[8]=='X')				ip=9;
				else if(t[5]=='X')					ip=3;
				else if(t[7]=='X')					ip=1;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 5: if(t[3]=='X')				ip=7;
						else if(t[7]=='X')              	ip=3;
						else easy();
						break;

					case 8: if(t[3]=='X')				ip=1;
						else if(t[1]=='X')               	ip=3;
						else easy();
						break;

					case 3: if(t[8]=='X')				ip=7;
						else easy();
						break;

					case 2: if(t[7]=='X')                    	ip=9;
						else if(t[9]=='X')                	ip=5;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}
			else if (count==8)
				easy();					//Pick a random number

			break;



		//5th game
		case 5: if (count==2)							ip=1;
			//When count is 2, play at 1

			else if(count==4)
				{
				if(t[9]=='X')		  				ip=7;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 9: if(t[2]=='X')                           ip=7;
						else easy();
						break;

					default: easy();		//Pick a random number
						 break;
					}
				}
			else if(count==8)
				easy();					//Pick a random number
						

			break;



		//6th game
		case 6: if (count==2)			  				ip=3;
			//When count is 2, play at 3

			else if(count==4)
				{
				if(t[1]=='X' || t[2]=='X' || t[4]=='X' || t[7]=='X')	ip=5;
				else if(t[5]=='X')					ip=4;
				else if(t[8]=='X' || t[9]=='X')				ip=1;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 7: if(t[2]=='X')				ip=9;
						else easy();
						break;

					case 2: if(t[9]=='X')				ip=7;
						else if(t[7]=='X')                      ip=9;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}

			else if (count==8)
				easy();					//Pick a random number

			break;



		//7th game
		case 7: if (count==2)						 	ip=6;
			//When count is 2, play at 6

		 	else if(count==4)
				{
				if(t[1]=='X')						ip=4;
				else if(t[2]=='X' || t[3]=='X')				ip=5;
				else if(t[4]=='X')					ip=1;
				else if(t[5]=='X')					ip=3;
				else if(t[8]=='X')					ip=9;
				else if(t[9]=='X')					ip=8;
				else easy();
				}

			else if(count==6) 
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 2: if(t[4]=='X')                           ip=9;
						else if(t[9]=='X')                      ip=5;
						else easy();
						break;
					
					default: easy();		//Pick a random number
						 break;
					}
				}
			
			else if (count==8)
				easy();					//Pick a random number

			break;



		//8th game
		case 8: if (count==2)							ip=2;
			//When count is 2, play at 2

		 	else if(count==4)
				{
				if(t[1]=='X' || t[4]=='X' || t[9]=='X')			ip=7;
				else if(t[3]=='X' || t[6]=='X' || t[7]=='X')		ip=9;
				else if(t[5]=='X')					ip=1;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 3: if(t[6]=='X')				ip=5;
						else easy();
						break;

					case 4: if(t[1]=='X')                           ip=5;
						else if(t[9]=='X')                      ip=3;
						else easy();
						break;

					case 6: if(t[3]=='X')                           ip=5;
						else if(t[7]=='X')                      ip=1;
						else easy();
						break;

					case 1: if(t[4]=='X')                           ip=5;
						else easy();
						break;

					default:easy();			//Pick a random number
						break;
					}
				}

			else if (count==8)
				easy();					//Pick a random number

			break;



		//9th game
		case 9: if (count==2)							ip=4;
			//When count is 2, play at 4

			else if(count==4)
				{
				if(t[1]=='X' || t[2]=='X')				ip=5;
				else if(t[3]=='X')					ip=6;
				else if(t[5]=='X')					ip=1;
				else if(t[6]=='X')					ip=3;
				else if(t[7]=='X')					ip=8;
				else if(t[8]=='X')					ip=7;
				else easy();
				}

			else if(count==6)
				{
				//playtwo contains the move that the player played
				switch(playtwo)
					{
					case 2: if(t[6]=='X')                           ip=7;
						else easy();
						break;
					
					default: easy();		//Pick a random number
						 break;
					}
				}

			else if(count==8)
				easy();      				//Pick a random number
			break;
		}
	}	
}


/***************************************************************************************/


void ttt::expert(int count, int game_no)
{
block('O');				//checks for a blocking condition
if(flag==0)
block('X');				//checks for a blocking condition
if (alterplay=='5')                     //if alternate game play is on
	{
	if (game_no%2 != 0)             //odd game    
	   playsecond(count);
	else                            //even game
	   playfirst(count);
	}
else 					//if alternate game play is off
	   playsecond(count);
} 

