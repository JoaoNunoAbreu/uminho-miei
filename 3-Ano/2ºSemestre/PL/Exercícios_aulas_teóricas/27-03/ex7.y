%{
#include <stdio.h>
#include <stdlib.h>
int yylex();
int yyerror(char* s);
#define _GNU_SOURCE
%}

%union {char* n;}
%token URI A STR 
%type <n> URI
%%    

gtriplos      : sub gpares '.'

gpares        : pares
              | gpares ';' pares
              ;

pares         : rel complementos

complementos  : comp
              | complementos ',' comp
              ;

comp          : URI
              | STR ;
sub           : URI ;
rel           : A
              | URI ;

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