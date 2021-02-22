%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Ficha 6

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
:- set_prolog_flag(unknown,fail).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% 1
 
voa(X) :- ave(X),nao(excecao(voa(X))).

excecao(voa(X))  :- avestruz(X).
excecao(voa(X))  :- pinguim(X).

% 2

-voa(X) :- mamifero(X),nao(excecao(-voa(X))).
-voa(X) :- excecao(voa(X)).
voa(X)  :- excecao(-voa(X)).

excecao(-voa(X)) :- morcego(X).

% 3

-voa(tweety).

% 4
 
ave(pitigui).

% 5

ave(X) :- canario(X).

% 6

ave(X) :- periquito(X).

% 7

canario(piupiu).

% 8

mamifero(silvestre).

% 9

mamifero(X) :- cao(X).

% 10

mamifero(X) :- gato(X).

% 11

cao(bobby).

% 12

ave(X) :- avestruz(X).

% 13

ave(X) :- pinguim(X).

% 14

avestruz(trux).

% 15

pinguim(pingu).

% 16

mamifero(X) :- morcego(X).

% 17

morcego(batemene).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado si: Questao,Resposta -> {V,F}
%                            Resposta = { verdadeiro,falso,desconhecido }
 
si(Questao,verdadeiro) :-
    Questao.
si(Questao,falso) :-
    -Questao.
si(Questao,desconhecido) :-
    nao(Questao),
    nao(-Questao).
 
%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do meta-predicado nao: Questao -> {V,F}
 
nao(Questao) :-
    Questao, !, fail.
nao(Questao).