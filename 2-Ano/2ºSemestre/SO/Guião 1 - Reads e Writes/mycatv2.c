#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {

    if(argc != 2) return 1;
    int N = atoi(argv[1]);

    char* buf = malloc(N);

    while(1){
        int n = read(0,buf,N);
        if (n <= 0) break;
        write(1,buf,n);
    }

    return 0;
}