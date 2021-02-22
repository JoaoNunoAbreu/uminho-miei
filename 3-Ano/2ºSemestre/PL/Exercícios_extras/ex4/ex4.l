%option noyywrap yylineno

%{
    char* elems[1000];
    int lastid = 0;
    void insereSemRepetir(char* elem);
    void print();
%}

%%
    int num_aberturas, num_fechos;
    num_fechos = num_aberturas = 0;
    
\<\/[^>]+>          {num_fechos++;}
\<[^>\/?!]+>        {
                        num_aberturas++;
                        yytext[strlen(yytext)-1] = '\0';
                        insereSemRepetir(yytext+1);
                    }
<<EOF>>             {
                        if(num_aberturas != num_fechos) 
                        printf("ERRO! %d != %d\n",num_aberturas,num_fechos); 
                        else printf("Sucesso!\nNúmero de aberturas = %d\nNúmero de fechos = %d\n",num_aberturas,num_fechos);
                        print();
                        return 0;
                    }

.|\n                {}

%%

void insereSemRepetir(char* x){
    int r = 0;
    for(int i = 0; i < lastid && r == 0; i++){
        if(!strcmp(elems[i],x)) r = 1;
    }
    if (r == 0) elems[lastid++] = strdup(x);
}

void print(){
    printf("--------- Elementos distintos de aberturas ---------\n");
    for(int i = 0; elems[i]; i++){
        printf("%s\n",elems[i]);
    }
}

int main(int argc, char* argv[]){
    yylex();
    return 0;
}