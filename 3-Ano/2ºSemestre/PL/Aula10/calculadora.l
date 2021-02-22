%{
int yyerror(char* s);
#include <math.h>
%}

%option noyywrap yylineno
%%
[0-9]+(\.[0-9]+)?           {yylval.n = atof(yytext); return NUM;}
pi                          {yylval.n = M_PI; return NUM;}
[a-zA-Z]                    {yylval.c = yytext[0]; return VAR;}               
exp                         {yylval.f = exp; return FUN1;}
log                         {yylval.f = log; return FUN1;}
sqrt                        {yylval.f = sqrt; return FUN1;}
sin                         {yylval.f = sin; return FUN1;}
cos                         {yylval.f = cos; return FUN1;}
ans|\.                      {return ANS;}
[()+\-*\/\n^=]              {return yytext[0];}     // Símbolos terminais
[ ]                         ;
#.*                         {} // Comentário
.                           {yyerror("Caracter inválido.");}
%%