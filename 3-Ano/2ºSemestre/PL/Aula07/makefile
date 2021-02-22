#2.2. compilação separada, Linguagem dos parentesis / Folha 2. Ex 3.2

ex-xml:  ex-xml.l ex-xml.y
	flex ex-xml.l
	yacc -d ex-xml.y
	cc y.tab.c lex.yy.c -o ex-xml

#3 - alguma limpeza ...
clean:
	rm -f ex-xml lex.yy.c y.tab.c y.tab.h