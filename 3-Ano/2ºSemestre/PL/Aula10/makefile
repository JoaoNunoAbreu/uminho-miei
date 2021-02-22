SHELL=bash
E=calculadora3
I=exemplo3.txt

$E: $E.y $E.l
	yacc $E.y
	flex $E.l
	cc y.tab.c -o $E

#fazer make teste em vez de executar só o programa
#faz com que mostre o output do flex para cada linha
teste: $E $I
	./$E < $I| paste -d : $I - | column -t -s :

install: $a
	cp $a /usr/local/bin/

clean:
	rm -f y.tab.c lex.yy.c $E
