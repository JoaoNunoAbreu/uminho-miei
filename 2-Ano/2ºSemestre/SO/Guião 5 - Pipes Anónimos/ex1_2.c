#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int main(){

    int fd[2];
    pipe(fd);
    
    if(!fork()){
        close(fd[1]);
        char buf[100];
        while(1){
            ssize_t n = read(fd[0],buf,sizeof(buf));
            if(n <= 0) break;
            write(1,buf,n);
        }
        _exit(0);
    }
    close(fd[0]);
    char* s = "ola mundo\n";
    write(fd[1],s,strlen(s));
    close(fd[1]);
    wait(0);
}