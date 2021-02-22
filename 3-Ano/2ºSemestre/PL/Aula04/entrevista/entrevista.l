%option noyywrap yylineno

%%
    char *reporter, *entrevistado;
    int contexto; // 0 = R, 1 = E

R=.*                    {reporter = strdup(yytext+2);}
E=.*                    {entrevistado = strdup(yytext+2);}
E:                      {printf("%s",yytext);contexto = 1;}
R:                      {printf("%s",yytext);contexto = 0;}
("Eu"|"eu")             {if(contexto == 0) printf("%s",reporter); else printf("%s",entrevistado);}
tu                      {if(contexto == 1) printf("%s",reporter); else printf("%s",entrevistado);}

%%

int main(int argc, char* argv[]){
    yylex();
    return 0;
}