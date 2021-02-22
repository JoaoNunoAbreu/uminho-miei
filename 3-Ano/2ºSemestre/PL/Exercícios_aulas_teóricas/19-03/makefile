ex2: ex2.y ex2.l
	yacc ex2.y
	flex ex2.l
	cc y.tab.c -o ex2

ex1: ex1.y
	yacc ex1.y
	cc y.tab.c -o ex1

automato.pdf: ex1.y
	yacc -g ex1.y
	dot -Tpdf y.dot > automato.pdf
	xpdf _.pdf

clean:
	rm -f y.tac.c lex.yy.c ex1 ex2