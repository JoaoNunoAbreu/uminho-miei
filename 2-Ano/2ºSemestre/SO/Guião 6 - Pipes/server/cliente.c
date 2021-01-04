#include <fcntl.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char* argv[]){ 

    if(argc > 2){
        int fd = open("FIFO", O_WRONLY);
        int n = strlen(argv[1]);
        char buf[n+1];
        memcpy(buf,argv[1],n);
        buf[n] = '\n';
        write(fd,buf,n+1);
        close(fd);
    }
    else printf("argc < 2\n");

    return 0; 
} 