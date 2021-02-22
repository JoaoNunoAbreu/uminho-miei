%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Ficha 3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
:- set_prolog_flag(unknown,fail).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% 1

elem(X,[X|T]).
elem(X,[H|T]) :- elem(X,T).

% 2

mylength([],0).
mylength([H|T],Sum) :- mylength(T,N), Sum is N+1.

% 3

igual(X,[],0).
igual(X,[H|T],Sum) :- X == H, igual(X,T,N), Sum is N + 1.
igual(X,[H|T],Sum) :- X \= H ,igual(X,T,N), Sum is N.

diferentes([],0).
diferentes([H|T],Sum) :- igual(H,[H|T],ResIg), ResIg == 1, diferentes(T,ResDif), Sum is ResDif + 1.
diferentes([H|T],Sum) :- igual(H,[H|T],ResIg), ResIg > 1 , diferentes(T,ResDif), Sum is ResDif.

% 4

apaga1(X,[],[]).
apaga1(X,[X|T],T).
apaga1(X,[H|T],[H|Res]) :- X\= H, apaga1(X,T,Res).

% 5

apagaT(X,[],[]).
apagaT(X,[X|T],Res) :- apagaT(X,T,Res).
apagaT(X,[H|T],[H|Res]) :- X\= H, apagaT(X,T,Res).

% 6

noRepeatInsert(X,[],[X]).
noRepeatInsert(X,[H|T],[H|T]) :- elem(X,[H|T]).
noRepeatInsert(X,[H|T],[X,H|T]) :- nonmember(X,[H|T]).

% 7

concat([],L,L).
concat([H|T],L,[H|Res]) :- concat(T,L,Res).

% 8

inverter([],[]).
inverter([H|T],L) :- inverter(T,Res), concat(Res,[H],L).

% 9

sublista([],_).
sublista([H1|T1],[H2|T2]) :- H1 == H2, sublista(T1,T2).
sublista([H1|T1],[H2|T2]) :- H1 \= H2, sublista([H1|T1],T2).