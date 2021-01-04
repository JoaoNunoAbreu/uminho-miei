#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <stdio.h>
#include <string.h>

// ------ (1) ------

void ex1(){
    execl("/bin/ls","ls","-l",(char*) 0);
}

void ex1v2(){
    execlp("ls","ls","-l",(char*) 0);
}

void ex1v3(){
    char* a[] = {"ls","-l",(char*) 0};
    execvp("ls",a);
}

// ------ (2) ------

void ex2(){
    if(!fork()){
        execl("/bin/ls","ls","-l",(char*) 0);
        _exit(0);
    }
}

// ------ (3) ------

void ex3(char* argv[]){
    for(int i = 0; argv[i]; i++)
        printf("%s\n",argv[i]);
}

// ------ (4) ------

void ex4(){
    execl("./ex3","ola","teste",(char*) 0);
}

void ex4v2(char* argv[]){
    execvp("./ex3",argv);
}

// ------ (5) ------

void ex5(int argc, char* argv[]){
    for(int i = 1; i < argc; i++){
        if(!fork()){
            execlp(argv[i],argv[i],(char*) 0);
            _exit(0);
        }
    }
    for(int i = 1; i < argc; i++){ 
        wait(0);  
    }
}

// ------ (6) ------

char** tokenizeArtigoDyn(char* artigo, int* tamanho, int quantos){
    
    char** artigos = (char**) malloc(quantos * sizeof(char*));
    char* temp = strdup(artigo);
    char* token = strtok(temp," ");
    while(token != NULL){
        artigos[*tamanho] = strdup(token);
        token = strtok(NULL," ");
        *tamanho = *tamanho + 1;
    }
    return artigos;
}

int contaPalavras(int N, char str[N]){
    int res = 1;
    for(int i = 0; i < N; i++)
        if(str[i] == ' ') res++;
    return res;
}

int ex6v1(char* s){
    pid_t p = fork();
    int tam = 0;
    if(p == -1) return -1;
    if(!p){
        s = strdup(s);
        char** a = tokenizeArtigoDyn(s,&tam,contaPalavras(strlen(s),s));
        execvp(a[0],a);
        _exit(127); // execvp falhar
    }
    int status;
    waitpid(p,&status,0);
    if(WIFEXITED(status)) return WEXITSTATUS(status);
    return -1;
}

int main(int argc, char* argv[]){

    ex6v1("ls -l");
	return 0;
}