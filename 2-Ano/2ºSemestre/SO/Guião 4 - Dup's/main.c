#include <unistd.h>
#include <fcntl.h>
#include <stdio.h>

int main(){

    int fd;

    fd = open("/etc/passwd",O_RDONLY);
    dup2(fd,0);
    close(fd); 

    fd = open("saida.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
    dup2(fd,1);
    close(fd);

    fd = open("erros.txt",O_CREAT | O_WRONLY | O_TRUNC,0666);
    dup2(fd,2);
    close(fd);
    
    execlp("wc","wc",(char*)0);

    return 0;
}