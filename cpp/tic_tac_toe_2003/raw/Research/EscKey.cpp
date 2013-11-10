#include<iostream.h>
#include<conio.h>

void main()
{
clrscr();
char ch='0';
while (!kbhit())
{
ch=getche();
cout << "\n\nYou entered: " << ch;
getch();
}
}
