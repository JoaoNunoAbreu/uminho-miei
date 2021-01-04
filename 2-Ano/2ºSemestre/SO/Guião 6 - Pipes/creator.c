#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

int main(){

    mkfifo("FIFO", 0666);
    return 0;
}