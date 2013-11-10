/*
*   Program: Tic-Tac-Toe
*   Author:  Joshua Dean Thompson
*   Last Update: June 30, 2000
*   Language: C
*   Compiler: DJGPP
*   File: t3.c
*   Description: main source file
*/

#include <conio.h>
#include <stdlib.h>

#ifndef TIC_TAC_TOE_H
#define TIC_TAC_TOE_H

/* Global Data */

static char Board[3][3] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }  };
static char Icons[3] = { ' ', 'X', 'O' };

/* Function Prototypes */

int wanna_play( void );
void init_board( void );
void init_screen( void );
void update_board( void );
int check_if_winner();
void get_player_move( int player );
void get_computer_move( int player );
void play_game( int gametype );
void print_credits( void );
int main( void );

#endif


int main( void )
{
     int gametype, ans;

	if( ( gametype = wanna_play() ) != 0 )
	{
		do
	     {
		     init_board();
		init_screen();

	       play_game( gametype );

		     gotoxy( 3, 24 );
		cputs( "Do you wish to play again? y/n:  " );
		do
		  {
			     ans = getch();
			if( ans == 'y' || ans == 'n' )
				break;
		} while(1);

		gotoxy( 3, 24 );
		     cputs( "                                          " );

	} while( ans == 'y' );
	}

	print_credits();
	return 0;
}

/* Tell user about game and see if they want to play */
int wanna_play( void )
{
	int ans;

	clrscr();
	cputs( "+-----------------------------------------------------------------------------+\r\n" );
     cputs( "|                                   WELCOME                                   |\r\n" );
     cputs( "+-----------------------------------------------------------------------------+\r\n\r\n" );
	cputs( "    Welcome to the classic game of Tic-Tac-Toe. This simple\r\n");
	cputs( "game is played on a 3 x 3 grid. Each player takes a choice of\r\n");
	cputs( "either X or O, then using only wit, try to creat a simple line\r\n");
	cputs( "of 3 of their respective symbol.  Whoever does so first, wins.\r\n\r\n");
	cputs( "    If all the squares are filled, but no lines created, a draw\r\n");
	cputs( "is the result. The game switches between who goes first, X or O.\r\n\r\n");
	cputs( "What kind of game do you wish to play?\r\n");
     cputs( "(0) None - just quit\r\n");
     cputs( "(1) Player vs Player - Play against another human\r\n" );
     cputs( "(2) Player vs Computer - Play against a beatable computer\r\n\r\n" );
     cputs( "Enter Choice:" );

	do
     {
		ans = getch();
		switch( ans )
	  {
	       case '0': return 0;
	       case '1': return 1;
	       case '2': return 2;
	  }

	} while(1);
}

/* Initialize Board to Default Values */
void init_board( void )
{
     int i, j;

	for( i = 0; i < 3; i++ )
		for( j = 0; j < 3; j++ )
			Board[i][j] = 0;
}

/* Set up Screen to show Game Board */
void init_screen( void )
{
	clrscr();
     cputs( "+-----------------------------------------------------------------------------+\r\n" );
     cputs( "|                                 TIC-TAC-TOE                                 |\r\n" );
     cputs( "+-----------------------------------------------------------------------------+\r\n\r\n" );
	cputs( "                                 C O L U M N S\r\n" );
     cputs( "                                [1]   [2]   [3]\r\n" );
	cputs( "                              +-----+-----+-----+\r\n" );
     cputs( "                              |     |     |     |\r\n" );
     cputs( "                           [1]|     |     |     |\r\n" );
     cputs( "                         R    |     |     |     |\r\n" );
     cputs( "                              +-----+-----+-----+\r\n" );
     cputs( "                         O    |     |     |     |\r\n" );
     cputs( "                           [2]|     |     |     |\r\n" );
     cputs( "                         W    |     |     |     |\r\n" );
     cputs( "                              +-----+-----+-----+\r\n" );
     cputs( "                         S    |     |     |     |\r\n" );
     cputs( "                           [3]|     |     |     |\r\n" );
     cputs( "                              |     |     |     |\r\n" );
     cputs( "                              +-----+-----+-----+\r\n\r\n" );
     cputs( "+-----------------------------------------------------------------------------+\r\n" );
     cputs( "|                                                                             |\r\n" );
     cputs( "|                                                                             |\r\n" );
     cputs( "|                                                                             |\r\n" );
     cputs( "+-----------------------------------------------------------------------------+" );
}

/* Update the screen with the current Board */
void update_board( void )
{
	gotoxy( 34, 9 );
     putch( Board[0][0] );
     gotoxy( 40, 9 );
     putch( Board[0][1] );
     gotoxy( 46, 9 );
     putch( Board[0][2] );

     gotoxy( 34, 13 );
     putch( Board[1][0] );
     gotoxy( 40, 13 );
     putch( Board[1][1] );
     gotoxy( 46, 13 );
     putch( Board[1][2] );

     gotoxy( 34, 17 );
     putch( Board[2][0] );
     gotoxy( 40, 17 );
     putch( Board[2][1] );
     gotoxy( 46, 17 );
     putch( Board[2][2] );

     gotoxy( 3, 23 );
}

/* Check if any player has completed a line */
int check_if_winner()
{
     int i, k, j;
	for( i = 1; i < 3; i++ )
     {
	for( k = 0; k < 3; k++ )
			if( Board[k][0] == Icons[i] && Board[k][1] == Icons[i] && Board[k][2] == Icons[i] )
			return i;

	  for( j = 0; j < 3; j++ )
			if( Board[0][j] == Icons[i] && Board[1][j] == Icons[i] && Board[2][j] == Icons[i] )
			return i;

	  if( Board[0][0] == Icons[i] && Board[1][1] == Icons[i] && Board[2][2] == Icons[i] )
				return i;

	  if( Board[0][2] == Icons[i] && Board[1][1] == Icons[i] && Board[2][0] == Icons[i] )
				return i;
     }

     return 0;
}

/* Get a user's move */
void get_player_move( int player )
{
     int row, column;

     do
	{
		gotoxy( 3, 23 );
		cprintf( "Player[%i]: Enter Row Number: ", player );
		do {  row = getch(); } while( row < '0' || row > '4' );
		gotoxy( 3, 23 );

	     cprintf( "Player[%i]: Enter Column Number: ", player );
		do {  column = getch(); } while( column < '0' || column > '4' );
	gotoxy( 3, 23 );

	     cputs( "                                   " );

	} while( Board[ row - 0x31 ][ column - 0x31 ] != 0 );

     Board[ row - 0x31 ][ column - 0x31 ] = Icons[player];
}

/* Get the computer's move */
void get_computer_move( int player )
{
     int row, column;

     do
     {
	  row = random(4) % 3;
	  column = random(4) % 3;
     } while( Board[ row ][ column ] != 0 );

     Board[ row ][ column ] = Icons[player];
}

/* Play the game */
void play_game( int gametype )
{
     int i = 0, player = 1;

	while( i < 9 )
	{
	  get_player_move(player);
		update_board();

		if( player == 1 )
			player = 2;
		else
			player = 1;

		gotoxy( 3, 23 );
	  i++;

	     if( check_if_winner() == 1 )
		{
		     cputs ( "Player 1 (X) Wins!                        " );
			return;
	  }
	  else if( check_if_winner() == 2 )
	  {
		cputs ( "Player 2 (0) Wins!                        " );
		 return;
	  }

	  if( gametype == 2 )
	  {
	       get_computer_move( player );

	       i++;
	       update_board();
	       if( player == 1 )
			player = 2;
		else
			player = 1;

	       if( check_if_winner() == 1 )
		{
		     cputs ( "Player 1 (X) Wins!                        " );
			return;
	       }
	       else if( check_if_winner() == 2 )
	       {
		     cputs ( "Player 2 (0) Wins!                        " );
			 return;
	       }
	  }
     }
}

/* Print the game credits */
void print_credits( void )
{
     clrscr();
     cputs( "+-----------------------------------------------------------------------------+\r\n" );
     cputs( "|                                    CREDITS                                  |\r\n" );
     cputs( "+-----------------------------------------------------------------------------+\r\n\r\n" );
     cputs( "Author: Joshua Dean Thompson\r\n" );
     cputs( "Inspiration: SHARIQ HASEEB's Tic-Tac-Toe\r\n\r\n" );
     cputs( "For original game and code by Shariq Haseeb goto:\r\n" );
     cputs( "www.planet-source-code.com\r\n\r\n");
     cputs( "Thank you for using this program, hope you had fun.\r\n" );
     cputs( "Send comments, questions, etc to: fd72@msn.com\r\n" );
}
