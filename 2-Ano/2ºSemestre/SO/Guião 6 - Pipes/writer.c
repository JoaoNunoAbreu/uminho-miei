#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>

int main(){ 

    int fd; 
    char buf[100];

    fd = open("FIFO", O_WRONLY); // Abre o FIFO para escrever
    while(1){ 
        int n = read(0,buf,sizeof buf); // Lê do standard input e guarda no array 
        if(n <= 0) break;
        write(fd,buf,n); // Escreve no FIFO o conteúdo do array
    } 
    close(fd);

    return 0; 
} 