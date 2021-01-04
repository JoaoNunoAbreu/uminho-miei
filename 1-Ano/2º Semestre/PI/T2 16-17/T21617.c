#include <stdio.h>
#include <stdlib.h>

// ---------1---------

typedef struct slist {
    int  valor;
    struct  slist  *prox;
} *LInt;

typedef struct nodo {
    int  valor;
    struct  nodo  *esq,  *dir;
} *ABin;


// a)

int limpaEspacos (char s[]) {
    
    int i,j = 0,conta = 0;
    
    for (i = 0; s[i] != '\0'; i++) {
        
        if (s[i] != ' ' || s[i+1] != ' ') {
            s[j++] = s[i];
            conta++;
        }
        
    }
    s[j] = '\0';
    
    return conta;
}

// b)

void transposta (int N, float m [N][N]) {

    int l,c;
    int temp;

    for (l = 0; l < N; l++)
        for (c = l; c < N; c++) {

            temp = m[l][c];
            m[l][c] = m[c][l];
            m[c][l] = temp;
        }

}

// c)

LInt cloneL (LInt l) {

    LInt x = NULL, *pt = &x;

    while (l) {

        *pt = malloc(sizeof(struct slist));
        (*pt) -> valor = l -> valor;
        (*pt) -> prox = NULL;
        pt = &((*pt) -> prox);
        l = l -> prox;
    }
    return x;
}

// d)

int nivelV (ABin a, int n, int v[]) {
    
    int i = 0;
    
    if (a != NULL) {
        
        if (n == 1) {
            v[i] = a -> valor;
            i++;
        }
        else {
            i = nivelV(a -> esq,n-1,v);
            i += nivelV(a -> dir,n-1,v+i);
        }
    }
    return i;
}

// e)

void removeMaiorA (ABin *a) {
    
    if (*a) {
        
        while (*a && (*a) -> dir) a = &((*a) -> dir);
        
        if ((*a) -> dir == NULL) (*a) = (*a) -> esq;
        else (*a) -> dir = NULL;
        
    }
}

// -----------------2---------------------

#define MAXc 3

typedef struct chunk {
    int vs [MAXc];
    struct chunk *prox;

} *CList;

typedef struct stackC {
    CList valores;
    int sp;

} StackC;

// 1.

int main() {

    int i = 0;

    return 0;

}