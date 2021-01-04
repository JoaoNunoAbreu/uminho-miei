#include <unistd.h> 
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>

// Ex 1
void ex1(){
    printf("Filho id: %d\n",getppid());
    printf("Pai id: %d\n",getpid());
}

// Ex 2
void ex2(){
    pid_t pid = fork();
    if(!pid){ 
        // Filho
        printf("Sou filho, pid: %d, ppid: %d\n",getpid(),getppid());
        _exit(0);
    }
    // Pai
    printf("Sou pai, pid: %d, ppid: %d\n",getpid(),getppid());
}

// Ex 3
void ex3(){
    for(int i = 0; i < 10; i++){
        if(!fork()){
            printf("Sou filho, pid: %d, ppid: %d\n",getpid(),getppid());
            _exit(i + 1);
        }
        int status;
        wait(&status);
        if(WIFEXITED(status)){
            printf("Filho fez exit: %d\n",WEXITSTATUS(status));
        }
    }
}

// Ex 4
void ex4(){
    for(int i = 0; i < 10; i++){
        if(!fork()){
            printf("Sou filho, pid: %d, ppid: %d\n",getpid(),getppid());
            _exit(i + 1);
        }
    }
    for(int j = 0; j < 10; j++){
        int status;
        wait(&status);
        if(WIFEXITED(status)){
            printf("Filho fez exit: %d\n",WEXITSTATUS(status));
        }
    }
}

// Ex 5
void ex5(int counter){
    if (counter < 10) {
        pid_t pid = fork();
        if(!pid) ex5(counter+1);
        else{
            //wait(&pid) pela ordem 1,2,3,...
            printf("Meu pid: %d, sou filho de %d, filho: %d\n", getpid(), getppid(), counter+1);
            wait(&pid);
            exit(0);
        }   
    }
}

void ex5V2(){
    int i;
    for(i = 1; i <= 10; i++){
        if(i == 10) break;
        pid_t p = fork();
        if(p){
            wait(0);
            break;
        }
    }
    printf("%d %d %d\n", i, getpid(), getppid());
}

// Ex 6
int procuraLinha(int key, int c, int l[c]){

    for(int i = 0; i < c; i++)
        if(l[i] == key) return 1;
    return 0;
}

int procura(int key, int l, int c, int m[l][c]){

    for(int i = 0; i < l; i++){
        if(!fork()){
            _exit(procuraLinha(key,c,m[i]));
        }
    }
    for(int j = 0; j < l; j++){
        int status;
        wait(&status);
        if(!WIFEXITED(status)) return -1;
        if(WEXITSTATUS(status)) return 1;
    }
    return 0;
}

int main(){

    ex5(0);
    return 0;
}