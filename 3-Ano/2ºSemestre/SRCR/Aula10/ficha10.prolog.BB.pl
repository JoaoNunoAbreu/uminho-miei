% -------------------------------------------------------------
% Ficha 7

% -------------------------------------------------------------
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).
% -------------------------------------------------------------

% 1

caminho(G,X,Y,P) :- caminhoAux(G,X,[Y],P).
caminhoAux(_,X,[X|T],[X|T]).
caminhoAux(G,X,[Y|T],P) :-
    adjacente(Prox_nodo,Y,G),
    nao(member(Prox_nodo,[Y|T])),
    caminhoAux(G,X,[Prox_nodo,Y|T],P).

% -------------------------------------------------------------
% Extensao do meta-predicado nao: Questao -> {V,F}
 
nao(Questao) :-
    Questao, !, fail.
nao(Questao).

% -------------------------------------------------------------

cerca(X,Sup,Inf) :- Sup is X * 1.25, Inf is X * 0.75.

cerca2(X,Sup,Inf) :- Sup is X * 1.1, Inf is X * 0.9.

comprimento( S,N ) :-
    length( S,N ).

solucoes( X,Y,Z ) :- findall( X,Y,Z ).

evolucao( Termo ) :-
    solucoes( Invariante,+Termo::Invariante,Lista ),
    insercao( Termo ),
    teste( Lista ).

involucao( Termo ) :-
    solucoes( Invariante,-Termo::Invariante,Lista ),
    remocao( Termo ),
    teste( Lista ).