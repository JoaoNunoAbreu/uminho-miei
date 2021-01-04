#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>

int main(){

    int fd;
    char buf[100];

    fd = open("FIFO", O_RDONLY); // Abre o FIFO para ler
    while(1){
        int n = read(fd, buf, sizeof buf); // Lê do FIFO e guarda no array 
        if(n <= 0) break;
        write(1,buf,n); // Escreve no standard output o conteúdo lido do FIFO que está no array
    }
    close(fd);

    return 0;
}