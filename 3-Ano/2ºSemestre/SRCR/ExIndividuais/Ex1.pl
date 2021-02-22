%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Programacao em logica estendida
% Representacao de conhecimento imperfeito

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- op( 900,xfy,'::' ).
:- dynamic data/4.
:- dynamic pais/3.


%--------------------------------- - - - - - - - - - -  -  nao
nao(Q) :- Q,!,fail.
nao(Q).

%--------------------------------- - - - - - - - - - -  -  solver
solver(Q,verdadeiro) :- Q.
solver(Q,falso) :- -Q.
solver(Q,desconhecido) :- nao(Q),nao(-Q).

%--------------------------------- - - - - - - - - - -  -  soluções
solucoes( X,Y,Z ) :- findall( X,Y,Z ).

%--------------------------------- - - - - - - - - - -  -  comprimento
comprimento( S,N ) :- length( S,N ).

%--------------------------------- - - - - - - - - - -  -  Extensao do predicado data: X,Y,Z,W -> {V,F}
-data(X,Y,Z,W) :- nao(data(X,Y,Z,W)), nao(excecao(X,Y,Z,W)).

%--------------------------------- - - - - - - - - - -  -  Extensao do predicado pais: X,Y,Z -> {V,F}
-pais(X,Y,Z) :- nao(pais(X,Y,Z)), nao(excecao(pais(X,Y,Z))).

%--------------------------------- - - - - - - - - - -  - I
data('Ana','01','01','2010').
pais('Abel','Alice','Ana'). 

%--------------------------------- - - - - - - - - - -  - II
data('Anibal','02','01','2010').
pais('Antonio','Alberta','Anibal').

%--------------------------------- - - - - - - - - - -  - III
data('Berta','02','01','2010').
data('Berto','02','02','2010').
pais('Bras','Belem','Berta').
pais('Bras','Belem','Berto').

%--------------------------------- - - - - - - - - - -  - IV
data('Catia','03','03','2010').

%--------------------------------- - - - - - - - - - -  - V
% Imperfeito Impreciso
excecao(pais('Celso','Catia','Crispim')).
excecao(pais('Caio','Catia','Crispim')).


%--------------------------------- - - - - - - - - - -  - VI
% Imperfeito Incerto
data('Danilo','04','04','2010').
pais('Danilo',alguem1,'Daniel').
excecao(pais(PAI,MAE,FILHO)) :- pais(PAI,alguem1,FILHO).

%--------------------------------- - - - - - - - - - -  - VII
% Imperfeito Impreciso
excecao(data('Eurico','05','05','2010')).
excecao(data('Eurico','15','05','2010')).
excecao(data('Eurico','25','05','2010')).
pais('Elias','Elsa','Eurico').

%--------------------------------- - - - - - - - - - -  - VIII
% Imperfeito Impreciso e incerto
excecao(pais('Fausto',alguem2,'Fabia')).
excecao(pais('Fausto',alguem2,'Octavia')).
excecao(pais(PAI,MAE,FILHO)) :- pais(PAI,alguem2,FILHO).

%--------------------------------- - - - - - - - - - -  - IX (por fazer)
% Imperfeito Interdito(por fazer)
excecao(data(P,D,M,A)) :- data(P,x,p,t).
pais('Guido','Guida','Golias').
data('Golias',x,p,t):
nulointerditoData(P,x,p,t).
+data(P,D,M,A) :: (solucoes( (Golias,Ds,Ms,As),(data(Golias,Ds,Ms,As),nao(nulointerdito(D,M,A))),S ),
                  comprimento( S,N ), N == 0 
                  ).

%--------------------------------- - - - - - - - - - -  - X
% Imperfeito Incerto
-data('Helder','08','08','2010').
data('Helder',dia,mes,ano).
excecao(data(NOME,DIA,MES,ANO)) :- data(NOME,dia,mes,ano).

%--------------------------------- - - - - - - - - - -  - XI
% Imperfeito Impreciso
excecao(data('Ivo',D,'06','2010')) :- D >= 1 , D =< 15.

% Invariante pessoa só pode ter um par de pais
+pais(P,M,F) :: (solucoes(F ,(pais(P,M,F)), S),comprimento(S,N),N == 1).

% Invariante pessoa só pode ter uma data de nascimento
+data(P,D,M,A) :: (solucoes(P ,(data(P,D,M,A)), S),comprimento(S,N),N == 1).


