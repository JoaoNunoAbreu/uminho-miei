%{
#include "y.tab.h"
void yyerror(char* s);
%}
%option noyywrap yylineno
%%
[ \n]           {}
#.*             {}                      // comentários
[()]            {return(yytext[0]); }   // símbolos terminais
.               {yyerror("Simbolo desconhecido");}
%%