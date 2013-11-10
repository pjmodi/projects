//-----------------------
//Function Definitions  |
//-----------------------
//
//initmouse()
//showmouseptr()
//hidemouseptr()
//changecursor()
//restrictmouseptr()
//getmousepos()


//Initializes the mouse drivers
initmouse()							
{
i.x.ax=0;								
int86(0x33,&i,&o);						
return(o.x.ax);
}


/***************************************************************************************/

//Shows the mouse pointer
void showmouseptr()						
{
i.x.ax=1;
int86(0x33,&i,&o);
}


/***************************************************************************************/

//Hides the mouse pointer
void hidemouseptr()						
{
i.x.ax=2;
int86(0x33,&i,&o);
}


/***************************************************************************************/

//Changes the mouse pointer to a different cursor
void changecursor(int *shape)			
{
i.x.ax=9;
i.x.bx=0;
i.x.cx=0;
i.x.dx=(unsigned)shape;
segread(&s);
s.es=s.ds;
int86x(0x33,&i,&i,&s);
}


/***************************************************************************************/

//Restricts the mouse pointer within specified area
void restrictmouseptr(int x1,int y1,int x2,int y2)	
{
i.x.ax=7;
i.x.cx=x1;
i.x.dx=x2;
int86(0x33,&i,&o);
i.x.ax=8;
i.x.cx=y1;
i.x.dx=y2;
int86(0x33,&i,&o);
}


/***************************************************************************************/

//Checks the x & y co-ords. & which button was clicked
void getmousepos(int *button, int *x, int *y)		
{
i.x.ax=3;
int86(0x33,&i,&o);
*button=o.x.bx;
*x=o.x.cx;
*y=o.x.dx;
}




