%option noyywrap
%%

         char dest[90];

\\mm                {printf("mesmo");}
\\prox              {printf("próxima");}
\\mt                {printf("muito");}
\\b                 {printf("boa");}
\\exs               {printf("exercicios");}
\\pq                {printf("porque");}
\\sp                {printf("sempre");}
\\cqd               {printf("como se queria demonstrar");}
~[a-zA-Z]+          {sprintf(dest,"in%s",yytext+1);printf("%s",dest);}
[a-zA-Z]+\/         {yytext[strlen(yytext)-1] = '\0'; sprintf(dest,"%smente",yytext);printf("%s",dest);}
~[a-zA-Z]+\/        {yytext[strlen(yytext)-1] = '\0'; sprintf(dest,"in%smente",yytext+1);printf("%s",dest);}

%%

int main(){
    yylex();
    return 0;
}

