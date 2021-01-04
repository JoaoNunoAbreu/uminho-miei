#include <stdio.h>
#include <ctype.h>


//-----------1.------------

/*int take (int n, LInt *l){
    
    LInt pt = *l; LInt temp; LInt ant = NULL;
    int conta;
    
    for (conta = 0; pt != NULL && conta < n; conta++, pt = pt -> prox)
        ant = pt;
    
    while (pt != NULL) {
        temp = pt;
        free(temp);
        pt = pt -> prox;
    }
    if (ant != NULL) ant -> prox = NULL;
    return conta;
}

//-----------2.--------------

int quantosMaiores (ABin a, int x) {
    
    int conta = 0;
    
    if (a != NULL) {
        
        if (a -> valor > x)
            conta = 1 + quantosMaiores(a -> esq,x) + quantosMaiores(a -> dir,x);
        else conta = quantosMaiores (a -> dir,x);
        
    }
    return conta;
}*/


//-----------3.--------------

int contaPal (char s[]) {
    
    int i, conta = 0;
    int dentro = 0;
    
    for(i = 0; s[i] != '\0';i++) {
        
        if (isspace(s[i])) dentro = 0;
        else if (dentro == 0) {
            conta++; dentro = 1;
        }
        
    }
    return conta;
}

int tamanho (char s[]) {

    int i;

    for (i = 0; s[i] != '\0'; i++);

    return i;

}

int maiorPal (char s[]) {

    int conta = 0;
    int maior = 0;
    int i;

    for(i = 0; s[i] != '\0'; i++)
        if (!isspace(s[i])) {
            conta++;
            if (conta > maior) maior = conta;
        }
        else conta = 0;

    return maior;

}

int wc (char *filename) {

    int linhas = 0;
    int palavras = 0;
    int caracteres = 0;
    int maior = 0, conta = 0;
    char str[100];

    FILE *file = fopen(filename,"r");

    while(fgets(str,100, file) != NULL) {

        linhas++;
        palavras += contaPal(str);
        caracteres += tamanho(str);
        conta = maiorPal(str);
        if (conta > maior) maior = conta;
    }

    printf("Linhas: %d\n", linhas);
    printf("Palavras: %d\n", palavras);
    printf("Caracteres: %d\n", caracteres);

    return maior;

}

//-----------4.--------------

typedef struct bloco {
    int  quantos; //  elementos  ocupados
    int  valores[NUM];
    struct  bloco  *prox;
}  Bloco,  *LBoco;

// a)

int elemArray (char s[], int N, int x) {

    int r = 0,i;

    for(i = 0; i < N && r == 0; i++)
        if (s[i] == x) r = 1;

    return r;

}

int pertence (LBloco l, int x) {

    int r = 0;

    while(l && r == 0) {

        if (elemArray(l -> valores,l -> N, x)) r = 1;
        l = l -> prox;
    }
    return r;
}

// b)


int acrescenta (LBloco *l, int n) {


    
}

//--------- Main ------------

int main() {

    int i = wc("filename.txt");

    printf("A maior palavra tem: %d elementos.\n",i);

    return 0;

}
