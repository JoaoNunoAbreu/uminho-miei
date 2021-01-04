#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define N 5

// Funções úteis independentes

void printArray(int a[],int tam) {

    for(int i = 0; i < tam; i++)
        printf("%d ", a[i]);
    printf("\n");
}

// ------------------------------------------------------------ MinHeap ------------------------------------------------------------

typedef struct pqueue {

    int quantos;
    int valores[N];

}*Pqueue;

void swap (int v[], int i, int j) {
    int tmp;
    tmp = v[i];
    v[i] = v[j];
    v[j] = tmp;
}


void printMinHeapArray (Pqueue h) {
    for (int i = 0; i < N; i++) 
        printf("%d ",h -> valores[i]);
    printf("\n");
}

Pqueue newHeap () {
    Pqueue h = malloc(sizeof(Pqueue));

    h->quantos = 0;

    return h;
}

// "Puxa" um elemento para a raíz da heap
void bubbleUp(int h[], int i) {

    while(i > 0 && h[i] < h[(i-1)/2]) {
        swap(h,i,(i-1)/2);
        i = (i-1)/2;
    }

}


// "Empurra" um elemento para o nível mais profundo da heap 
void bubbleDown(int h[], int tam) {

    int f, i = 0;

    while(2*i+1 < tam) {

        f = 2*i+1;

        if (2*i+2 < tam && h[2*i+2] < h[2*i+1])
            f = 2*i+2;

        if(h[f] < h[i]) {
            swap(h,i,f);
            i = f;
        }
        else break;
    }
}

int minHeapOK (int h[]) {
    int i = 0;
    int r = 1;

    for (i = 0; 2*i+1 < N-1; i++)
        if (h[2*i+1] < h[i] || 
            (2*i+2 < N && h[2*i+2] < h[i])) {
                r = 0;
                break;
        }

    return r;
}

// Minha versão

int minHeapOK2(int h[]) {

    int r = 1;

    for(int i = 0; i < N && r == 1; i++) 
        if((2*i+1 < N && h[i] > h[2*i+1]) || (2*i+2 < N && h[i] > h[2*i+2]))
            r = 0;
    
    return r;
}

int addHeap (Pqueue h, int x) {

    int r = 0;

    if(h -> quantos == N) r = 1;
    else {
        h->valores[h->quantos] = x;
        bubbleUp(h -> valores, h->quantos);
        h->quantos++;
    }

    return r;
}

int removeHeap (Pqueue h, int *x) {

    int r = 0;

    if(h -> quantos == 0) r = 1;
    else {
        *x = h->valores[0];
        h->quantos--;
        h -> valores[0] = h -> valores[h -> quantos];
        bubbleDown(h -> valores, h -> quantos);
    } 

    return r;
}


Pqueue heapifyUp(int v[]) {

    Pqueue h = newHeap();

    for (int i = 0; i < N; i++)

        bubbleUp(v,i);
    
    for (int j = 0; j < N; j++) // Cópia do vetor v corrigido para o vetor da heap

        h -> valores[j] = v[j];

    return h;

}

void bubbleDown2(int h[], int tam,int i) {

    int f;

    while(2*i+1 < tam) {

        f = 2*i+1;

        if (2*i+2 < tam && h[2*i+2] < h[2*i+1])
            f = 2*i+2;

        if(h[f] < h[i]) {
            swap(h,i,f);
            i = f;
        }
        else break;
    }
}

void heapifyDown (int v[], int tam){

    int i;

    for (i=(tam-2)/2; i>=0; i--)
        bubbleDown2(v,tam,i);
}


// Main para minHeaps

int main() {

    Pqueue h = newHeap();
    
    int resultado = 0;

    int v[7] = {50, 20, 42, 13, 2, 12, 36};
    Pqueue teste;

    addHeap(h,3);
    addHeap(h,2);
    addHeap(h,1);
    addHeap(h,5);
    addHeap(h,4);

    //teste = heapifyDown(v);

    //printMinHeapArray(teste);

    // Testa se é minHeap

    //resultado = minHeapOK2(v);
    //if(!resultado) printf("Não é uma minHeap\n");
    //else printf("É uma minHeap\n");

    heapifyDown(v,7);
    printArray(v,7);

    return 0;

}

// ------------------------------------------------------------ HashTables ----------------------------------------------------------

#define Livre 0
#define Ocup 1
#define Apag 2

typedef int Chave;
typedef int Info;

int hash(Chave c, int tam) {

    return (c%tam);

}

// Closed adressing - Uma forma de resolver colisões é guardar em cada posição do array todos os pares
// (chave,info) que colidem nessa posição


typedef struct bucketChain {

    Chave key;
    Info info;
    struct bucketChain *next;

} *BucketChain;

typedef BucketChain HashTableChain[N];

void initTabChain(HashTableChain h){

    for(int i = 0; i < N; i++) 
        h[i] = NULL;

}

int searchChain(HashTableChain h, Chave k, Info *i) {

    int found = 0;
    int p = hash(k,N);
    BucketChain it;

    for(it = h[p]; it != NULL && it->key != k; it = it -> next);

    if(it != NULL) {*i = it -> info; found = 1;}

    return found;
}

int updateChain(HashTableChain h, Chave k, Info i){

    BucketChain it;
    int p = hash(k,N), new = 0;

    for(it = h[p]; it != NULL && it -> key != k; it = it -> next);

    if(it != NULL) it -> info = i;
    else {
        it = malloc(sizeof(struct bucketChain));
        it -> key = k;
        it -> info = i;
        it -> next = h[p];
        h[p] = it;
        new = 1;
    }

    return new;
}

int removeKey (HashTableChain h ,int k){

    int found = 0, p = hash(k,N);
    BucketChain *it,tmp;

    for(it = h+p; *it != NULL && (*it) -> key != k; it = &((*it) -> next));

    if(*it != NULL) {
        tmp = *it;       
        *it = (*it) -> next;
        free(tmp);
        found = 1;
    }

    return found;
}

/* 
Open adressing - uma outra forma de lidar com colisões consiste em manter todos os pares (chave,info)
num único array. No caso de acontecer uma colisão, é calculada uma nova posiçãoo para armazenar esse par. 
// Linear probing - No caso de querermos guardar um par numa dada posição p já ocupada, tentamos fazê-lo
na posição seguinte.
*/

#define LIVRE 0
#define OCUPADA 1
#define APAGADA 2

typedef struct bucket {

    int status;
    int key; int info;

}Bucket;

typedef Bucket HashTable[N];

void initTab(HashTable h){

    for(int i = 0; i < N; i++)
        h[i].status = LIVRE;

}

int findprobe(HashTable h, int k) {

    int p = hash(k,N);
    int tent = N;

    while(h[p].status == OCUPADA && h[p].key != k && tent > 0) {
        p = (p+1)%N;
        tent--;
    }
    if(tent == 0) p = -1;

    return p;
}

int search (HashTable h, int k, int *i){

    int found = 0, p;

    p = findprobe(h,k);

    if(p != -1 && h[p].key == k) {*i = h[p].info; found = 1;}

    return found;
}

int update(HashTable h, int k, int i){

    int r,p;

    p = findprobe(h,k);

    if (p == -1) r = 0; // tabela cheia
    else 
        if(h[p].key == k) {h[p].info = i; r = 1;} // Chave existe
        else {              // nova chave (h[p].status = LIVRE)

            h[p].key = k;
            h[p].info = i;
            h[p].status = OCUPADA;
            r = 2;
    }
    return r;
}

//---- Usando agora a possibilibade do status ser apagadas

int findprobe2(HashTable h, int k) {

    int p = hash(k,N),temp;
    int tent = N;

    while(h[p].status == OCUPADA && h[p].key != k && tent > 0) {
        p = (p+1)%N;
        tent--;
    }
    if(h[p].key != k) { 
        if(tent == 0) p = -1; // Tabela Cheia
        else { 
            temp = p;
            while(h[p].status == OCUPADA && h[p].status == APAGADA && h[p].key != k && tent > 0) {
                p = (p+1)%N;
                tent--;
            }
            if(h[p].key != k) p = temp;
        }
    }
    return p;
}

int search2 (HashTable h, int k, int *i){

    int p = findprobe2(h,N), found = 0;

    if(p != -1 && h[p].status == OCUPADA && h[p].key == k) {
        *i = h[p].info;
        found = 1;
    }

    return found;
}

int update2(HashTable h, int k, int i){

    int p = findprobe2(h,N);
    int r;

    if(p == -1) r = 0;
    else 
        if(h[p].key == k) {
            h[p].info = i;
            h[p].status = OCUPADA;
            r = 1;
        }
        else {
            h[p].info = i;
            h[p].status = OCUPADA;
            h[p].key = k;
            r = 2;
        }

    return r;
}

int delete(HashTable h , int k){

    int p = findprobe2(h,N);
    int deleted = 0;

    if(p == -1) deleted = 1; // insucesso 
    else 
        if(h[p].status == OCUPADA && h[p].key == k) {
            h[p].status = APAGADA;
        }

    return deleted;
}

// ----- Usando probe na definicioção das hashtables
// O parâmetro "probeC" guarda o número de probes que foram  
// efectuados para guardar essa informação nessa posição

typedef struct bucketProbe {

    int probeC; // -1: Free ; -2: Deleted
    int key;
    int value;

}BucketProbe;

typedef BucketProbe HashTableProbe[N];


int searchProbe (HashTableProbe h, int key, int *value) {

    int p = hash(key,N), probe = 0;
    int found = 1; // insucesso

    while(h[p].probeC >= 0 && h[p].key != key && probe < N) {
        p = (p+1)%N;
        probe++;
    }
    if(probe == N) found = 2;
    if (h[p].probeC >= 0) {
        *value = h[p].value;
        found = 0;
    }

    return found;
}


int updateProbe(HashTableProbe h, int key, int value) {

    int p = hash(key,N);
    int probe = 0, r;

    while(h[p].probeC >= 0 && h[p].key != key && probe < N) {
        p = (p+1)%N;
        probe++;
    }
    if (probe == N) r = 2;
    else 
        if (h[p].probeC != LIVRE) {
            h[p].value = value;
            r = 1;
        }
        else {
            h[p].value = value;
            h[p].probeC = probe;
            h[p].key = key;
            r = 0;
        }

    return r;
}



// ------------------------------------------------------------ AVL Tree ------------------------------------------------------------

#define LEFT 1
#define BAL 0
#define RIGHT -1

typedef struct AVLTree {

    int value;
    int bal;
    struct AVLTree *left, *right;

} *AVLTree;

int alturaAVLTree (AVLTree a) {

    int count = 0;

    if (a != NULL) {
        while(a -> left || a -> right) {
            if(a -> bal == LEFT) a = a -> left;
            else a = a -> right;
            count++;
        }
    }
    return count;
}

int isAVL(AVLTree a) {

    int r = 1;
    int left,right;

    if(a != NULL) {

        left = alturaAVLTree(a -> left);
        right = alturaAVLTree(a -> right);

        if(abs(left - right) > 1) r = 0;

        r = isAVL(a->left) && isAVL(a ->right);
    }
    return r;

}

AVLTree rotateRight(AVLTree a){
    
    AVLTree b = a->left;
    a->left = b->right;
    b->right=a;

    return b;
}

AVLTree rotateLeft(AVLTree b){

    AVLTree a = b -> right;
    b -> right = a -> left;
    a -> left = b;

    return a;
}

AVLTree fixRight (AVLTree a) {

    AVLTree b,c;
    b = a -> right;

    if(b -> bal == RIGHT) {
        a -> bal = b -> bal = BAL;
        a = rotateLeft(a);
    }
    else {
        c = b -> left;
        switch (c -> bal){

            case BAL: {a -> bal = b -> bal = BAL;}
            case LEFT: {a -> bal = BAL; b -> bal = RIGHT; break;}
            case RIGHT: {a -> bal = LEFT; b -> bal = BAL; break;}
        }
        c -> bal = BAL;
        a -> right = rotateRight(b);
        a = rotateLeft(a);
    }
    return a;
}


AVLTree fixLeft(AVLTree a) {

    AVLTree b,c;
    b = a -> left;

    if(b -> bal == LEFT) {
        b -> bal = a -> bal = BAL;
        a = rotateRight(a);
    }
    else {

        c = b -> right;
        switch(c -> bal) {

            case BAL: {a -> bal = b -> bal = BAL;}
            case LEFT: {a -> bal = RIGHT; b -> bal = BAL; break;}
            case RIGHT:{a -> bal = BAL; b -> bal = LEFT; break;}
        }
        c -> bal = BAL;
        a -> left = rotateLeft(b);
        a = rotateRight(a);
    }
    return a;
}


// --------------------------------------------------------------- Grafos ------------------------------------------------------------

// Matrizes de adjacência (muito pouco eficiente)

#define NV 3 // número de vértices
#define NEd 3 // número de arestas
#define NE -1 // número da aresta que não existe

typedef int GraphMat[NV][NV];

// Listas de adjacência

typedef struct edge {

    int dest; // destino da aresta
    int cost ; // peso da aresta
    struct edge *next;

}*EList;

typedef EList Graph [NV];

void printGrafo(Graph g) {

    EList it;

    for(int i = 0; i < NV; i++) {
        for(it = g[i]; it != NULL; it = it -> next)
            printf("(%d,%d)", it -> dest, it -> cost);
        printf("\n");
    }
    printf("\n");

}

void printMatriz(int tam, int a[tam][tam]) {

    for(int l = 0; l < tam; l++) {
        for(int c = 0; c < tam; c++)
            printf("%d ", a[l][c]);
        printf("\n");
    }
    printf("\n");
}

void matToList (GraphMat go,Graph gd){

    int l,c;
    EList new;

    for(l = 0; l < NV; l++) gd[l] = NULL; // preencher a lista com nulls

    for(l = 0; l < NV; l++)
        for(c = NV-1; c > 0; c--) {
            
            if(go[l][c] != 0) {
                new = malloc(sizeof(struct edge));
                new -> dest = c;
                new -> cost = go[l][c];
                new -> next = gd[l];
                gd[l] = new;
            }
        }
}

void listToMat(Graph go, GraphMat gd) {

    int l,c;
    EList it;

    for(l = 0; l < NV; l++)
        for(c = 0; c < NV; c++)
            gd[l][c] = 0;

    for(l = 0; l < NV; l++)
        for(it = go[l]; it != NULL; it = it -> next) 
            gd[l][it -> dest] = it -> cost;

}

int nArestas(Graph g){

    int count = 0;
    EList it;

    for(int i = 0; i < NV; i++)
        for(it = g[i]; it != NULL; it = it -> next)
            count++;

    return count;
}

// -------------- Vetor de adjacência -----------------

typedef struct edgeV {
    int dest; 
    int cost; 
} Edge;

typedef struct {
    int vertices[NV+1];
    Edge edges[NEd];
} GraphVect;


void listToVect (Graph go, GraphVect *gd){

    EList it;
    int k = 0,i;

    for(i = 0; i < NV; i++){
        gd->vertices[i] = k;
        for(it = go[i]; it != NULL; it = it -> next) {
            gd->edges[k].dest = it -> dest;
            gd->edges[k].cost = it -> cost;
            k++;
        }
    }
    gd->vertices[i] = k;
}

void vectToList (GraphVect *go, Graph gd){
    int i, k;
    EList tmp;
    for (i = 0; i < NV; i++){
        gd [i] = NULL;
        for (k = go -> vertices[i+1]-1 ;k >= go -> vertices[i];k--){
            tmp = malloc(sizeof(struct edge));
            tmp -> dest = go -> edges[k].dest;
            tmp -> cost = go->edges[k].cost;
            tmp -> next = gd[i];
            gd [i] = tmp;
        }
    }
}

// ----------------------------------------------------

int edgeWMat(GraphMat g, int o, int d, int *w){

    *w = g[o][d];
    return (*w != NE);

}

int edgeW(Graph g, int o, int d, int *w){

    EList it;

    for(it = g[o]; it != NULL; it = it -> next)
        if(it -> dest == d) *w = it -> cost;

    return (*w != NE);

}

int indegreeMat (GraphMat g, int v){ // Conta quantas arestas têm como destino v (matriz de adjência)

    int count = 0;

    for(int i = 0; i < NV; i++)
        if (g[i][v] != NE) count++;

    return count;

}

int outdegreeMat (GraphMat g, int v){ // Conta quantas arestas têm como origem v (matriz de adjência)

    int count = 0;

    for(int i = 0; i < NV; i++)
        if(g[v][i] != NE) count++;

    return count;
}

int indegree (Graph g, int v){ // Conta quantas arestas têm como destino v (lista de adjência)

    int count = 0;
    EList it;

    for(int i = 0; i < NV; i++)
        for(it = g[i]; it != NULL; it = it -> next)
            if(it -> dest == v) count++;

    return count;
}

int outdegree (Graph g, int v){ // Conta quantas arestas têm como origem v (lista de adjência)

    EList it;
    int count = 0;

    for(it = g[v]; it != NULL; it = it -> next)
        count++;

    return count;

}


// --------------- Travessias Depth first (RECURSIVIDADE) -----------------------

// Diz se há um caminho de "o" a "d" no grafo "g".
// Nota: Não precisava de guardar os visitados se o grafo fosse acíclico.

int procuraRec (Graph g, int o, int d, int visitados[]){

    int found = 0;
    EList it;

    visitados[o] = 1;    

    if(o == d) found = 1;
    else 
        for(it = g[o]; it != NULL && found == 0; it = it -> next) 
            if(visitados[it -> dest] == 0){
                visitados[it->dest] = 1;
                found = procuraRec(g,it -> dest,d,visitados);
            }

    return found;    
}

int procura (Graph g, int o, int d){

    int visitados[NV]; int i;

    for (i = 0 ;i < NV; i++) visitados[i] = 0;

    return (procuraRec(g,o,d,visitados));
}


// Conta quantos vértices são alcançáveis a partir de um vértice "o" no grafo "g".
// Nota: Sem caso de paragem, percorre o destinos de todos os não visitados de o.

int DFRec (Graph g, int o, int visitados[]) {

    EList it;
    int count = 0;

    visitados[o] = 1;

    for(it = g[o]; it != NULL; it = it -> next)
        if(visitados[it -> dest] == 0)
            count += DFRec(g,it -> dest, visitados);

    return count;
}


int travessiaDF (Graph g, int o){

    int visitados[NV];
    
    for(int i = 0; i < NV; i++) visitados[i] = 0;

    return(DFRec(g,o,visitados));

}

// --------------------------- Travessias Breadth first (Não recursiva e QUEUES) ------------------

// Conta quantos vértices são alcançáveis a partir de um vértice "o" no grafo "g".

int travessiaBF(Graph g, int o) {

    int q[NV],visitados[NV];
    int inicio,fim; inicio = fim = 0;
    EList it;
    int v,count = 0;

    for(int i = 0; i < NV; i++) visitados[i] = 0;

    q[fim++] = o;
    visitados[o] = 1;

    while(inicio != fim) {

        v = q[inicio++];
        count++;

        for(it = g[v]; it; it = it -> next)
            if(visitados[it->dest] == 0){
                visitados[it->dest] = 1;
                q[fim++] = it->dest;
            }
    }
    return count;
}

// Conta quantos vértices são alcançáveis a partir de um vértice "o" no grafo "g" em forma de árvore
// Nota: Não usa visitados mas usa um vetor para os anteriores

int travessiaBFTree (Graph g, int o) {

    EList it;
    int q[NV], inicio,fim,count = 0; inicio = fim = 0;
    int ant[NV];

    for(int i = 0; i < NV; i++)
        ant[i] = -2;

    q[fim++] = o;
    ant[o] = -1;

    while(inicio != fim) {

        o = q[inicio++];
        count++;

        for(it = g[o]; it != NULL; it = it -> next) 

            if(ant[it -> dest] == -2) {
                ant[it -> dest] = o;
                q[fim++] = it -> dest;
            }
    }
    return count;
}

// Conta quantos vértices são alcançáveis a partir de um vértice "o" no grafo "g" com uma distância máxima de "x".

int succN(Graph g, int o, int x) {

    EList it; int inicio, fim; inicio = fim = 0;
    int q[NV]; int distancia[NV]; int visitados[NV];
    int count = 0;

    for(int i = 0; i < NV; distancia[i++] = 0) visitados[i] = 0; 

    q[fim++] = o;
    visitados[o] = 1;

    while(inicio != fim && distancia[q[inicio]] <= x) {

        o = q[inicio++];
        count++;

        for(it = g[o]; it != NULL; it = it -> next)
            if(visitados[it -> dest] == 0){
                q[fim++] = it -> dest;
                distancia[it -> dest] = 1 + distancia[o];
                visitados[it -> dest] = 1;
            }

    }
    return count;
}

// Conta quantos vértices são alcançáveis a partir de um vértice "o" no grafo "g"
// Nota: Não usa visitados mas usa um vetor para os anteriores, orla e cores.

#define BRANCO   0 // não visitado
#define CINZENTO 1 // em visita
#define PRETO    2 // visitado

int travessiaBForla(Graph g, int o, int ant[]) {

    int orla[N], inicio,fim; inicio = fim = 0;
    int cor[N],i, count = 0;
    EList it;

    for(i = 0; i < N;i++){
        cor[i] = BRANCO; 
        ant[i] = -1;
    }

    orla[fim++] = o;
    cor[o] = CINZENTO;

    while(inicio != fim) {

        o = orla[inicio++]; 
        cor[o] = PRETO;
        count++;

        for(it = g[o]; it != NULL; it = it -> next) 

            if(cor[it -> dest] == BRANCO) {
                orla[fim++] = it -> dest;
                cor[it -> dest] = CINZENTO;
                ant[it -> dest] = o;
            }
    }
    return count;
    
}

// ----------------------------- ALGORITMOS DE GRAFOS ---------------------------------

// PRIM

struct fringe{

    int size;
    int edges[NV];

};

typedef struct fringe *Fringe;

int initFringe (Fringe f) {
    f -> size = 0;
    return 0;
}

int addEdgeFringe(Fringe f, int v, int cost[]) {

    f->edges[f->size++] = v;
    return 0;
}

int updateFringe(Fringe f, int v, int cost[]) {
    return 0;
}

int getEdge1(Fringe f, int cost[]){

    int i,minind,r;
    minind = 0;
    for (i = 1; i < f -> size; i++)
        if(cost[f -> edges[i]] < cost[f -> edges[minind]])
            minind = i;
    r = f -> edges[minind];
    f -> size--;
    f -> edges[minind] = f -> edges[f -> size];

    return r;
}

int PrimMST1 (Graph g, int cost[], int ant[]) {

    int res = 0; int v;
    EList it;
    int color[NV];
    struct fringe ff, *f;
    int fringesize;
    f = &ff;

    for (v = 0; v < NV; color[v++] = 0);

    initFringe(f); fringesize = 0;
    v = 0;
    color[v] = 1; cost[v] = 0;
    addEdgeFringe (f,v,cost);fringesize++;

    while (fringesize > 0){
        v = getEdge1 (f,cost);fringesize--;
        res += cost[v];
        color[v] = 2; //BLACK
        for (it = g[v];it;it = it -> next)
            if ((color[it -> dest] == 0) || (color[it -> dest] == 1 && cost[it -> dest] > it -> cost)) {
                ant[it -> dest] = v;
                cost[it -> dest] = it -> cost;
                if (color[it -> dest] == 0){ // White
                    addEdgeFringe (f,it -> dest,cost);
                    fringesize++;
                }
                else updateFringe (f,it -> dest,cost);
        }
    }
    return res;
}

int getEdge(int orla[],int cost[],int fim) {

    int minInd = 0,r = 0;
    for(int i = 1; i < NV; i++)
        if(cost[orla[i]] < cost[orla[minInd]]) minInd = i;

    r = orla[minInd];
    fim--;
    orla[minInd] = orla[fim];

    return r;

}

int PrimMST (Graph g) {

    int res = 0; int v;
    EList it;
    int color[NV],cost[NV],ant[NV],orla[NV];
    int inicio,fim; inicio = fim = 0;

    for (v = 0; v < NV; v++){color[v] = cost[v] = 0; ant[v] = -1;}

    color[0] = 1;
    orla[fim++] = 0;

    while (inicio != fim){
        v = getEdge(orla,cost,fim); fim--;
        res += cost[v];
        color[v] = 2; //BLACK

        for (it = g[v];it;it = it -> next)
            if ((color[it -> dest] == 0) || (color[it -> dest] == 1 && cost[it -> dest] > it -> cost)) {
                ant[it -> dest] = v;
                cost[it -> dest] = it -> cost;
                if (color[it -> dest] == 0){ // White
                    orla[fim++] = it -> dest;
                    color[it->dest] = 1;
                }
        }
    }
    return res;
}


int dijkstraSP (Graph g, int o){

    int res = 0,v;
    int newcost;
    EList it;
    int color[NV], cost[NV], ant[NV],orla[NV];
    int inicio, fim; inicio = fim = 0;

    for (v = 0; v < NV ; v++){color[v] = cost[v] = 0; ant[v] = -1;}

    orla[fim++] = o;
    color[o] = 1;

    while (inicio != fim){
        v = getEdge(orla,cost,fim);fim--;
        res++;
        color[v]= 2; //BLACK

        for (it = g[v];it; it = it -> next){
            newcost = cost[v] + it -> cost;
            if((color[it -> dest] == 0)||((color[it -> dest] == 1) && cost[it -> dest] > newcost)){
                ant[it -> dest] = v;
                cost[it -> dest] = newcost;
                if (color [it -> dest] == 0){ // White
                    orla[fim++] = it->dest;
                    color[it->dest] = 1;
                }
            }
        }
    }
    return res;
}



// ------------------------------------------------------------ npComplete -----------------------------------------------------------

int colorOK(Graph g, int color[]) {

    int r = 1;
    int i;
    EList it;

    for(i = 0; i < NV; i++)
        for(it = g[i]; it; it = it -> next)
            if (color[it -> dest] == color[i]) r = 0;

    return r;
}

int k_colorRec(Graph g,int color[], int i, int k) {

    int x;

    if(i == N) return colorOK(g,color);
    else for(x = 0; x < k; x++) {
            color[i] = x;
            if(k_colorRec(g,color,i+1,k)) return 1;
    }
    return 0;
}

int k_color(Graph g, int k) {

    int color[N];
    for(int i = 0; i < N; i++)
        color[i] = 0;
    return (k_colorRec(g,color,0,k));

}


/* Main para grafos

int main() {

    GraphMat matrizGrafo;
    Graph Grafo;

    int matriz[NV][NV] = {
                          { 0, 2, 3 },
                          { 0, 0, 5 },                  
                          { 0, 0, 0 }                   
                         };

    printMatriz(NV,matriz);

    for(int i = 0; i < NV; i++)
        for(int j = 0; j < NV; j++)
            matrizGrafo[i][j] = matriz[i][j];

    matToList(matrizGrafo,Grafo);

    printGrafo(Grafo);

    printf("%d\n", nArestas(Grafo));

    return 0;
}*/

//int main() {}