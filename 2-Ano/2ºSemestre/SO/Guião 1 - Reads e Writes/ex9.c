#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

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

int main(int argc, char* argv[]){

    int i = 2; int fd = 0; int counter = 0;
    char c;
    char buf[100];

    if(argc == 1){
        while(1){
            int n = readln(0,buf,sizeof buf);
            if(n <= 0) break;
            write(1,buf,n);
        }
    }
    else if(argc == 2){
        while(counter < abs(atoi(argv[1]))){
            int n = readln(0,buf,sizeof buf);
            if(n <= 0) break;
            write(1,buf,n);
            counter++;
        }
    }
    else{
        while(i < argc){
            int fd = open(argv[i],O_RDONLY);
            char linha[100];
            int tam = sprintf(linha,"==> %s <==\n",argv[i]);
            write(1,linha,tam);
            counter = 0;
            while(counter < abs(atoi(argv[1]))){
                int n = readln(fd,buf,sizeof buf);
                if(n <= 0) break;
                write(1,buf,n);
                counter++;
            }
            close(fd);
            i++;
        }
    }
}