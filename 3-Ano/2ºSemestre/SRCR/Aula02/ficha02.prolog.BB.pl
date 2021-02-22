%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Ficha2

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
:- set_prolog_flag(unknown,fail).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% 1

soma2(X,Y) :- G is X+Y,write(G).

% 2

soma3(X,Y,Z) :- G is X+Y+Z,write(G).

% 3

soma([],0).
soma([H|T],Sum) :- soma(T,N),Sum is N+H.

% 4

op2(X,Y,Op) :- (Op == '+') -> G is X+Y, write(G).
op2(X,Y,Op) :- (Op == '-') -> G is X-Y, write(G).
op2(X,Y,Op) :- (Op == '*') -> G is X*Y, write(G).
op2(X,Y,Op) :- (Op == '/') -> G is X/Y, write(G).

% 5

opL([],0,'+').
opL([],1,'*').
opL([H|T],Sum,Op) :- (Op == '+') -> opL(T,N,Op),Sum is N+H.
opL([H|T],Sum,Op) :- (Op == '*') -> opL(T,N,Op),Sum is N*H.

% 6

max2(X,Y) :- X>Y -> write(X).
max2(X,Y) :- X<Y -> write(Y).

% 7

max3(X,Y,Z) :- (X>Y,X>Z) -> write(X).
max3(X,Y,Z) :- (Y>X,Y>Z) -> write(Y).
max3(X,Y,Z) :- (Z>X,Z>Y) -> write(Z).

% 8

max([H],H).
max([H|T],Maior) :- max(T,Maior), Maior >= H.
max([H|T],H) :- max(T,Maior), H > Maior.

% 9

min2(X,Y) :- (X>Y) -> write(X).
min2(X,Y) :- (X<Y) -> write(Y).

% 10

min3(X,Y,Z) :- (X<Y,X<Z) -> write(X).
min3(X,Y,Z) :- (Y<X,Y<Z) -> write(Y).
min3(X,Y,Z) :- (Z<X,Z<Y) -> write(Z).

% 11

min([H],H).
min([H|T],Menor) :- min(T,Menor), Menor =< H.
min([H|T],H) :- min(T,Menor), Menor > H.

% 12

media(L,Res) :- soma(L,Sum),length(L,Tam),Res is Sum / Tam.

% 13

insertC(X,[],[X]).
insertC(X,[H|T],[X,H|T]) :- X =< H.
insertC(X,[H|T],[H|L]) :- insert(X,T,L).

sortC([],[]).
sortC([H|T],Res) :- sortC(T,Ord),insertC(H,Ord,Res).

% 14

insertD(X,[],[X]).
insertD(X,[H|T],[X,H|T]) :- X > H.
insertD(X,[H|T],[H|L]) :- insertD(X,T,L).

sortD([],[]).
sortD([H|L],Res) :- sortD(L,Ord), insertD(H,Ord,Res).

% 15

nEmpty([],0).
nEmpty([H|L],Sum) :- nEmpty(L,Res), H == [], Sum is Res + 1.
nEmpty([H|L],Sum) :- nEmpty(L,Res), Sum is Res.

% 16

neg(X):- \+X.
