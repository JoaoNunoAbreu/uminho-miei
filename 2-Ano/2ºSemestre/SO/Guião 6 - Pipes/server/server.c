#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>

int main(){
    mkfifo("FIFO",0666);
    int fdLog = open("LOG", O_CREAT | O_WRONLY | O_APPEND , 0666);

    while(1){
        int fd = open("FIFO", O_RDONLY); // Abre o FIFO para ler
        char buf[1000];
        while(1){
            int n = read(fd,buf,sizeof buf); // Lê do FIFO e guarda no array 
            if(n <= 0) break;
            write(fdLog,buf,n); // Escreve no standard output o conteúdo lido do FIFO que está no array
        }
        close(fd);
    }

    return 0;
}