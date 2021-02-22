%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int yylex();
int yyerror(char* s);

double mem[256];
double ans;

struct tu{
  int t; // 1 = str, 2 = double, 3 = char, 4 = fun
  union {
    char* s;
    double n; 
    char c;
    double (*f)(double);
  } v;
}

#define set_num(v,x){
  v.t = 2;
  v.v.n=x;
}
#define set_str(v,x){
  v.t = 1;
  v.v.s=x;
}

%}

%union {
     double n; 
     char c;
     double (*f)(double);
     char* s;
     {struct tu} *u;
}
%token NUM VAR ANS FUN1 STR
%type<n> NUM 
%type<u> exp
%type<c> VAR ANS
%type<f> FUN1
%type<s> STR

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
    /*| exp '-' exp        {$$ = $1 - $3;}
    | exp '*' exp        {$$ = $1 * $3;}
    | exp '/' exp        {$$ = $1 / $3;}
    | exp '^' exp        {$$ = pow($1,$3);}
    | VAR '=' exp        {$$ = mem[$1] = $3;}
    | '-' exp            {$$ = -1 * $2;}*/
    | NUM                {set_num($$,$1);}
    //| VAR                {$$ = mem[$1];}
    | STR                {set_str($$,$1);}
    /*| ANS                {$$ = ans;}
    | '(' exp ')'        {$$ = $2;}
    | FUN1 '(' exp ')'   {$$ = $1($3);}   */
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