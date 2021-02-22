%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Ficha 5

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag(discontiguous_warnings,off).
:- set_prolog_flag(single_var_warnings,off).
:- set_prolog_flag(unknown,fail).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% Extensao do predicado filho: Filho,Pai -> {V,F}

filho(joao,jose).
filho(jose,manuel).
filho(carlos,jose).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado pai: Pai,Filho -> {V,F}

pai(P,F) :- filho(F,P).

pai(paulo,filipe).
pai(paulo,maria).


%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado avo: Avo,Neto -> {V,F}

neto(N,A) :- avo(A,N).
avo(A,N) :- pai(A,P),pai(P,N).

avo(antonio,nadia).
neto(nuno,ana).

% Exercício XVII
avo(A,N) :- descendente(N,A,2).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado masculino: Masculino -> {V,F}

masculino(joao).
masculino(jose).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado masculino: Feminino -> {V,F}

feminino(maria).
feminino(joana).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente -> {V,F}

descendente(X,Y) :- filho(X,Y).
descendente(X,Y) :- filho(X,Z),descendente(Z,Y).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado descendente: Descendente,Ascendente,Grau -> {V,F}

descendente(D,A,1) :- filho(D,A).
% descendente(D,A,G) :- filho(D,X), descendente(X,A,N), G is N+1

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado bisavo: Bisavo,Bisneto -> {V,F}

bisavo(BA,BN) :- pai(BA,A),avo(A,BN).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado bisavo: Trisavo,Trisneto -> {V,F}

trisavo(TA,TN) :- avo(TA,A),avo(A,TN).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Extensao do predicado bisavo: Tetravo,Tetraneto -> {V,F}

tetravo(TA,TN) :- avo(TA,A),bisavo(A,TN).

% ---------------------------------------------------------------------

insercao(Termo) :- assert(Termo).
insercao(Termo) :- retract(Termo), !, fail.

nao(Questao) :- Questao,!,fail.
nao(Questao).

demo(Questao,verdadeiro) :- Questao.
demo(Questao,falso) :- -Questao.
demo(Questao,desconhecido) :- nao(Questao), nao(-Questao).

% ------------ --------Resolução da ficha -------------------------------


% 1

+filho(F,P) :: (findall((F,P),(filho(F,P)),S),
                length(S,N),
                N == 1).

% 2

% 3

% 4

% 5

% 6

% 7

% 8

% 9

% 10