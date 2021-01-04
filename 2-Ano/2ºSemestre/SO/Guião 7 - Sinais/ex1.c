#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

volatile int count = 0;
volatile int segundos = 0;
volatile int fim = 0;

void sigint_handler(int sig){
    count++;
    char buf[100];
    int n = sprintf(buf,"Passaram %d segundos\n",segundos);
    write(1,buf,n);
}

void sigalrm_handler(int sig){
    segundos++;
    alarm(1);
}

void sigquit_handler(int sig){
    // Como se vai fazer isto só no fim do programa podemos usar printf
    //char buf[100];
    //int n = sprintf(buf,"Clicou ctrl-c %d vezes\n",count);
    //write(1,buf,n);
    fim = 1;
}

int main(){
    signal(SIGINT , sigint_handler);
    signal(SIGALRM, sigalrm_handler);
    signal(SIGQUIT, sigquit_handler);
    alarm(1);

    while(!fim)
        pause();

    printf("Clicou ctrl-c %d vezes\n",count);
    return 0;
}