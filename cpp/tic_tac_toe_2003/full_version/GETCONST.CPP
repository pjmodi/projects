//-----------------------
//Function Definitions  |
//-----------------------
//
//ttt()
//get()


//Constructor for initializing values of data members
ttt::ttt(void)  				
{
ip=0; move_no=0; move=0; flag=0; newgame=0; playone=0; playtwo=0; select=1;

b[0]=0; b[1]=0; b[2]=0; b[3]=0; b[4]=0;
b[5]=0; b[6]=0; b[7]=0; b[8]=0; b[9]=0;

t[0]=' '; t[1]=' '; t[2]=' '; t[3]=' '; t[4]=' ';
t[5]=' '; t[6]=' '; t[7]=' '; t[8]=' '; t[9]=' ';
}


/***************************************************************************************/

void ttt::get(void)
{
gotoxy(1,23);
cout << " Please click on the appropriate position.";
ip=0;	

	while(ip==0)
	{
	getmousepos(&button, &x, &y);			//Gets position & button of clicking

	if((button & 1)==1)				//If left-click enter block below
		{

			//If click between 200 & 266 of X co-ordinate enter block
			if(x>199 && x<267)		
			{

				//If click between 85 & 142 of Y co-ordinate
				if(y>84 && y<143)	
				ip=1;			//Player wants to play at position 1
				
				//If click between 150 & 206 of Y co-ordinate
				else if(y>149 && y<207) 	
				ip=4;			//Player wants to play at position 4

				//If click between 214 & 280 of Y co-ordinate
				else if(y>213 && y<281) 	
				ip=7;			//Player wants to play at position 7
			}

			//If click between 274 & 335 of X co-ordinate enter block
			else if(x>273 && x<336)		
			{

				//If click between 85 & 142 of Y co-ordinate
				if(y>84 && y<143)		
				ip=2;			//Player wants to play at position 2
		
				//If click between 150 & 206 of Y co-ordinate
				else if(y>149 && y<207) 	
				ip=5;			//Player wants to play at position 5

				//If click between 214 & 280 of Y co-ordinate
				else if(y>213 && y<281) 	
				ip=8;			//Player wants to play at position 8
			}

			//If click between 343 & 420 of X co-ordinate enter block
			else if(x>342 && x<421)		
			{

				//If click between 85 & 142 of Y co-ordinate
				if(y>84 && y<143)		
				ip=3;			//Player wants to play at position 3
			
				//If click between 150 & 206 of Y co-ordinate
				else if(y>149 && y<207) 	
				ip=6;			//Player wants to play at position 6

				//If click between 214 & 280 of Y co-ordinate
				else if(y>213 && y<281) 	
				ip=9;			//Player wants to play at position 9
			}
		}
	}

	//Check if position already occupied for values 0-9 in buffer ‘b[i]’
	for (int i=0; i<=9; i++)			
	{
	if (ip==b[i])			//Check if ip=b[i] which means it is an invalid move
		{
		get();			//Thus, re-calls the get() function
		}
	}
}
