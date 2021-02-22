#1 - testar o analisador lexico

testaALex: ex1.l
	flex ex1.l
	cc -o testaALex lex.yy.c

#2 - testar gramática 
#2.1 compilação com include (lex.yy.c)

ex1-inc:  ex1-inc.l ex1-inc.y
	flex ex1-inc.l
	yacc ex1-inc.y
	cc y.tab.c -o ex1-inc


#2.2. compilação separada

ex1-cs:  ex1-cs.l ex1-cs.y
	flex ex1-cs.l
	yacc -d ex1-cs.y
	cc y.tab.c lex.yy.c -o ex1-cs

#3 - alguma limpeza ...
clean:
	rm -f testaALex ex1-inc ex1-cs lex.yy.c y.tab.c y.tab.h
