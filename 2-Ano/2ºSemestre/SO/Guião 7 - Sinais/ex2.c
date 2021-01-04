#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int main(int argc, char* argv[]){
    int pids[3];
    char a = 1;

    for(int i = 1; i < 3; i++){
        pids[i-1] = fork();
        if(!pids[i-1]){
            execlp(argv[i],argv[i],(char*) 0);
            _exit(0);
        }
        else{
            sleep(1);
            wait(0);
        }
    }
    return 0;
}