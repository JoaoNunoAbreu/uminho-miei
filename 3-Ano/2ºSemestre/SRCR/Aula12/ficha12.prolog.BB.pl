% -------------------------------------------------------------
% Ficha 12

% -------------------------------------------------------------
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

% -------------------------------------------------------------


% --------------------------------------------------------------
si(Questao,verdadeiro) :-
    Questao.
si(Questao,falso) :-
    -Questao.
si(Questao,desconhecido) :-
    nao(Questao),
    nao(-Questao).

pertence(X,Y) :- member(X,Y).