%option noyywrap
%%
::.+   {printf("%s\n",yytext+2);}
.|\n    {;}
%%
int main(){
    yylex();
    return 0;
}
