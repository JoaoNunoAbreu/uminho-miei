#include <stdio.h>

void pascal (int p[], int N) {

    int linha,i;

    p[0] = 1;
    linha = 0;

    while (linha < N) {

        linha ++;
        p[linha] = 1; // para por 1 no fim
        for (i = (linha - 1); i > 0 ; i--)
            p[i] = p[i] + p[i-1];

    }
}

int main() {

    int x[5]; int i;
    pascal (x,5);

    for (int i = 0; i < 5; ++i)
        printf("%d", x[i]);

    printf("\n");
    return 0;

}

