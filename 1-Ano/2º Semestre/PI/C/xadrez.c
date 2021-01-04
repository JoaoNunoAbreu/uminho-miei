#include <stdio.h>

void tabXadrez (int n) {

    int l,c;
    for (l = 0; l < n ; ++ l) {

        if (l % 2 == 0) 
            { for (c = 0; c < n ; ++ c)
                if (c % 2 == 0)
                    putchar ('#');
                    else putchar ('_');
                putchar ('\n');
            }

        else { for (c = 0; c < n ; ++ c)
                if (c % 2 == 0)
                    putchar ('_');
                    else putchar ('#');
                putchar ('\n');
        }
    }
}

int main() {

    tabXadrez (5);
    return 0;
}