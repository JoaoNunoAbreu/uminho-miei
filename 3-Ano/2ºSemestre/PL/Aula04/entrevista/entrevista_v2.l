%option noyywrap yylineno
%x R E

%%
    char *reporter, *entrevistado;

<*>E:                       {BEGIN E;}
<*>R:                       {BEGIN R;}
<*>\n                       {printf("%s",yytext);BEGIN 0;}
R=.*                        {reporter = strdup(yytext+2);}
E=.*                        {entrevistado = strdup(yytext+2);}
<E>[Ee]u/[^a-z]             {printf("%s=(%s)",yytext,entrevistado);}
<E>[Tt]u/[^a-z]             {printf("%s=(%s)",yytext,reporter);}
<R>[Ee]u/[^a-z]             {printf("%s=(%s)",yytext,reporter);}
<R>[Tt]u/[^a-z]             {printf("%s=(%s)",yytext,entrevistado);}

%%

int main(int argc, char* argv[]){
    yylex();
    return 0;
}