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
:- dynamic servico/2.
:- dynamic ato/4.

%--------------------------------------------------------------
% Aplicação do PMF

-servico(Servico, Nome) :-
    nao(servico(Servico, Nome)),
    nao(excecao(servico(Servico, Nome))).

-ato(Ato, Prestador, Utente, Dia) :-
    nao(ato(Ato, Prestador, Utente, Dia)),
    nao(excecao(ato(Ato, Prestador, Utente, Dia))). 

%-------------------------------------------------------------------
% Extensão do predicado servico: servico, enfermeira -> {V,F,D}
% a)
%------ Conhecimento Perfeito


servico(ortopedia,amelia).
servico(obstetricia,ana).
servico(obstetricia,maria).
servico(obstetricia,mariana).
servico(geriatria,sofia).
servico(geriatria,susana).

%------ Conhecimento Imperfeito Incerto

excecao(servico(xpt007,teodora)).

%------ Conhecimento Imperfeito Interdito

excecao(servico(np9,zulmira)).
nulo(np9).

%Invariante
+servico(S,zulmira) :- (solucoes((SS,zulmira),(servico(SS,zulmira),nao(nulo(SS))),L),
    comprimento(L,C),
    C == 0).


%-------------------------------------------------------------------
% Extensão do predicado ato: ato,enfermeira,utente,data -> {V,F,D}
% b)
%------ Conhecimento Perfeito

ato(penso,ana,joana,sabado).
ato(gesso,amelia,jose,domingo).
ato(domicilio,susana,[joao,jose],segunda).
ato(sutura,[maria,mariana],josefa,[terca,sexta]).
ato(penso,ana,jacinta,[segunda,sexta]).

%------Conhecimento Imperfeito Incerto

excecao(ato(xpt017,mariana,joaquina,domingo)).
excecao(ato(domicilio,maria,xpt121,xpt251)).
excecao(ato(sutura,xpt313,josue,segunda)).


%------Conhecimento Imperfeito Impreciso

excecao(ato(domicilio,susana,X,segunda)) :- (X=Joao;X=Jose).
excecao(ato(sutura,X,josefa,Y)) :- (X=Maria;X=Mariana), (Y=terca;Y=sexta).
excecao(ato(penso,ana,jacinta,X)) :- (X=segunda;X=terca;X=quarta;X=quinta;X=sexta).

%-------------------------------------------------------------------
% Impede registos de atos medicos em feriados
% c)

feriado(domingo).

+ato(A,M,U,D)   ::(solucoes((A,M,U,D),(ato(A,M,U,D),feriado(D)),L),
                comprimento(L,C),
                C==0).

%-------------------------------------------------------------------
% Impede remoção de profissionais com atos registados
% d)

-servico(S,E)   :: (solucoes((A),ato(A,E,_,_),L),
                comprimento(L,N),
                N==0).


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