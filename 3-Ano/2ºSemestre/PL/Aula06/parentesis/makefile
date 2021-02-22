#2.2. compilação separada, Linguagem dos parentesis / Folha 2. Ex 3.2

ex1-lp:  ex1-lp.l ex1-lp.y
	flex ex1-lp.l
	yacc -d ex1-lp.y
	cc y.tab.c lex.yy.c -o ex1-lp

#3 - alguma limpeza ...
clean:
	rm -f ex1-lp lex.yy.c y.tab.c y.tab.h