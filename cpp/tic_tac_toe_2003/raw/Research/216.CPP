//Average marks of 10 students
//10:13 PM 11/7/2002

#include<stdio.h>
#include<conio.h>
void main()
{
int i;
float marks[6],avg=0.0,sum=0.0;
clrscr();
for(i=1;i<=5;i++)
	{
	printf("Enter the marks of student %d - ",i);
	scanf(" %f",&marks[i]);
	}
for(i=1;i<=5;i++)
	{
	 sum+=marks[i];
	}
printf("\nThe average marks of 5 students are %f",avg=sum/5);
getch();
}

