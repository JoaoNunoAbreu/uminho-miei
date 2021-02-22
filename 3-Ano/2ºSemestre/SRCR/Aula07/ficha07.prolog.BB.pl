% -------------------------------------------------------------
% Ficha 7

% -------------------------------------------------------------
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

% -------------------------------------------------------------
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).
:- dynamic jogo/3.


% 1 Normal

jogo(1,aa,500).  

% 2 incerto

jogo(2,bb,xpto0123).
excecao(jogo(Jogo,Arbitro,Ajudas)) :- jogo(Jogo,Arbitro,xpto0123).
nuloIncerto(xpto0123).

% 3 impreciso

jogo(3,cc,pagCC).
excecao(jogo(Jogo,Arbitro,Ajudas)) :- jogo(Jogo,Arbitro,pagCC).
excecao(jogo(3,cc,500)).
excecao(jogo(3,cc,2500)).

% 4 impreciso

-jogo(4, dd, Ajudas) :- Ajudas < 250, Ajudas > 750.
excecao(jogo(4,dd,Ajudas)) :- Ajudas >= 250, Ajudas =< 750. 

% 5 interdito

jogo(5,ee,pagEE).
excecao(jogo(Jogo,Arbitro,Ajudas)) :- jogo(Jogo,Arbitro,pagEE).
nuloIncerto(pagEE).

% 6 impreciso

jogo(6,ff,250).
excecao(jogo(6,ff,Ajudas)) :- Ajudas > 5000.

% 7 incerto

-jogo(7,gg,2500).
jogo(7,gg,xpto).
excecao(jogo(Jogo,Arbitro,Ajudas)) :- jogo(Jogo,Arbitro,xpto).

% 8 impreciso

jogo(8,hh,1000).
excecao(jogo(8,hh,Ajudas)) :-
    cerca(1000,Csup,Cinf),
    Ajudas >= Cinf, Ajudas =< Csup.

% 9 impreciso

excecao(jogo(9,ii,Ajudas)) :-
    cerca2(3000,Csup,Cinf),
    Ajudas >= Cinf, Ajudas =< Csup.

% 10

+jogo(A,J,AJ) :: (solucoes((A),(jogo(A,J,AJ)),S),
                 comprimento(S,N),
                 N==1). 

% 11

+jogo(A,J,AJ) :: (solucoes((Js),(jogo(A,Js,AJ)),S),
                 comprimento(S,N),
                 N=<3). 

% 12

head([H|T],H).

-seguido([]).
seguido([H|T]) :- head(T,P), H =:= P-1.
seguido([H|T]) :- head(T,P), H \= P-1, seguido(T).

+jogo(Jogo, Arbitro, Ajudas) :: (solucoes(Jogos, jogo(Jogos, Arbitro, _), S), nao(seguido(S))).

% -------------------------------------------------------------
% Extensao do meta-predicado si: Questao,Resposta -> {V,F}
%                            Resposta = { verdadeiro,falso,desconhecido }
 
si(Questao,verdadeiro) :-
    Questao.
si(Questao,falso) :-
    -Questao.
si(Questao,desconhecido) :-
    nao(Questao),
    nao(-Questao).
 
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