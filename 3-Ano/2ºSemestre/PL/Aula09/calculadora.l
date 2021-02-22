%{
int yyerror(char* s);
%}

%option noyywrap yylineno
%%
[0-9]+(\.[0-9]+)?           {yylval.n = atof(yytext); return NUM;}
[ ]                         ;
[a-zA-Z]                    {yylval.c = yytext[0]; return VAR;}               
sqrt                        {return SQRT;}
ans                         {return ANS;}
[()+\-*\/\n^=]              {return yytext[0];}     // Símbolos terminais
#.*                         {} // Comentário
.                           {yyerror("Caracter inválido.");}
%%