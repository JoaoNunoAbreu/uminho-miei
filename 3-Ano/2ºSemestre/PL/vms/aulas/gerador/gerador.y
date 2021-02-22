%{
#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int yylex();
int yyerror(char* s);

int COUNT_VAR = 0;
struct simb{
  int add;
  int typ;    // 1 = int, 2 = string, 3 = real 
} TS[256];

#define _ad(x) TS[x].add
#define _ty(x) TS[x].typ

%}

%union{
    char c;    // id de variável
    char *s;   // string
    int n;
    char* ass; // código assembly
    struct exp{
      char * ass;
      int typ;
    } ex;      //  expressão com tipo
}

%token TINT TSTR LER ESCREVER
%token <c> VAR 
%token <n> NUM 
%token <s> STR
%type <ass> state prog decls decl
%type <ex> exp

%left '+' '-'
%left '/' '*'

%%
axioma: decls prog      {printf("%s\nstart\n%s\nstop\n",$1,$2);}
      ;

decls: decls decl       {asprintf(&$$, "%s\n%s", $1, $2);}
     |                  {$$ = "";}
     ;

prog: prog state        {asprintf(&$$, "%s\n%s", $1, $2);}
    | state             {$$ = $1;}
    ;

state: VAR '=' exp ';'  {asprintf(&$$, "%s\nstoreg %d // %c",$3,_ad($1),$1);}
     | VAR '=' LER ';'  {asprintf(&$$, "read\natoi\nstoreg %d // %c",_ad($1),$1);}
     | ESCREVER exp ';' {
       if($2.typ == 1) asprintf(&$$, "%s\nwritei\n", $2.ass);
       if($2.typ == 2) asprintf(&$$, "%s\nwrites\n", $2.ass);
     }
     ;

decl : TINT VAR ';'     {asprintf(&$$, "pushi 0 // %c",$2); _ad($2) = COUNT_VAR; COUNT_VAR++;}
     | TSTR VAR ';'     {$$ = "//fixme";}
     ;   

exp: TINT VAR ';'       {asprintf(&$$, "pushi 0 // %c",$1);}
   | VAR                {asprintf(&$$, "pushg %d // %c",_ad($1),$1);}
   | STR                {asprintf(&$$, "//fixme pushs %s",$1);}
   | exp '+' exp        {asprintf(&$$, "%s\n%s\nadd",$1,$3);}
   | exp '-' exp        {asprintf(&$$, "%s\n%s\nsub",$1,$3);}
   | exp '*' exp        {asprintf(&$$, "%s\n%s\nmul",$1,$3);}
   | exp '/' exp        {asprintf(&$$, "%s\n%s\ndiv",$1,$3);}
   | '(' exp ')'        {$$ = $2;}
   ;

%%

#include "lex.yy.c"

int main(int argc, char* argv[]){
  extern FILE * yyin;
  if(argc == 2) yyin = fopen(argv[1],"r");
  yyparse();
  return 0;
}
int yyerror(char* s){
  printf("erro %d: %s junto a '%s'\n",yylineno,s,yytext);
  return 0;
}