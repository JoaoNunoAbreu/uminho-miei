#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char* argv[]){

    int i = 1;
    if(argc < 2) exit(1);
    if(!strcmp(argv[i],"-i")){
        int fd = open(argv[i+1],O_RDONLY);
        dup2(fd,0);
        close(fd);
        i += 2;
    }
    if(!strcmp(argv[i],"-o")){
        int fd = open(argv[i+1],O_CREAT | O_WRONLY | O_TRUNC,0666);
        dup2(fd,1);
        close(fd);
        i += 2;
    }
    execvp(argv[i],argv + i);

    return 0;
}