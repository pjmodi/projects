#include<iostream.h>
#include<conio.h>


class account
{
protected:
 char cust_name[25];
 unsigned long int acc_no;
 char acc_type[8];
};

class cur_acc : public account
{
private:
 float balance;
protected:
 char acc_type="Current";
};

class sav_acc : public account
{
private:
 float balance;
protected:
 char acc_type="Savings";
};

//incomplete