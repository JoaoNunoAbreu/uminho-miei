%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int yylex();
int yyerror(char* s);

double mem[256];
double ans;

%}

%union {
     double n; 
     char c;
     double (*f)(double);
}
%token NUM VAR ANS FUN1
%type<n> NUM exp
%type<c> VAR ANS
%type<f> FUN1

%right '='
%left '+' '-'
%left '*' '/'
%right '^'

%%    

calc : calc exp '\n'     {printf("%f\n",$2); ans = $2;}
     | calc error '\n'   // Permite ter erros e não fechar o programa
     | calc '\n'
     | 
     ;

exp : exp '+' exp        {$$ = $1 + $3;}
    | exp '-' exp        {$$ = $1 - $3;}
    | exp '*' exp        {$$ = $1 * $3;}
    | exp '/' exp        {$$ = $1 / $3;}
    | exp '^' exp        {$$ = pow($1,$3);}
    | VAR '=' exp        {$$ = mem[$1] = $3;} // FIX prioridades
    | '-' exp            {$$ = -1 * $2;} 
    | NUM                {$$ = $1;}
    | VAR                {$$ = mem[$1];}
    | ANS                {$$ = ans;}
    | '(' exp ')'        {$$ = $2;}
    | FUN1 '(' exp ')'   {$$ = $1($3);}   
    ;

%%

#include "lex.yy.c"

int main(){
  yyparse();
  return 0;
}
int yyerror(char* s){
  printf("erro %d: %s junto a '%s'\n",yylineno,s,yytext);
  return 0;
}