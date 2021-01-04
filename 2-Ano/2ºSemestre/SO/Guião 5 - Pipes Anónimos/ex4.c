#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(){

    int fd[2];
    pipe(fd);
    
    if(!fork()){ 
        dup2(fd[1],1); // Para escrever no pipe
        close(fd[1]); // Esta linha e a anterior vêem sempre aos pares
        close(fd[0]); // Não precisa de ler no pipe
        execlp("ls","ls","/etc",(char*) 0);
        _exit(0);
    }
    dup2(fd[0],0); // Para ler do pipe
    close(fd[0]);
    close(fd[1]); // Não precisa de escrever no pipe
    execlp("wc","wc","-l",(char*) 0); 
    return 0;
}