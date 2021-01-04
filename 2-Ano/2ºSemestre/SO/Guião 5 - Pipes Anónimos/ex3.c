#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(){

    int fd[2];
    pipe(fd);
    
    if(!fork()){
        close(fd[1]);
        dup2(fd[0],0);
        close(fd[0]);
        execlp("wc","wc",(char*) 0);
        _exit(0);
    }

    close(fd[0]);
    char buf[100];
    while(1){
        ssize_t n = read(0,buf,sizeof(buf));
        if(n <= 0) break;
        write(fd[1],buf,strlen(buf));
    }
    return 0;
}