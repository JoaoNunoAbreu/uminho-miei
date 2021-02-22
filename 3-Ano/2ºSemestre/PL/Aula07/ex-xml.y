%{
  int yylex();
  void yyerror(char*);
  #include <stdio.h>
  #include <string.h>
  char* tag;
  typedef struct nodo {
    char* at;
    struct nodo *next;
  } *LIST;
  LIST push(LIST l, char* id);
%}

%union {char *s; struct nodo *l;}

%token ID STR TXT AB BF
%type <s> ID STR TXT
%type <l> atribs

%%
  // AB = '</'  BF = '/>'

xml           : elem
              ;

elem          : abrelem filhos fechaelem 
              | abrefechaelem
              ;

filhos        : TXT filhos            // (TXT | elem)*
              | elem filhos
              | 
              ;

abrelem       : '<' ID atribs '>'     {for(LIST l = $3; l ; l = l->next)
                                          printf("%s -> %s;\n",$2,l->at);
                                      }
              ;

abrefechaelem : '<' ID atribs BF

fechaelem     : AB ID '>' 
              ;

atribs        :                       {$$ = NULL;} // (ID=STR)*
              | ID '=' STR atribs     {$$ = push($4,$1);}
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

LIST push(LIST l, char* id){
  LIST aux = malloc(sizeof(struct nodo));
  aux->at = strdup(id);
  aux->next = l;
  return aux;
}