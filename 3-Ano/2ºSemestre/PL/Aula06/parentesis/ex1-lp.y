%{
  int yylex();
  void yyerror(char*);
  #include <stdio.h>
%}

%%

texto : lp {printf("ok\n");}

lp    : bpe lp 
      | 
      ;

bpe   : '(' lp ')';

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

// expreg ---> gramáticas
  
  // a*             A : A a | ;
  // a+             A : A a | a;
  // (a | b)* c     A : Bc; 
  //                B : aB | bB | ;
  //                ou
  //                A : a A | b A | c;