%{
#include <stdio.h>
#include <stdlib.h>
int yylex();
int yyerror(char* s);
#define _GNU_SOURCE
%}

%union {char* n;}
%token ID
%type <n> ID elems arvd elem

%%    
      // dir1(file1,file2,dir2(file3,file3),dir3())

fs    : arvd              {system($1);}
      | fs arvd           {system($2);}
      ;

arvd  : ID '(' elems ')'  {asprintf(&$$,"mkdir %s\n cd %s\n %s\n cd ..",$1,$1,$3);}    // ficheiros
      | ID '(' ')'        {asprintf(&$$,"mkdir %s",$1);}    // dir
      ;

elems : elem              {$$ = $1;}
      | elem ',' elems    {asprintf(&$$,"%s\n%s",$1,$3);}
      ;

elem  : ID                {asprintf(&$$,"touch %s",$1);}    // ficheiros
      | arvd              {$$=$1;}    // dir
      ;

%%

#include "lex.yy.c"

int main(){
  yyparse();
  return 0;
}
int yyerror(char* s){
  printf("erro: %s\n",s);
  return 0;
}
