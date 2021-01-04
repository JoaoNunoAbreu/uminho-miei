#include <stdio.h>

void quadrado (int n) {

    int l,c;
    l = 0;
    while (l < n) {
        c = 0;
        while (c < n) {
            putchar ('#');
            c = c + 1;

        }
        putchar ('\n');
        l = l + 1;
    }
}

int main () {

    quadrado (5);
    return 0;
}

/* #include <stdio.h>


void linha (int n) {

    int l;
    l = 0;
    while (l < n) {
        putchar ('#');
        l = l + 1;
    }
}

void coluna (int n) {

    int c;
    while (c < n){
        linha (5);
        putchar ('\n');
        c = c + 1;
    }

}

int main () {

    coluna (5);
    return 0;
}
*/
