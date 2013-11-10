#include<iostream.h>
#include<conio.h>

class rights
{

long unsigned int x;	//Let x be the number of shares to be owned before using Rights issue card
long unsigned int z;	//Allocated Budget
long double a;		//Cost of a single share

public:
void get_val(void)	//Function for getting values
{
	do
	{
	cout << "\nEnter the Total Budget available for buying the share(s)."
	     << "\nPlease make sure the amount is a multiple of 500 - ";
	cin >> z;
	}
	while ((z%500)!=0);

	cout <<"\nEnter the cost of a single share."
	     <<"\nPlease make sure the cost is a multiple of 0.5 - ";
	cin >> a;
}

void calc(void)		//Function for calculating no. of shares to be bought
{
x=(z/(a+0.5));
}

void out_val(void)	//Function for output of results
{
cout << "\n\n-----------------------------------------------------------------------\n\n"
<< "The total no. of share(s) that you must have on the board = "
<< x << "\n\nThis will cost you " << x*a
<< "\n\nThe balance amount of " << z-(x*a) <<" can be used to buy "
<< x/2 << " share(s) later. \n\nYou will thus profit by an amount of "
<< ((x/2)*a)-(x/2);
}

};

void main()
{
char get;
do
{
float b=0.5;
clrscr();
rights r1;
cout
<< "-----------------------------------------------------------------------"
<< "\n|            RISCULATOR - Rights Issue Calculator v1.0.0129	      |"
<< "\n|		   Program developed by Pushkar Modi		      |"
<< "\n|      	   (c) 2003 Dicelords Interactive - www.dicelords.com	      |"
<< "\n-----------------------------------------------------------------------\n\n\n";
r1.get_val();
r1.calc();
r1.out_val();
cout << "\n\n\n\nPress 'Y' to continue or any other key to exit.";
get=getch();
}
while (get=='y' || get=='Y');
}