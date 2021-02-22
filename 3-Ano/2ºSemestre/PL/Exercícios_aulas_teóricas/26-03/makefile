E=ex6

$E: $E.y $E.l
	yacc $E.y
	flex $E.l
	cc y.tab.c -o $E

clean:
	rm -f y.tab.c lex.yy.c $E _.html
	rm -rd dir1
