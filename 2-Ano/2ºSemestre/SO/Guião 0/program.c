#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "person.h"

int main(){

    Person andre = new_person("Andre",20);

    printf("idade anterior andré %d\n", andre.age);
    person_change_age(&andre,30);
    printf("idade modificada andré %d\n", andre.age);

    Person new_andre = clone_person(&andre);

    person_change_age(&new_andre,40);
    printf("idade andre %d\n", andre.age);
    printf("idade new_andre %d\n", new_andre.age);

    destroy_person(&new_andre);
    destroy_person(&andre);

    return 0;

}
