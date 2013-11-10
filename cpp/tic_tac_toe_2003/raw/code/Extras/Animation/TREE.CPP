#include <graphics.h>
#include <math.h>
#include <stdlib.h>
#include <conio.h>
#include<dos.h>

main( )
{ 

int gd = DETECT, gm ;
int drawtree ( int x1, int y1, float a, float l, float f, int n ) ;
initgraph ( &gd, &gm, "d:\\tc\\bgi" ) ;

 

drawtree ( 280, 350, 270, 60, 90, 6 ) ;

 

getch( ) ;
closegraph( ) ;
restorecrtmode( ) ;

}

 

drawtree ( int x1, int y1, float a, float l, float f, int n )

{

int i, num = 3, x2, y2 ;
float spreadratio = 0.75, lenratio = 0.9 ;
float delang, ang ;

 

if ( n > 0 )
{

switch ( n )
{

case 1:

setcolor ( LIGHTGREEN ) ;
break ;

case 2:

setcolor ( GREEN ) ;
break ;

case 3:

setcolor ( RED ) ;
break ;

case 4:

setcolor ( BROWN ) ;
break ;

default:

setcolor ( DARKGRAY ) ;
break ;

}

 

x2 = x1 + l * cos ( 0.0174 * a ) ;
y2 = y1 + l * sin ( 0.0174 * a ) ;

 

for ( i = 0 ; i < n ; i++ )
{

line ( x1 + i, y1, x2 + i, y2 ) ;
line ( x1 - i, y1, x2 - i, y2 ) ;

}

 

randomize( ) ;
num = random ( 5 ) + 2 ;
if ( num > 1 )

delang = f / ( num - 1.0 ) ;

else

delang = 0.0 ;

 

ang = a - f / 2.0 - delang ;

for ( i = 1 ; i <= num ; i++ )
{

ang += delang ;
delay ( 10 ) ;
drawtree ( x2, y2, ang , l * lenratio, random ( 90 ) + 1 , n - 1 ) ;

}

}

else
{

setfillstyle ( SOLID_FILL, random ( 15 ) + 1 ) ;
fillellipse ( x1, y1, 3, 6 ) ;

}

}

