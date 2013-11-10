//----------------------
//Function Definitions  |
//----------------------
//
//banner()		-- Displays huge TTT banner!


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
outtextxy(272,320,"   Demo  ");			//Goes to co-ordinate (x, y and outputs “text”)

delay(3000);						//Waits for 3 seconds before exiting
}


void lastpage(void)
{
cleardevice();

//Sets font-type=11,alignment as horizontal and font-size=0
settextstyle(11, HORIZ_DIR, 0);

outtextxy(1,90,"    Thankyou for playing our demo version");
outtextxy(1,120,"   The full version includes:");
outtextxy(1,140,"\n 1: Single Player Game");
outtextxy(1,155,"      - Quick Game");
outtextxy(1,170,"      - Tournament");
outtextxy(1,200,"\n 2: Two Player Game");
outtextxy(1,215,"      - Quick Game");
outtextxy(1,230,"      - Tournament");
outtextxy(1,260,"\n 3: Four Difficulty Levels");
outtextxy(1,275,"      - Easy");
outtextxy(1,290,"      - Medium");
outtextxy(1,305,"      - Advanced");
outtextxy(1,320,"      - Expert");
outtextxy(1,350,"\n 4: Alternate Game Play - ON/OFF");

outtextxy(1,400,"   Download the full version from www.dicelords.com/ttt");

delay(10000);
}
