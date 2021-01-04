#include <stdio.h>

void crescente (int x) {

    int i,j,fixo;
    i = 0;
    j = fixo = 1;
    while (i < (x - 1)) {
        while (j != 0) {
            putchar ('#');
            j = j - 1;
        }
        putchar ('\n');
        i = i + 1;
        fixo = fixo + 1;
        j = fixo;
    }
}

void decrescente (int x) {

    int i,j;
    i = j = x;
    while (i != 0) {
        while (j != 0) {
            putchar ('#');
            j = j - 1;
        }
        putchar ('\n');
        i = i - 1;
        x = x - 1;
        j = x;
    }

}

int main () {

    int x,y;
    crescente (5);
    decrescente (5);
    return 0;

}