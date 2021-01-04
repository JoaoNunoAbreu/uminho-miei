#include <stdio.h>

int factorial (int n) {
    int r;
    r = 1;
    while (n > 0) {
        r = r * n;
        n = n - 1;
    }
    return r;
}

int main () {
    
    int a,b;
    a = 4;
    b = factorial (a);
    printf("%d!=%d \n",a,b);
    return 0;

}