%option noyywrap yylineno

%%
    char eu[100], ele[100];

EU=.+                        {yytext[strlen(yytext)-1] = '\0';strcpy(eu,yytext+3);}
ELE=.+                       {yytext[strlen(yytext)-1] = '\0';strcpy(ele,yytext+4);}
("eu: "|"Eu: "|"EU: ")       {printf("%s: ",eu);}
("ele: "|"Ele: "|"ELE: ")    {printf("%s: ",ele);}
%%

int main(int argc, char* argv[]){
    yylex();
    return 0;
}