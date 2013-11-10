//Buffer marks of students into a file
//10:31 AM 11/26/2002

#include<iostream.h>
#include<conio.h>
#include<fstream.h>

void main()
{
 int b[10];
 ofstream buffer;
 buffer.open("buffer_ttt.txt", ios::ate);
// buffer << winner << ": ";

 for (int i=0; i<=9; i++)
 b[i]=i;

 for (int j=0; j<=9; j++)
 buffer << b;

 buffer << "\t";
 buffer.close();
}






/*
clrscr();
char i[]="123457689";
ofstream settings("superman.txt");
settings << i;
settings.close();
getch();


int j;
ifstream input("superman");
input >> j;
cout << endl << j;
input.close();
getch();
*/

