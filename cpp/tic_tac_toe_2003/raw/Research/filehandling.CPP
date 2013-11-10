//Buffer marks of students into a file
//10:31 AM 11/26/2002

#include<stdio.h>
#include<conio.h>
#include<fstream.h>
int main()
{
clrscr();
ofstream outf("pushkar.pjm");
int i;
cout << "Enter marks of the student - ";
cin >> i;
outf << i;
getch();
return 0;
}

