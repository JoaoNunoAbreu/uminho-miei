#include <unistd.h>
#include <fcntl.h> 

int main(int argc, char* argv[]) {

    if(argc != 2) return 1;
    char* nome = argv[1];
    int fd = open(nome,O_WRONLY | O_TRUNC | O_CREAT, 0666);

    const int N = 1000;
    const int IT = 10000;
    char buf[N];

    for(int i = 0; i < N; i++) buf[i] = 'a';

    for(int i = 0; i < IT; i++)
        write(fd,buf,N);

    return 0;

}