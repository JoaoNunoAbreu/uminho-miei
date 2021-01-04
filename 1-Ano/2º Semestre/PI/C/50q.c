#include <stdio.h>

//--------------------1.-------------------------

// Maior número

int main() {

    int x,maior;
    maior = 0;
    x = 42;
    while (x != 0) {
        scanf ("%d",&x);
        if (x > maior) maior = x;
    }
    printf("O maior número é: %d\n",maior);
    return 0;
} 

//--------------------2.-------------------------

// Média

int main() {

    int x = 42;
    float media, sum, conta = -1;
    media = sum = 0;

    while (x != 0) {

        scanf("%d",&x);
        conta++;
        sum = sum + x;
    }

    media = sum/conta;
    printf("A média é: %f\n", media);

    return 0;
}

//--------------------3.-------------------------

// Segundo maior elemento

int main() {

    int x = 42,maior, maior2,a,b;

    scanf("%d",&a);
    scanf("%d",&b);

    if (a > b) {maior = a; maior2 = b;}
    else {maior = b; maior2 = a;}

    while (x != 0) {

        scanf ("%d",&x);
        if (x > maior) {maior2 = maior; maior = x;}
        if (x > maior2 && x < maior) maior2 = x;

    }

    printf("O segundo maior número é: %d\n",maior2);
    return 0;
} 

//--------------------4.-------------------------

// Calcula o número de bits iguais a 1 usados na representação binária de um dado número n.

int bitsUm (int x){
    
    int conta = 0;
    
    for (;x != 0; x /= 2)
        if (x % 2 == 1) conta++;
    
    return conta;
}

//--------------------5.-------------------------

int trailingZ (unsigned int n) {
    
    int conta = 0;
    
    for (;n % 2 == 0; n/= 2) conta++;
    
    return conta;
}

//--------------------6.-------------------------

// Diz quantos algarismos um número tem

int qDig (int n) {
    
    int conta = 0;
    
    for(; n > 0; n /= 10) conta++;
        
    return conta;
}

//--------------------7.-------------------------

char *mystrcat(char s1[], char s2[]) {
    
    int i,j;
    
    for (i = 0; s1[i] != '\0'; i++);
    
    for (j = 0; s2[j] != '\0'; j++) {
        
        s1[i] = s2[j];
        i++;
    }
    
    s1[i] = '\0';
    
    return s1;
}



//--------------------8.-------------------------

char *mystrcpy(char s1[], const char s2[]) {
    
    int i;
    
    for (i = 0; s2[i] != '\0'; i++)
        s1[i] = s2[i];
    
    s1[i] = '\0';
    
    return s1;
}

//--------------------9.-------------------------

int mystrcmp(char s1[], char s2[]) {
    
    int i;
    
    for (i = 0; s1[i] == s2[i] && s1[i] != '\0'; i++);
    
    return (s1[i] - s2[i]);
    
}

//--------------------10.-------------------------

char *mystrstr (char s1[], char s2[]) {
    
    int i,j;
    j = 0;
    for (i = 0; s1[i] != '\0' && s2[j] != '\0'; i++) 
        if (s1[i] == s2[j]) j++;
    
    if (s2[j] == '\0') return (s1+(i-j));
    return NULL;
}

//--------------------11.-------------------------

void swap (char s[], char a, char b) {
    
    char guarda;
    
    guarda = s[a];
    s[a] = s[b];
    s[b] = guarda;
    
}

void strrev (char s[]) {
    
    int i, tam,meio;
    
    for (tam = 0; s[tam] != '\0'; tam++);
    
    meio = tam / 2;
    tam--;
    
    for (i = 0; i < meio; i++)
        swap (s,i,tam - i);
    
}

//--------------------12.-------------------------

void strnoV (char t[]){
    
    int i,j = 0;
    
    for (i = 0; t[i] != '\0'; i++){
        
        if (t[i] != 'a' && t[i] != 'e' && t[i] != 'i' && t[i] != 'o' && t[i] != 'u' && t[i] != 'A' && t[i] != 'E' && t[i] != 'I' && t[i] != 'O' && t[i] != 'U')
            t[j++] = t[i];
    }
    t[j] = '\0';
}

//--------------------13.-------------------------

void truncW (char s[], int n){

    int i, j = 0, conta = 0;
    
    for (i = 0; s[i] ; i++) {
        
        if (s[i] == ' ') conta = 0;
        else conta++;
        
        if (conta <= n) s[j++] = s[i];
        
    }
    s[j] = '\0';
}

//--------------------14.-------------------------


int contaChar (char s[], char a) {
    
    int i,conta = 0;

    for (i = 0; s[i] != '\0';i++){
        if (s[i] == a) conta++;
    }
    
    return conta;
    
}

char charMaisfreq (char s[]) {

    int i,max = 0;
    char r;
    
    for (i = 0; s[i] != '\0'; i++){
        
        if (max < contaChar (s,s[i])){
            max = contaChar (s,s[i]);
            r = s[i];
        }
    }
    return r;
}

//--------------------15.-------------------------

int iguaisConsecutivos (char s[]) {
    
    int i,conta = 1,maior = 0;
    
    for (i = 0; s[i]; i++) {
        
        if(s[i] == s[i+1]) conta++;
        else conta = 1;
        
        if (conta > maior) maior = conta;
    }
    return maior;
}

//--------------------16.-------------------------

int difConsecutivos(char s[])
{
    int i,j,maior = 0,r;
    
    for (i = 0; s[i] != '\0'; i++) {
        
        r = 0;
        for (j = i; s[j] != s[j+1] && s[j] != '\0'; j++)
            r++;
        if (r > maior) maior = r + 1;
        
    }
    return maior;
}

//--------------------17.-------------------------

int maiorPrefixo (char s1 [], char s2 []) {
    int i;
    for (i = 0; s1[i] == s2[i] && s1[i] != '\0'; i++);
    return i;
}

//--------------------18.-------------------------

int maiorSufixo (char s1 [], char s2 []) {
    
    int tam1, tam2, conta = 0;
    
    for (tam1 = 0; s1[tam1] ; tam1++);
    for (tam2 = 0; s2[tam2] ; tam2++);

    while (s1[tam1-1] == s2[tam2-1]) {conta++;tam1--;tam2--;}
    
    return conta;
    
}

//--------------------19.-------------------------

int sufPref (char s1[], char s2[]) {
    
    int i,j,r = 0;
    
    for (i = 0; s1[i] != '\0' ; i++){
        
        j = 0;
        if (s1[i] == s2[j]) r = 0;
        while (s1[i] == s2[j] && s1[i] != '\0'){i++; j++; r++;}
    }
    if (s1[i-1] != '\0' && s2[j] == '\0') r = 0;
    return r;
}

//--------------------20.-------------------------

int contaPal (char s[]) {
    
    int i,r = 0,dentro = 0;
    
    for (i = 0; s[i] != '\0'; i++){
        
        if (isspace (s[i])) dentro = 0;
        else if (dentro == 0) 
            {r++; dentro = 1;}
        
    }
    return r;  
}

//--------------------21.-------------------------


int contaVogais (char s[]) {
    
    int i,r = 0;
    
    for (i = 0; s[i] != '\0'; i++) {
        
        if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u' || s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U')
            r++;
    }
    return r;
    
}

//--------------------22.-------------------------

int elem (char a[], char var) {
    
    int i,r = 0;
    
    for (i = 0; a[i];i++)
        if (a[i] == var) r = 1;
    
    return r;
    
}

int contida (char a[], char b[]) {
    
    int i, r = 1;
    
    for (i = 0; a[i] && r == 1; i++)
        if (!elem (b,a[i])) r = 0;
        
    return r;
}

//--------------------23.-------------------------

int palindroma (char s[]) {
    
    int i,tam,r = 1;
    
    for (tam = 0; s[tam]; tam++);
    tam--;
    
    for (i = 0; s[i] && r == 1; i++)
        if (s[i] != s[tam-i]) r = 0;
    
    return r;
}

//--------------------24.-------------------------

int remRep (char s[]) {
    
    int i,j = 0;
    
    for (i = 0; s[i];i++)
        
        if (s[i] != s[i+1])
            s[j++] = s[i];
            
    s[j] = '\0';
    return j;
}

//--------------------25.-------------------------

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

//--------------------26.-------------------------

void insere (int s[], int N, int x){
    
    int i;
    
    for (i = N; i > 0 && s[i-1] > x; i--) 
        s[i] = s[i-1];
        
    s[i] = x;
}

//--------------------27.-------------------------

void merge (int r [], int a[], int b[], int na, int nb){
       
    int ia,ib,ir;
    ia = ib = ir = 0;
    while (ia < na && ib < nb)
        if (a[ia] < b[ib]) r[ir++] = a[ia++];
        else r[ir++] = b[ib++];
    
    while (ia < na) r[ir++] = a[ia++];
    while (ib < nb) r[ir++] = b[ib++];
       
}

//--------------------28.-------------------------

int crescente (int a[], int i, int j){
    
    int r = 1;
    
    for (;i < j && r == 1; i++)
        if (a[i] > a[i+1]) r = 0;
        
    return r;
}

//--------------------29.-------------------------

int retiraNeg (int v[], int N){
    
    int i, j = 0;
    
    for (i = 0; i < N ; i++) 
        if (v[i] > 0) v[j++] = v[i];
    
    return j;
}

//--------------------30.-------------------------

int menosFreq (int v[], int N){
    
    int menor = N,r = v[0],conta = 0;
    int i,j;
    
    for (i = 0; i < N; i++) {
        
        for (j = i,conta = 0; j < N && v[j] == v[j+1]; j++) conta++;
        
        if (conta < menor) {menor = conta; r = v[j];}
        
        i = j;
        
    }
    return r;
}

//--------------------31.-------------------------

int maisFreq (int v[], int N){
    
    int i,j,conta,maior = 0, r = v[0];
    
    for (i = 0; i < N; i++) {
        
        for (j = i, conta = 1; j < N && v[j] == v[j+1]; j++,conta++);
        
        if (conta > maior) {maior = conta; r = v[j];}
        
        i = j;
    }
    return r;
}

//--------------------32.-------------------------

int maxCresc (int v[], int N) {
    
    int i,j,conta, maior = 0;
    
    for (i = 0; i < N; i++) {
        
        for (j = i, conta = 1; v[j] <= v[j+1] && j < N; j++, conta++);
        
        if (conta > maior) maior = conta;
        
    }
    return maior;
}

//--------------------33.-------------------------

int contida (int v[], int N, int var) {
    
    int i, r = 0;
    
    for (i = 0; i < N && r == 0; i++)
        if (v[i] == var) r = 1;
        
    return r;
    
}

int elimRep (int v[], int N) {
    
    int i, j = 0;
    
    for (i = 0; i < N; i++)
        if (!contida (v,i,v[i])) v[j++] = v[i];
        
    return j;
}

//--------------------34.-------------------------

int contida (int v[], int N, int var) {
    
    int i,r = 0;
    
    for (i = 0; i < N && r == 0; i++)
        if (v[i] == var) r = 1;
        
    return r;
    
}

int elimRepOrd (int v[], int N) {
    
    int i,j = 0;
    
    for (i = 0; i < N; i++)
        if (!contida(v,i,v[i])) v[j++] = v[i];
        
    return j;
    
}

//--------------------35.-------------------------

int comunsOrd (int a[], int na, int b[], int nb){
    
    int ia,ib,conta = 0;
    
    for (ia = 0, ib = 0; ia < na && ib < nb;) {
        
        if (a[ia] == b[ib]) {conta++;ia++;ib++;}
        if (a[ia] < b[ib]) ia++;
        if (a[ia] > b[ib]) ib++;
        
    }
    return conta;
}

//--------------------36.-------------------------

int pertence (int v[], int N, int var) {
    
    int i,r = 0;
    
    for (i = 0; i < N && r == 0; i++)
        if (v[i] == var) r = 1;
    
    return r;
}

int comuns (int a[], int na, int b[], int nb){
    
    int i,conta = 0;
    
    for (i = 0; i < na; i++)
        if (pertence(b,nb,a[i])) conta++;
        
    return conta;
}

//--------------------37.-------------------------

int minInd (int v[], int N) {
    
   int i, conta, menor = v[0], r = 0;
   
   for (i = 1; i < N ; i++)
       
       if (v[i] < menor) {menor = v[i]; r = i;}
       
   return r;
}

//--------------------38.-------------------------

void somasAc (int v[], int Ac [], int N){
   
   int i, sum = 0;
   
   for (i = 0; i < N; i++) {   
       Ac[i] = sum + v[i]; 
       sum += v[i];
   }
}

//--------------------39.-------------------------

int triSup (int N, int m [N][N]) {
    
    int l,c, r = 1;
    
    for (l = 0; l < N && r == 1; l++) 
        
        for (c = 0; c < l && r == 1; c++) 
            if (m[l][c] != 0) r = 0;
            
    return r;
}

//--------------------40.-------------------------

void transposta (int N, float m [N][N]) {
    
    int l,c,temp;
    
    for (l = 0; l < N; l++)
    
        for (c = l; c < N; c++) {
            temp = m[l][c];
            m[l][c] = m[c][l];
            m[c][l] = temp;
        }
}


//--------------------41.-------------------------

void addTo(int N, int M, int a [N][M], int b[N][M]) {
    
    int l,c;
    
    for (l = 0; l < N; l++) 
        for (c = 0; c < M; c++)
            a[l][c] += b[l][c];
}

//--------------------42.-------------------------

int unionSet (int N, int v1[N], int v2[N], int r[N]){
    
    int i;
    
    for (i = 0; i < N; i++) 
        if (v1[i] == 1 || v2[i] == 1) r[i] = 1;
        else r[i] = 0;
        
    return i;
}

//--------------------43.-------------------------

int intersectSet (int N, int v1[N], int v2[N], int r[N]){
    
    int i;
    
    for (i = 0; i < N; i++)
        if (v1[i] == 1 && v2[i] == 1) r[i] = 1;
        else r[i] = 0;
    
    return i;
}

//--------------------44.-------------------------

int intersectMSet (int N, int v1[N], int v2[N], int r[N]){
    
    int i;
    
    for (i = 0; i < N; i++) 
        if (v1[i] > v2[i]) r[i] = v2[i];
        else r[i] = v1[i];
    
    return i;
}

//--------------------45.-------------------------

int unionMSet (int N, int v1[N], int v2[N], int r[N]){
    int c=0;
    for (i=0;i<N;i++){
            r[c]= v1[i]+v2[i];
            c++;
       }
    return c;
}

//--------------------46.-------------------------

int cardinalMSet (int N, int v[N]){
    
    int i,sum = 0;
    
    for (i = 0; i < N; i++) 
        sum += v[i];
    return sum;
}

//--------------------47.-------------------------

typedef enum movimento {Norte, Oeste, Sul, Este} Movimento;

typedef struct posicao {
   int x, y;
} Posicao;


Posicao posFinal (Posicao inicial, Movimento mov[], int N){
    
    int i;
    
    for (i = 0; i < N; i++) {
        
        if (mov[i] == Norte) inicial.y++;
        if (mov[i] == Sul) inicial.y--;
        if (mov[i] == Oeste) inicial.x--;
        if (mov[i] == Este) inicial.x++;
        
    }
    return inicial;   
}

//--------------------48.-------------------------

int caminho (Posicao inicial, Posicao final, Movimento mov[], int N){
    
    int i = 0;    

    while (final.x != inicial.x || final.y != inicial.y) {
        
        if (final.x < inicial.x) {mov[i++] = Oeste; final.x++;}
        if (final.x > inicial.x) {mov[i++] = Este;  inicial.x++;}
        if (final.y < inicial.y) {mov[i++] = Sul;   final.y++;}
        if (final.y > inicial.y) {mov[i++] = Norte; inicial.y++;}
    }
    if (i > N) return -1;
    return i;
}

//--------------------49.-------------------------

int maiscentral (Posicao pos[], int N) {
    
    int i,r = 0,x1,y1;
    float a;
    int x0 = pos[0].x ,y0 = pos[0].y;
    
    float menor = sqrt (pow (x0,2) + pow(y0,2));
    
    for (i = 1; i < N ; i++) {
        
        x1 = pos[i].x;
        y1 = pos[i].y;
        
        a = sqrt (pow (x1,2) + pow(y1,2));
        
        if (a < menor) {menor = a; r = i;}
    }
    
    return r;
}

//--------------------50.-------------------------

int vizinhos (Posicao p, Posicao pos[], int N) {
    
    int i,conta = 0;
    
    for (i = 0; i < N; i++) {
        
        if (pos[i].x == p.x+1 && pos[i].y == p.y) conta++;
        if (pos[i].x == p.x-1 && pos[i].y == p.y) conta++;
        if (pos[i].x == p.x && pos[i].y == p.y+1) conta++;
        if (pos[i].x == p.x && pos[i].y == p.y-1) conta++;
    }
    return conta;
}