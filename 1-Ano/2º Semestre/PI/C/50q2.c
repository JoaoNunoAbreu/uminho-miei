#include <stdio.h>

//--------------------1.-------------------------

int length (LInt l){
    
    int conta = 0;
    
    while (l) {conta++; l = l -> prox;}
    
    return conta;
}

//--------------------2.-------------------------

void freeL (LInt l){
    
    LInt * l1;
    while (l) {   
        
        l1 = &(l->prox);
        free (l);
        l = *l1;
    }
}

//--------------------3.-------------------------

void imprimeL (LInt x){

    while(x){

        printf("%d\n",x->valor);
        x = x -> prox;
    }

}

//--------------------4.-------------------------

LInt reverseL (LInt l){
    
    LInt r = NULL, t;
    
    while (l) {
        t = l;
        l = l -> prox;
        t -> prox = r;
        r = t;
    }
    return r;
}

//--------------------5.-------------------------

void insertOrd (LInt *el, int x){
    
    LInt ant = NULL ,pt = *el,n;
    
    while (pt && pt -> valor < x) {
        ant = pt;
        pt = pt -> prox;
    }
    
    n = malloc (sizeof (struct lligada));
    n -> valor = x;
    n -> prox = pt;
    if (ant == NULL) *el = n; else ant -> prox = n;
}

//--------------------6.-------------------------

int removeOneOrd (LInt *l, int x){
    
    int r = 1;
    LInt pt = *l;
    LInt ant = NULL;
    
    while (pt) {
        
        if (pt -> valor == x) {
            if (ant != NULL) ant -> prox = pt -> prox;
            else *l = pt -> prox;
            r = 0;
        }
        ant = pt;
        pt = pt -> prox;
    }
    return r;
}

//--------------------7.-------------------------

void merge (LInt *r, LInt l1, LInt l2){
    
    while (l1 && l2) {
        
        if (l1 -> valor < l2 -> valor) {*r = l1; l1 = l1 -> prox;}
        else {*r = l2 ;l2 = l2 -> prox;}
        r = &((*r) -> prox);
    }
    if (l1 == NULL) *r = l2; 
    if (l2 == NULL) *r = l1; 
    
}

//--------------------8.-------------------------

void splitQS (LInt l, int x, LInt *mx, LInt *Mx){
    
    
    while (l) {
        
        if (l -> valor < x) {
            *mx = l;
            mx = &((*mx) -> prox);
        }
        else {
            *Mx = l;
            Mx = &((*Mx) -> prox);
        }
        l = l -> prox;
    }
    *mx = NULL;
    *Mx = NULL;
}

//--------------------9.-------------------------

int length (LInt l){
    
    int conta = 0;
    
    while (l) {conta++; l = l -> prox;}
    
    return conta;
}

LInt parteAmeio (LInt *l){
    
    int i, meio = length(*l)/2 - 1;
    LInt y = NULL;
    LInt el = *l;
    
    for (i = 0; i < meio; i++, el = el -> prox);
    
    if (meio >= 0) {
        y = *l;
        *l = el -> prox;
        el -> prox = NULL;
    }
    return y;
}

//--------------------10.------------------------

int removeAll (LInt *l, int x){
    
    int r = 0;
    LInt pt = *l,ant = NULL;

    while (pt){
        
        if(pt -> valor == x){
            if (pt == *l) *l = (*l)->prox;
            else ant -> prox = pt -> prox;
            r++;
        } 
        else ant=pt;
        pt = pt -> prox;
    }
    return r;
}

//--------------------11.------------------------

int removeAll (LInt *l, int x){
    
    int r = 0;
    LInt pt = *l,ant = NULL;

    while (pt){
        
        if(pt -> valor == x){
            if (pt == *l) *l = (*l)->prox;
            else ant -> prox = pt -> prox;
            r++;
        } 
        else ant=pt;
        pt = pt -> prox;
    }
    return r;
}

int repetidos (LInt l, int x){
    
    int r = 0;
    
    while (l != NULL && r == 0) {
        if (l -> valor == x) r = 1;
        l = l -> prox;
    }   
    
    return r;
}

int removeDups (LInt *l){
    
    LInt el = *l;
    int r = 0;
    
    while (el != NULL) {
        if (repetidos (el,el -> valor) == 1) r = removeAll (&(el -> prox),el -> valor);
        el = el -> prox;
    }
    return r;
}

//--------------------12.------------------------

int maiorElem (LInt l) {
    
    int maior = 0;
    
    while (l) {
        
        if (l -> valor > maior) maior = l -> valor;
        
        l = l -> prox;
    }
    return maior;   
}

int removeMaiorL (LInt *l){
    
    LInt ant = NULL, pt = *l;
    int maior = maiorElem(*l);
    int flag = 0;
    
    while (pt && flag == 0) {
        
        if (pt -> valor == maior) {
            
            if (ant != NULL) ant -> prox = pt -> prox;
            else *l = pt -> prox;
            flag = 1;
        }
        ant = pt;
        pt = pt -> prox;
    }
    return maior;
}

//--------------------13.------------------------

void init (LInt *l){
    
    while(*l && (*l) -> prox != NULL) l = &((*l) -> prox);
    *l = NULL;
    free(*l);
}

//--------------------14.------------------------

void appendL (LInt *l, int x){
    
    while (*l != NULL) l = &((*l) -> prox);
    
    *l = malloc (sizeof (struct lligada));
    (*l) -> valor = x;
    (*l) -> prox = NULL;
}

// OU 

void appendL (LInt *l, int x){
    
    LInt new;
    
    while (*l && (*l) -> prox != NULL) l = &((*l) -> prox);
    
    new = malloc(sizeof(struct lligada));
    new -> valor = x;
    new -> prox = NULL;
    
    if (*l != NULL) (*l) -> prox = new;
    else *l = new;
}

//--------------------15.------------------------

void concatL (LInt *a, LInt b){
    
    while (*a && (*a) -> prox) a = &((*a) -> prox);
    
    if (*a) (*a) -> prox = b;
    else *a = b;
    
}

//--------------------16.------------------------

LInt cloneL (LInt l){

    LInt guarda ,*pt = &guarda;

    while (l != NULL) {

        *pt = malloc(sizeof(struct lligada));
        (*pt) -> valor = l -> valor;
        pt = &((*pt) -> prox);
        l = l -> prox;
        
    }
    return (guarda);
}

//--------------------17.------------------------

LInt cloneL (LInt l) {
    
    LInt x = NULL,*l2 = &x;
    
    while (l) {
        *l2 = malloc(sizeof(struct lligada));
        (*l2) -> valor = l -> valor;
        (*l2) -> prox = NULL;
        l2 = &((*l2) -> prox);
        l = l -> prox;
    }
    return x;
}

LInt reverseL (LInt l){
    
    LInt l1 = NULL, l2;
    
    while (l) {
        l2 = l;
        l = l -> prox;
        l2 -> prox = l1;
        l1 = l2;
    }
    return l1;
}

LInt cloneRev (LInt l){
    
    return (cloneL(reverseL(l)));
    
}

//--------------------18.------------------------

int maximo (LInt l){
    
    int maior = 0;
    
    for (; l ; l = l -> prox) 
        if (l -> valor > maior) maior = l -> valor;
    
    return maior;
}

//--------------------19.------------------------

int take (int n, LInt *l){
    
    int i;
    LInt ant = NULL;
    
    for (i = 0; i < n && *l; i++) {
        ant = *l;
        l = &((*l) -> prox);
    }
    if (ant != NULL) ant -> prox = NULL;
    
    return i;
}

//--------------------20.------------------------

int drop (int n, LInt *l){
    
    int i;
    LInt temp;
    
    for (i = 0; *l && i < n; i++) {
        temp = (*l);
        (*l) = (*l) -> prox;
        free(temp);
    }
    return i;
}

//--------------------21.------------------------

LInt NForward (LInt l, int N){
    
    int i;
    
    for(i = 0; i < N; i++, l = l -> prox);
    
    return l;
}

//--------------------22.------------------------

int listToArray (LInt l, int v[], int N){
    
    int i;
    
    for (i = 0; l != NULL && i < N; i++, l = l -> prox) 
        v[i] = l -> valor;
    
    return i;
}

//--------------------23.------------------------

LInt arrayToList (int v[], int N){
    
    LInt r = NULL, *l = &r;
    int i;
    
    for (i = 0; i < N; i++) {
        *l = malloc(sizeof(struct lligada));
        (*l) -> valor = v[i];
        l = &((*l) -> prox);
    }
    return r;
}

//--------------------24.------------------------

LInt somasAcL (LInt l) {
    
    LInt x = NULL, *el = &x;
    int sum = 0;
    
    while (l) {
        *el = malloc(sizeof(struct lligada));
        (*el) -> valor = l -> valor + sum;
        (*el) -> prox = NULL;
        el = &((*el) -> prox);
        sum += l -> valor;
        l = l -> prox;
    }
    return x;
}

//--------------------25.------------------------

int removeAll (LInt *l, int x){
    
    int conta = 0;
    LInt pt = *l, ant = NULL;
    
    while (pt) {
        
        if (pt -> valor == x){
            if (ant != NULL) ant -> prox = pt -> prox;
            else *l = pt -> prox;
            conta++;
        }
        else ant = pt;
        pt = pt -> prox;
    }
    return conta;
}

int repete (LInt l, int x) {
    
    int r = 0;
    
    while (l) {
        if (l -> valor == x) r = 1;
        l = l -> prox;
    }
    
    return r;
}

void remreps (LInt l){
    
    while (l) {
        if (repete(l,l -> valor))
            removeAll (&(l -> prox),l -> valor);
        l = l -> prox;
    }
    
}

//--------------------26.------------------------

LInt rotateL (LInt l){
    
    LInt ultimo = l;
    LInt x = NULL;
    
    if (l != NULL && l -> prox != NULL) x = l -> prox;
    else return l;
    
    while (l && l -> prox) l = l -> prox;
    
    if (l != NULL) l -> prox = ultimo;
    
    if (ultimo != NULL) ultimo -> prox = NULL;
    
    return x;
}

//--------------------27.------------------------

LInt parte (LInt l){
    
    int par = 0;
    LInt x = NULL, *lpares = &x;
    LInt *limpares = &l;
    
    while (*limpares) {
        
        if (par == 1) {
            *lpares = malloc(sizeof(struct lligada));
            (*lpares) -> valor = (*limpares) -> valor;
            (*lpares) -> prox = NULL;
            lpares = &((*lpares) -> prox);
            *limpares = (*limpares) -> prox;
            par = 0;
        }
        else {
            impares = &((*limpares) -> prox);
            par = 1;
        }
    }
    return x;
}

//--------------------28.------------------------

int altura (ABin a){
    
    int r = -1,e,d;
    
    if (a != NULL) {
        
        e = altura(a -> esq);
        d = altura(a -> dir);
        if (e > d) r = 1 + e;
        else r = 1 + d;
    }
    return r;
}

//--------------------29.------------------------

ABin cloneAB (ABin a) {
    
    ABin x = NULL;
    
    if (a != NULL) {
        
        x = malloc(sizeof(struct nodo));
        x -> valor = a -> valor;
        x -> esq = cloneAB(a -> esq);
        x -> dir = cloneAB(a -> dir);
    }
    return x;
}

//--------------------30.------------------------

void mirror (ABin *a) {
    
    ABin temp;
    
    if (*a != NULL) {
        
        temp = (*a) -> esq;
        (*a) -> esq = (*a) -> dir;
        (*a) -> dir = temp;
        
        mirror(&( (*a) -> esq) );
        mirror(&( (*a) -> dir) );
    }
}

//--------------------31.------------------------

void inorder (ABin a, LInt * l) {
    
    LInt le,ld;
    LInt n;
    
    if (a == NULL) *l = NULL;
    else {
        inorder(a -> esq,&le);
        inorder(a -> dir,&ld);
        n = malloc(sizeof(struct lligada));
        n -> valor = a -> valor;
        n -> prox = ld;
        if (le != NULL) {
            *l = le;
            while (le -> prox) le = le -> prox;
            le -> prox = n;
        }
        else *l = n;
    }
}

//--------------------32.------------------------

void preorder (ABin a, LInt * l) {
    
    LInt le,ld;
    LInt n;
    
    if (a == NULL) *l = NULL;
    else {
        
        preorder(a->esq,&le);
        preorder(a->dir,&ld);
        
        n = malloc(sizeof(struct lligada));
        n -> valor = a -> valor;
        n -> prox = le;
        *l = n;
        if (le != NULL) {
            while (le -> prox) le = le -> prox;
            le -> prox = ld;
        }
        else n -> prox = ld;
    }
}

//--------------------33.------------------------

void posorder (ABin a, LInt * l) {

    LInt le,ld;
    LInt new;

    if (a == NULL) *l = NULL;
    else {
        
        posorder (a -> esq, &le);
        posorder (a -> dir, &ld);
        
        new = malloc(sizeof(struct lligada));
        new -> valor = a -> valor;
        new -> prox = NULL;
        
        if (le != NULL) {
            *l = le;
            while (le -> prox) le = le -> prox;
            le -> prox = ld;
        }
        else *l = ld;
        if (ld != NULL) {
            while (ld -> prox) ld = ld -> prox;
            ld -> prox = new;
        }
        else if (le != NULL) le -> prox = new;
             else (*l) = new;
    }
}

//--------------------34.------------------------

int depth (ABin a, int x) {
    
    int r = -1;
    int e,d;
    
    if (a != NULL) {
        
        if (a -> valor == x) r = 1;
        else {
            e = depth(a -> esq,x);
            d = depth(a -> dir,x);
            
            if (e > 0 && d > 0)
                if (e > d) r = 1 + d;
                else r = 1 + e;
            if (e > 0 && d < 0) r = 1 + e;
            if (e < 0 && d > 0) r = 1 + d;
        }
    }
    return r;
}

//--------------------35.------------------------

int freeAB (ABin a) {
    
    int conta = 0;
    
    if (a != NULL) {
        
        conta =  freeAB(a -> esq);
        conta += freeAB(a -> dir);
        free(a);
        conta++;
    }
    return conta;
}

//--------------------36.------------------------

int pruneAB (ABin *a, int l) {

    int r = 0;
    if (*a != NULL){
         
        r += pruneAB(&((*a)->esq),l-1);
        r += pruneAB(&((*a)->dir),l-1);
        if (l <= 0) {
            free(*a);
            *a = NULL;
            r++;
        }
    }
    return r;
}

//--------------------37.------------------------

int iguaisAB (ABin a, ABin b) {
    
    int r = 0;
    
    if (a == NULL && b == NULL) r = 1;
    if (a != NULL && b != NULL) 
        r = (a -> valor == b -> valor) && iguaisAB(a -> esq,b -> esq) && iguaisAB (a -> dir, b -> dir);

    return r;    
}

//--------------------38.------------------------

LInt nivelL (ABin a, int n) {
    
    LInt l1 = NULL,l2,pt;
    
    if (a != NULL) 
        
        if (n == 1) {
            l1 = malloc(sizeof(struct lligada));
            l1 -> valor = a -> valor;
            l1 -> prox = NULL;
        }
        else {
            
            l1 = nivelL (a -> esq, n-1);
            l2 = nivelL (a -> dir, n-1);
            
            if (l1 != NULL) {
                pt = l1;
                for(;pt -> prox != NULL; pt = pt -> prox);
                pt -> prox = l2;
            }
            else l1 = l2;
        }
    return l1;
}

//--------------------39.------------------------

int nivelV (ABin a, int n, int v[]) {
    
    int i = 0;
    
    if (a != NULL) {
        
        if (n == 1) {
            v[i] = a -> valor;
            i++;
        }
        else {
            i  = nivelV(a -> esq,n-1,v);
            i += nivelV(a -> dir,n-1,v+i);
        }
        
    }
    return i;
}

//--------------------40.------------------------

int dumpAbin (ABin a, int v[], int N) {
    
    int i = 0;
    
    if (a != NULL) {
        
        i = dumpAbin (a -> esq,v,N);
        if (i < N) {
            v[i] = a -> valor; i++;
        }
        i += dumpAbin (a -> dir,v+i,N-i);
    }
    return i;
}

//--------------------41.------------------------

int somas (ABin a) {
    
    int r = 0;
    
    if (a != NULL) 
        r = a -> valor + somas (a -> esq) + somas (a -> dir);
    
    return r;
}

ABin somasAcA (ABin a) {
    
    ABin x = NULL,e,d;
    
    if (a != NULL) {
        
        x = malloc(sizeof(struct nodo));
        x -> valor = somas(a);
        x -> esq = somasAcA (a -> esq);
        x -> dir = somasAcA (a -> dir);
        
    }
    return x;
}

//--------------------42.------------------------

int contaFolhas (ABin a) {
    
    int conta = 0;
    
    if (a != NULL) {
        if (a -> esq == NULL && a -> dir == NULL ) conta++;
        else conta = contaFolhas (a -> esq) + contaFolhas (a -> dir);
    }
    return conta;
}

//--------------------43.------------------------

void mirror (ABin *a) {
    
    ABin temp;
    
    if (*a != NULL) {
        
        temp = (*a) -> dir;
        (*a) -> dir = (*a) -> esq;
        (*a) -> esq = temp;
        
        mirror (&((*a) -> esq));
        mirror (&((*a) -> dir));
        
    }
    
}

ABin cloneAB (ABin a) {
    
    ABin x = NULL;
    
    if (a != NULL) {
        
        x = malloc(sizeof(struct nodo));
        x -> valor = a -> valor;
        x -> esq = cloneAB (a -> esq);
        x -> dir = cloneAB (a -> dir);
    }
    return x;
}
 
ABin cloneMirror (ABin a) {
    
    mirror(&a);
    
    return (cloneAB(a));
}

//--------------------44.------------------------

int addOrd (ABin *a, int x) {
    
    int r = 0;
    
    while (*a != NULL && r == 0) {
        
        if ((*a) -> valor == x) r = 1;
        else if ((*a) -> valor >  x) a = &((*a) -> esq);
        else if ((*a) -> valor <  x) a = &((*a) -> dir);
    }
    if (r == 0) {
        *a = malloc(sizeof(struct nodo));
        (*a) -> valor = x;
        (*a) -> esq = (*a)-> dir = NULL;
    }
    return r;
}

//--------------------45.------------------------

int lookupAB (ABin a, int x) {
    
    int r = 0;
    
    while (a != NULL && r == 0) {
        
        if (a -> valor == x) r = 1;
        else if (a -> valor > x) a = a -> esq;
        else if (a -> valor < x) a = a -> dir;
    }
    return r;
}

//--------------------46.------------------------

int depthOrd (ABin a, int x) {
    
    int r = -1;
    int conta = 1;
    
    while (a && r == -1) {
        
        if (a -> valor == x) r = conta;
        else if (a -> valor > x) a = a -> esq;
        else if (a -> valor < x) a = a -> dir;
        conta++;
        
    }
    return r;
}

//--------------------47.------------------------

int maiorAB (ABin a) {
    
    while (a && a -> dir != NULL) a = a -> dir;
    
    return (a -> valor);
    
}

//--------------------48.------------------------

void removeMaiorA (ABin *a) {
    
    while(*a && (*a) -> dir) a = &((*a) -> dir);
    
    if (*a != NULL && (*a) -> dir == NULL) *a = (*a) -> esq;
    else (*a) -> dir = NULL;
}

//--------------------49.------------------------

int quantosMaiores (ABin a, int x) {
    
    int conta = 0;
    
    if (a != NULL) {
        
        if (a -> valor > x)
            conta = 1 + quantosMaiores(a -> esq,x) + quantosMaiores(a -> dir,x);
        else conta = quantosMaiores (a -> dir,x);
        
    }
    return conta;
}

//--------------------50.------------------------

void insertOrd (ABin pt,ABin * a) {
    
    while (*a != NULL) {
        
        if ((*a) -> valor > pt -> valor) a = &((*a) -> esq);
        if ((*a) -> valor < pt -> valor) a = &((*a) -> dir);

    }
    *a = pt;
    
}

void listToBTree (LInt l, ABin *a) {
    
    ABin pt;
    
    while (l != NULL) {
        
        pt = malloc(sizeof(struct nodo));
        pt-> valor = l -> valor;
        pt -> esq = pt -> dir = NULL;
     
        insertOrd(pt,a);
        l = l -> prox;
    }
    
}

//--------------------51.------------------------

int maiorAB (ABin a) {

    for (;a -> dir != NULL; a = a -> dir);
    
    return (a -> valor);
}

int menorAB (ABin a) {

    for (;a -> esq != NULL; a = a -> esq);
    
    return (a -> valor);
}

int deProcura (ABin a) {
    
    int r = 1;
    
    if (a != NULL) {
    
        if (a -> esq != NULL && maiorAB (a->esq) > a->valor) 
            r = 0; 
     
        if (a -> dir != NULL && menorAB (a->dir) < a->valor) 
            r = 0;
   
        if (!deProcura (a->esq) || !deProcura (a->dir)) 
            r = 0;
    }
    return r;
}