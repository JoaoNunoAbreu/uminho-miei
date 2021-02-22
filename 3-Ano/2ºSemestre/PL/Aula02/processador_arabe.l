%option noyywrap
%%
    int c = 0;
:: {c++;}
.|\n {;}
<<EOF>> {printf("%d\n",c); return 0;}
%%
int main(){
    yylex();
    return 0;
}
