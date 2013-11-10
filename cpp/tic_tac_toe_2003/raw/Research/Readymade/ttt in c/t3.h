/*
*   Program: Tic-Tac-Toe
*   Author:  Joshua Dean Thompson
*   Last Update: June 30, 2000
*   Language: C
*   Compiler: DJGPP
*   File: t3.h 
*   Description: main header file
*/

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
