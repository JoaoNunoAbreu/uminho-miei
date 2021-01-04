#include <unistd.h>

int main(){
    
    for(int i = 0; i < 10; i++){
        write(1,"Programa 2\n",12);
    }
}