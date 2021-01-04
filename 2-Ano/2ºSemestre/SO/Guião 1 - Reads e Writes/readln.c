#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

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

int main(int argc, char *argv[]){

    int fd = 0;

    // Testa se vai ler ficheiro ou standard input
    if(argc == 2) fd = open(argv[1],O_RDONLY);
    int BUFFSIZE = 10000;
    char *buf = malloc(BUFFSIZE);
    int line = 1;   
    
    while(1){
        int pre = sprintf(buf,"%6d  ",line);
        ssize_t n = readln(fd,buf+pre,BUFFSIZE-pre);
        if (n <= 0) break;
        write(1,buf,n+pre);
        line++;
    }

    return 0;
}