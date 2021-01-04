#include <stdio.h>


char *mystrcpy(char s1[], const char s2[]) {
    
    int i;
    
    for (i = 0; s2[i] != '\0'; i++) {
        s1[i] = s2[i];
    }
    s1[i] = '\0';
    return s1;
    
}

void palavra (int  N, char sopa[N][N], char f[]) {


    char guarda[1024] = {0};
    int i;
    int l = 0,c = 0;
    guarda[0] = sopa[0][0];

    for(i = 0; f[i] != '\0'; i++) {

        if (f[i] == 'D') c++;
        if (f[i] == 'E') c--;
        if (f[i] == 'C') l--;
        if (f[i] == 'B') l++;
        guarda[i+1] = sopa[l][c];
    }
    mystrcpy(f,guarda);

}

// ------------------- 5.------------------------

int maiores (int v[], int N, int x) {

    int conta = 0,i;

    for (i = 0; i < N; i++)
        if (v[i] > x) conta++;

    return conta;

}

int menores (int v[], int N, int x) {

    int conta = 0,i;

    for (i = 0; i < N; i++)
        if (v[i] < x) conta++;

    return conta;

}

int mediana (int v[], int N) {

    int i; int r;

    for (i = 0; i < N; i++) 

        if ((maiores(v,N,v[i]) <= N/2) && (menores(v,N,v[i]) <= N/2)) r = v[i];
    
    return r;

}

int main() {

    int i,resultado;

    char str[100][100] = {{'B','A','T'},{'O','T','A'},{'L','O','T'}};

    char r[100] = "DDBEC";

    int x[100] = {11,15,13,12,14,13,12,14};

    //palavra(100,str,r);

    resultado = mediana(x,8);

    //for(i = 0; r[i] != '\0';i++)
    //    printf("%c", r[i]);
    //printf("\n");

    printf("%d\n",resultado);

    return 0; 

}
