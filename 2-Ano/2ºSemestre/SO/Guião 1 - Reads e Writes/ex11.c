#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

ssize_t readln(int fildes, void *buf, size_t nbyte){

    char *b = buf;
    size_t i = 0;

    while(i < nbyte){
        ssize_t n = read(fildes,&b[i],1);
        if (n <= 0) break;
        if(b[i] == '\n') return (i+1);
        i++;
    }
    return i;
}

int contaPalavras(int N, char str[N]){
    int res = 1;
    for(int i = 0; i < N; i++)
        if(str[i] == ' ') res++;
    return res;
}

int main(int argc, char* argv[]){

    int i = 1; int fd = 0; int counter = 0;
    char c;
    char buf[100];

    int nLinhas = 0;
    int nPalavras = 0;
    int nBytes = 0;

    char res[100]; int tam;

    if(argc == 1){
        while(1){
            int n = readln(0,buf,sizeof buf);
            if(n <= 0) break;
            nLinhas++;
            nPalavras += contaPalavras(n,buf);
            nBytes += n;
        }
        tam = sprintf(res," %7d %7d %7d\n", nLinhas,nPalavras,nBytes);
        write(1,res,tam);
    }
    else if(argc == 2){
        while(i < argc){
            int fd = open(argv[i],O_RDONLY);
            char linha[100];
            while(1){
                int n = readln(fd,buf,sizeof buf);
                if(n <= 0) break;
                nLinhas++;
                nPalavras += contaPalavras(n,buf);
                nBytes += n;
            }
            close(fd);
            tam = sprintf(res," %7d %7d %7d %s\n", nLinhas,nPalavras,nBytes,argv[i]);
            write(1,res,tam);
            i++;
        }
    }
    else{
        int nLinhasTotais = 0;
        int nPalavrasTotais = 0;
        int nBytesTotais = 0;
        while(i < argc){
            int fd = open(argv[i],O_RDONLY);
            char linha[100];

            nLinhas = 0;
            nPalavras = 0;
            nBytes = 0;

            while(1){

                int n = readln(fd,buf,sizeof buf);
                if(n <= 0) break;
                nLinhasTotais++;
                nPalavrasTotais += contaPalavras(n,buf);
                nBytesTotais += n;

                nLinhas++;
                nPalavras += contaPalavras(n,buf);
                nBytes += n;
            }
            close(fd);
            tam = sprintf(res," %7d %7d %7d %s\n", nLinhas,nPalavras,nBytes,argv[i]);
            write(1,res,tam);
            i++;
        }
        tam = sprintf(res," %7d %7d %7d %s\n", nLinhasTotais,nPalavrasTotais,nBytesTotais,"total");
        write(1,res,tam);
    }
}