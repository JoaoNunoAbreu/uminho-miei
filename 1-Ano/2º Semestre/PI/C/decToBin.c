#include <stdio.h>

void f(int x) {

    while (x>0) {
        if (x % 2 == 0)
             putchar ('0');
        else putchar ('1');
    x = x/2;
    }
    putchar ('\n');
}


int main() {
    f(5);
    return 0;
}