%{
  int yylex();
  void yyerror(char*);
  #include <stdio.h>
%}
%token INICIO FIM pal num

%%
Frase : INICIO Lista FIM  {printf("ok\n");}
      ;
Lista : Elem
      | Elem ',' Lista
      ;
Elem : pal
     | num
     ;
%%

int main(){
  yyparse();
  return 0;
}

void yyerror(char* s){
  extern int yylineno;
  extern char* yytext;
  fprintf(stderr,"Linha %d: %s (%s)\n",yylineno,s,yytext);    
}