/* Objective function
max: 4 x04 + 4 x02 + 7 x23 + 2 x3f + 9 x42 + 9 x45 + 4 x5f + 5 x64 
+ 5 x68 + 4 x89 + 2 x9f + 8 x1011 + 7 x119 + 5 x610;*/

/* Restrições de Sentido
xi0 + xi6 = 1;
x04 + x02 - xi0 = 0;
x23 - x02 - x42 = 0;
x3f - x23 - x53 = 0;
x42 + x45 - x04 - x64 = 0;
x53 + x5f - x45 - x85 = 0;
x64 + x68 + x610 - xi6 = 0;
x85 + x89 - x68 - x108 = 0;
x9f - x89 - x119 = 0;
x108 + x1011 - x610 = 0;
x119 - x1011 = 0;
- x3f - x5f - x9f = -1;*/

/* função objectivo */
min: tf;

/* restrições */
arco_i0: t0 >= ti + 0;
arco_i6: t6 >= ti + 0;
arco_02: t2 >= t0 + 4;
arco_04: t4 >= t0 + 4;
arco_23: t3 >= t2 + 7;
arco_3f: tf >= t3 + 2;
arco_42: t2 >= t4 + 9;
arco_45: t5 >= t4 + 9;
arco_53: t3 >= t5 + 4;
arco_5f: tf >= t5 + 4;
arco_64: t4 >= t6 + 5;
arco_68: t8 >= t6 + 5;
arco_610: t10 >= t6 + 5;
arco_85: t5 >= t8 + 4;
arco_89: t9 >= t8 + 4;
arco_9f: tf >= t9 + 2;
arco_108: t8 >= t10 + 8;
arco_1011: t11 >= t10 + 8;
arco_119: t9 >= t11 + 7;


t2 + 7 <= t8 + 1000000 - 1000000y28;
t8 + 4 <= t2 + 1000000y28;

t11+7<=t8 + 1000000-1000000y118;
t8+4<=t11+10000000y118;

t11+7<=t2+1000000-1000000y112;
t2+7<=t11+1000000y112;

bin y28,y118,y112;