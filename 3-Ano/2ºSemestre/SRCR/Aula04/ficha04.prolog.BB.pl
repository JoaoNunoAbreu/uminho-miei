%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Ficha 4

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
:- set_prolog_flag(unknown,fail).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% 1

par(0).
par(X) :- Z is X-2,Z >= 0,par(Z).

% 2

impar(1).
impar(X) :- Z is X-2,Z >= 0,impar(Z).

% 3

natural(1).
natural(X) :- Z is X-1,Z >= 0,natural(Z).

% 4

inteiros(0).
inteiros(X) :- Z is X-1,Z >= 0,inteiros(Z).
inteiros(X) :- Z is X+1,Z =< 0,inteiros(Z).

% 5

divisao(_,0) :- !,fail.
divisao(0,_).
divisao(X,Y) :- Z is X-Y,Z >= 0,divisao(Z,Y).

divisoresAux(_,1,[1]).
divisoresAux(O,X,[X|L]) :- divisao(O,X),Z is X-1,Z > 0,divisoresAux(O,Z,L).
divisoresAux(O,X,L) :- \+(divisao(O,X)),Z is X-1,Z > 0,divisoresAux(O,Z,L).

divisores(X,Lista) :- divisoresAux(X,X,Lista).

% 6

primoAux(_,1).
primoAux(O,X) :- divisao(O,X),X \= O,X \= 1,!,fail.
primoAux(O,X) :- Z is X-1,Z > 0,primoAux(O,Z).

primo(X) :- primoAux(X,X).