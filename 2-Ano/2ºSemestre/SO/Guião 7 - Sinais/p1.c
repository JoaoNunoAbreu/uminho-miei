#include <unistd.h>

int main(){
    
    for(int i = 0; i < 10; i++){
        write(1,"Programa 1\n",12);
    }
    return 0;
}