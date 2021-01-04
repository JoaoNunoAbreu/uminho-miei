#include <stdio.h>

float multInt (int n, float m) {

    int i; float r;

    r = 0;

    for (i = 0; i < n; i++)
        r = r + m;

    return r;
}

int main() {

    float x;

    x = multInt (5,6);

    printf("%f\n", x);

    return 0;
}