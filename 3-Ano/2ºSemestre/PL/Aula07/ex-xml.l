%{
#include "y.tab.h"
void yyerror(char* s);
%}
%option noyywrap yylineno
%x texto entreMM
ASPAS (["][^"\n]*["]|['][^'\n]*['])
%%

\<!--.*--\>             {}
<*>[<]                  {BEGIN entreMM; return yytext[0];}
<*>[>]                  {BEGIN texto;   return yytext[0];}
<*>\<\/                 {BEGIN entreMM; return AB;}
<*>\/\>                 {BEGIN texto;   return BF;}

<entreMM>{
    [ \t\n]             {}
    [a-zA-Z:\-_0-9]+    {yylval.s = strdup(yytext); return ID;}
    {ASPAS}             {yylval.s = strdup(yytext); return STR;}
    [=]                 {return yytext[0];}
}
<texto>{
    [^<>]+/\<           {yylval.s = strdup(yytext); return TXT;}
    [ \t\n]+            {}
    [^<>]+              {yylval.s = strdup(yytext); return TXT;}
    \<!--.*--\>         {}
}

<*>.                    {yyerror("Símbolo desconhecido");}
%%