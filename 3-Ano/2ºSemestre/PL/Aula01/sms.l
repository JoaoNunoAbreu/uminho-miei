%{
/* Declaracoes C diversas */
%}

%%
118 {printf("Emergencia\n") ; }
[0-9]+  {printf("%s\n",yytext) ; }
.|\n    { ; }
%%
int yywrap()
{ return(1); }

int main()
{ yylex(); return 0; }
