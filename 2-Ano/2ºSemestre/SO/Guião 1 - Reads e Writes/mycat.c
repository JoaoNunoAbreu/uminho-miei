#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {

    char c;
    while(1){
        int n = read(0,&c,1);
        if (n <= 0) break;
        write(1,&c,1);
    }

    return 0;
}