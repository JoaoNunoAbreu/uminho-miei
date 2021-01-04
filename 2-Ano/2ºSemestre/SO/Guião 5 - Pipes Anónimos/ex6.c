#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

int mypopen(char* cmd, char* modo){
    int m;
    if(!strcmp(modo,"r")) m = 0;
    else if(!strcmp(modo,"w")) m = 1;
    else return -1;

    int fd[2];
    pipe(fd);
    if(!fork()){
        dup2(fd[1-m],1-m);
        close(fd[1]);
        close(fd[0]);
        execl("/bin/sh","/bin/sh","-c",cmd, (char*) 0);
    }
    close(fd[1-m]);
    return fd[m];
}

int main(){

    int fd = mypopen("ls -l","r");
    char buf[1000];
    while(1){
        int n = read(fd,buf,1000);
        if(n <= 0) break;
        write(1,buf,n);
    }
    return 0;
}