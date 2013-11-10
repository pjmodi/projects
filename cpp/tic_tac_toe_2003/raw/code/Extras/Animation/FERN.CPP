#include <graphics.h>
#include <stdlib.h>
#include<conio.h>


int j, k, xscale, yscale, xoffset, yoffset, pr, p[4], pk[4] ;
long unsigned int i ;
float a[4], b[4], c[4], d[4], e[4], f[4], x, y, newx ; 

draw ( int color );

main( )
{

int gd=DETECT, gm ;
initgraph ( &gd, &gm, "d:\\tc\\bgi" ) ;



a[0] = 0 ; a[1] = 0.20 ; a[2] = -0.15 ; a[3] = 0.85 ;
b[0] = 0 ; b[1] = -0.26 ; b[2] = 0.28 ; b[3] = 0.04 ;
c[0] = 0 ; c[1] = 0.23 ; c[2] = 0.26 ; c[3] = -0.04 ;
d[0] = 0.16 ; d[1] = 0.22 ; d[2] = 0.24 ; d[3] = 0.85 ;
e[0] = 0 ; e[1] = 0 ; e[2] = 0 ; e[3] = 0 ;
f[0] = 0 ; f[1] = 1.6 ; f[2] = 0.44 ; f[3] = 1.6 ;
p[0] = 480 ; p[1] = 2621 ; p[2] = 4915 ; p[3] = 32767 ;


xscale = 25 ;
yscale = 25 ;
xoffset = 300 ;
yoffset = 50 ;


draw ( 10 ) ;

getch();

closegraph( ) ;
restorecrtmode( ) ;

}


draw ( int color )
{

int px, py ;


x = 0 ;
y = 0 ;
for ( i = 1 ; i <= 30000 ; i++ )
{

j = rand( ) ;
k = ( j < p[0] ) ? 0 : ( ( j < p[1] ) ? 1 : ( ( j < p[2] ) ? 2 : 3 ) ) ;
newx = ( a[k] * x + b[k] * y + e[k] ) ;


y = ( c[k] * x + d[k] * y + f[k] ) ;
x = newx ;


px = x * xscale + xoffset ;
py = ( 350 - y * yscale + yoffset ) ;


if ( ( px >= 0 ) && ( px < 640 ) && (py >= 0 ) && ( py < 480 ) )

putpixel ( px, py, color ) ;

}

}
