%{
  int yylex();
  void yyerror(char*);
  #include <stdio.h>
  int n = 0;
%}

%union {char *s; int n;}
%token ID STR TXT AB BF
%type <s> ID STR TXT

%%
bibtex  : pupl bibtex
        | pupl
        ;

pupl    : '@' ID '{' ID ',' pares '}'

pares   : ATR '=' val
        | ATR '=' val ',' pares
        ;

val     : INT
        | STR
        | BCH
        ;

%%

int main(){
  yyparse();
  return 0;
}

void yyerror(char* s){
  extern int yylineno;
  extern char* yytext;
  fprintf(stderr,"Linha %d: %s (%s)\n",yylineno,s,yytext);    
}

