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
%type<n> NUM exp termo parc fact
%type<c> VAR ANS
%type<f> FUN1
%%    

calc : calc exp '\n'        {printf("%f\n",$2); ans = $2;}
     | calc atrib '\n'
     | calc error '\n'      // Permite ter erros e não fechar o programa
     | calc '\n'
     | 
     ;

exp : parc                  {$$ = $1;}    
    | exp '+' parc          {$$ = $1 + $3;}
    | exp '-' parc          {$$ = $1 - $3;}
    ;

parc : fact                 // Dá prioridade às mults e divs
     | parc '*' fact        {$$ = $1 * $3;}
     | parc '/' fact        {$$ = $1 / $3;}
     ;

fact : termo                {$$ = $1;}
     | termo '^' fact       {$$ = pow($1,$3);}

atrib : VAR '=' exp         {mem[$1] = $3;}

termo : NUM                 {$$ = $1;}    
      | VAR                 {$$ = mem[$1];}
      | ANS                 {$$ = ans;}
      | '(' exp ')'         {$$ = $2;}
      | FUN1 '(' exp ')'    {$$ = $1($3);}   
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