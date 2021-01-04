#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]){

    for(int i = 1; i < argc; i++){
        if(!fork()){
            execlp(argv[i],argv[i],(char*) 0);
            _exit(0);
        }
    }
    for(int i = 1; i < argc; i++){ 
        wait(0);  
    }

    return 0;
}