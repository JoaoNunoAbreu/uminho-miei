#include <stdio.h>
#include <stdlib.h>
#include <math.h>


#define NV 5
#define BRANCO   0 // não visitado
#define CINZENTO 1 // em visita
#define PRETO    2 // visitado

typedef struct aresta {

    int destino, peso;
    struct aresta *prox;

} *Grafo [NV];

//--------------------------------------------------------------------- Teste 14/15 -------------------------------------------------------------

// Parte B

int coberturaOK(Grafo g, int N, int t[]) {

    int i,r = 1;
    Elist it;

    for(i = 0; i < N && r == 1; i++)
        for(it = g[i]; it; it = it->next)
            r = existeNoGrafo(g,t[i],t[it->dest]);

    return r;
}

int existeNoGrafo(Grafo g, int or, int dest) {

    EList it; int r = 1;
    for(it = g[or]; it && it->destino != dest; it = it -> next);

    if(it == NULL) r = 0;

    return r;

}

int coberturaRec(Grafo g, int N, int i, int t[]) {

    int x;

    if(i == N) return coberturaOK(g,N,t);
    else for(x = 0;x < N; x++){
        t[x] = i;
        if(coberturaRec(g,N,i+1,t)) return 1;

    }

    return 0;

}

int cobertura(Grafo g, int N) {

    int t[N];
    int i;
    for(i = 0; i < N; i++) t[i] = 0;

    return (coberturaRec(g,N,0,t));

}


//--------------------------------------------------------------------- Teste 15/16 -------------------------------------------------------------

// Parte A

// 1.

/*

50, 20, 42, 13, 2, 12, 36
50, 20, 12, 13, 2, 42, 36
etc
*/

// 2.

int bipartido(Graph g, int V1[]) {

    int q[N];
    Elist it;
    int r = 0,v;
    int inicio, fim; inicio = fim = 0;

    for(int i = 0; i < N; i++) {
        V1[i] = 0;
    }

    q[fim++] = 0;
    V1[0] = 1;

    while(inicio != fim && r == 0) {

        v = q[inicio++];

        for(it = g[v];it;it->next)
            if(V1[it->destino] == 1 && V1[v] == 1)r = 1;
            if(V1[it->destino] == 0 && v1[v] == 0){    
                q[fim++] = it->destino;
                V1[it->destino] = 1;        
            }


    }
    return (!r);
}


// Parte B

int particaoOK(int v[], int N, int t[], int half) {

    int i,r;

    for(i = 0; i < N; i++) 
        if(t[i]) r += v[i];
        
    if(r == half) return 1;
    return 0;

}

int particaoRec(int v[], int N, int i, int t[], int half) {

    int x;

    if (i == N) return particaoOK(v,N,t,half);
    else for(x = 0; x <= 1; x++) { // t[] apenas terá valores 0 e 1 significando pertencer ao não ao conjunto Y cada índice do array
        t[i] = x;
        if(particaoRec(v,N,i+1,t,half)) return 1;
    }

    return 0;

}

int particao(int v[], int N) {

    int t[N];
    int i;
    int half = 0;

    for(i = 0; i < N; i++) { 
        t[i] = 0;
        half += v[i]; // Sum do array

    }
    half = half / 2;

    return (particaoRec(v,N,0,t,half));

}


//--------------------------------------------------------------------- Teste 16/17 -------------------------------------------------------------

// 1.

/*
0   1  2  3  4  5  6  7  8  9  10  11  12 
260 40 80 54 65          60    140
          1  4  
*/


// 2.

#define LEFT 1
#define BAL 0
#define RIGHT -1

typedef struct avl {

    int value;
    int bal;
    struct avl *left, *right;

} *AVL;

// (a)

AVL maisProfundo (AVL a) {

    if (a == NULL) return NULL;
    while (a->left || a->right) {
        if (a->bal == LEFT) a = a->left;
        else a = a->right;
    }
    return a;
}

/* (b)


              20                               
            /    \                       
          10      40                     
            \    /  \                    
            15  30   50              
               /                           
              29                            

*/


// 3.

#define N 5
typedef struct edge {
    int dest;
    struct edge *next;
} *Adjlist;

typedef AdjList Graph[N];

#define WHITE 0
#define RED   1
#define GREEN 2

// (a)

int bicolor(Graph g, int color[]){

    Queue q; init_queue(q);
    int v; r = 1;

    for (v = 0; v < N; v++) color[v] = WHITE; // Θ(V)

    enqueue(q,0); color[0] = RED;

    while (!is_empty(q) && r == 1) { // Θ(V+E)
        v = dequeue(q);

        for(it = g[v]; it; it = it -> prox)
            if(color[it->destino] == WHITE) {
                if(color[v] == RED) color[it->destino] = GREEN;
                else color[it->destino] = RED;
                enqueue(q,it-destino);
            }
            else if(color[it->destino] == color[v]) r = 0;
    }
    return r; 
}


// (b)

/*

    Melhor caso : T(N) = Θ(V)
    Pior caso : T(N) = Θ(V+E)

*/


//Parte B

// 1

int colorOK(Grafo g, int color[]) {

    int r = 1;
    int i;
    struct aresta *it;

    for(i = 0; i < NV; i++)
        for(it = g[i]; it; it = it -> prox)
            if (color[it -> destino] == color[i]) r = 0;

    return r;
}

// 2

int k_colorRec(Grafo g,int color[], int i, int k) {

    int x;

    if(i == N) return colorOK(g,color);
    else for(x = 0; x < k; x++) {
            color[i] = x;
            if(k_colorRec(g,color,i+1,k)) return 1;
    }
    return 0;
}

int k_color(Grafo g, int k) {

    int color[N];
    for(int i = 0; i < N; i++)
        color[i] = 0;
    return (k_colorRec(g,color,0,k));

}


//--------------------------------------------------------------------- Teste 17/18 -------------------------------------------------------------


// (1)


/* 

3 8 25 9 24 32 37 110 120 31
8 9 25 31 24 32 37 110 120
9 24 25 31 120 32 37 110
24 31 25 110 120 32 37
25 31 32 110 120 37
31 37 32 110 120
32 37 120 110
37 110 120
110 120
120
Vazio 

*/

// (2)

#define HSIZE 5
#define LIVRE 0
#define OCUPADA 1

typedef struct bucket {

    int status;
    int key; int info;

}Bucket;

typedef Bucket HashTable[HSIZE];

int hash(int key, int size) {

    return (key%size);

}

int update(HashTable h, int key, int info) {

    int r,p = hash(key,HSIZE);
    int tent = HSIZE;

    while(h[p].status == OCUPADA && h[p].key != key && tent > 0) {
        p = (p+1)%HSIZE;
        tent--;
    }
    if(tent == 0) r = 2; // Chave não encontrada
    else 
        if(h[p].key == key) {
            h[p].info = info; // Existe chave
            r = 0;
        }
        else {
            h[p].status = OCUPADA;
            h[p].key = key;
            h[p].info = info;
            r = 1; // Insere chave
        }

    return r;
}

int repetidos(int v[], int tam, HashTable h) {

    int r = -1;

    for(int i = 0; i < tam && r == -1; i++)
        r = update(h,v[i],v[i]);

    return r;

}

// (3)

int travessiaBF(Grafo g, int o,int ant[]) {

    int orla[NV],cor[NV];
    int inicio,fim,r = 0; inicio = fim = 0;
    struct aresta *it;

    for(int i = 0; i < NV; i++){ant[i] = -1; cor[i] = BRANCO;}

    orla[fim++] = o;
    cor[o] = CINZENTO;

    while(inicio != fim) {

        o = orla[inicio++];
        cor[o] = PRETO;
        for(it = g[o]; it != NULL; it = it -> prox)
            if(cor[it->destino] == BRANCO) {
                ant[it->destino] = o;
                orla[fim++] = it -> destino;
                cor[o] = CINZENTO;
            }
            else r = 1; // significa que encontrou um vértice a preto ou cinzento por outro caminho diferente, ou seja, não é árvore.

    }
    return r;
}

int inDegree(Grafo g, int d) {

    int i; struct aresta *it;
    int count = 0;

    for(i = 0; i < NV; i++)
        for(it = g[i]; it != NULL; it = it -> prox)
            if(it -> destino == d) count++;

    return count;

}

int isTree (Grafo g) {

    int notTree = 1, contaRaizes = 0;
    int *ant,i;
    int origem = -1;

    for(i = 0; i < NV && origem == -1; i++)     // Descobre origem do grafo
        if (inDegree(g,i) == 0) origem = i;

    if (travessiaBF(g,origem,ant)) notTree = 0; // Se a flag ativar na travessia -> não é árvore.

    for(i = 0; i < NV && notTree == 1; i++)     

        if(ant[i] == -1) contaRaizes++;

    if(contaRaizes > 1) notTree = 0;            // Se houver mais do que uma raíz -> não é árvore.

    return (notTree);

}

// (4)

int isHomomorphic(Grafo g, Grafo h, int t[], int N) {

    int i, r = 1;
    Elist it;

    for(i = 0; i < N && r == 1; i++)
        for(it = g[i]; it && r == 1; it = it -> prox) 
            r = existeNoGrafo(h,t[i],t[it->destino]);
        

    return r;
}

int existeNoGrafo(Grafo g, int or, int dest) {

    EList it; int r = 1;
    for(it = g[or]; it && it->destino != dest; it = it -> next);

    if(it == NULL) r = 0;

    return r;

}

int homomorphicRec(Grafo g, Grafo h, int N, int i, int t[]) {

    int x;

    if(i == N) return (isHomomorphic(g,h,t,N));
    else 
        for(x = 0; x < N; x++){
            t[i] = x;
            if(homomorphicRec(g,h,N,i+1,t)) return 1;
        }
    return 0;
}

int homomorphic(Grafo a, Grafo b, int N) {

    int t[N], i;

    for(i = 0; i < N; i++) t[i] = 0;

    return (homomorphicRec(a,b,N,0,t));

}

int main(){}
