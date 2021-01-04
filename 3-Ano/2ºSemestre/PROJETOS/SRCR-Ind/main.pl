%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SIST. REPR. CONHECIMENTO E RACIOCINIO - MiEI/3

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% Trabalho Individual
% João Nuno Abreu A84802

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: Declaracoes iniciais

:- set_prolog_flag( discontiguous_warnings,off ).
:- set_prolog_flag( single_var_warnings,off ).
:- set_prolog_flag( unknown,fail ).

%--------------------------------- - - - - - - - - - -  -  -  -  -   -
% SICStus PROLOG: definicoes iniciais

:- set_prolog_flag(toplevel_print_options,[quoted(true),numbervars(true),portrayed(true),max_depth(1000)]).
%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% Includes

:- include('paragem.pl').
:- include('adjacencias.pl').
:- include('auxiliares.pl').

%--------------------------------- - - - - - - - - - -  -  -  -  -   -

% Queries

% 1 - Calcular um trajeto entre dois pontos (Depth-first)

% 1 (Depth-first)

query1_df(Node,Ending,L) :- findall(Solution,solve_df_1(Node, Ending, Solution),L).

solve_df_1(Node, Ending, Solution) :- depthfirst1([], Node, Ending,Solution,[]).
solve_df_1(_,_,([],[])).

depthfirst1(Path, Node, Node,(D,L),Lista) :- inverso([Node|Path],D),inverso(Lista,L).
depthfirst1(Path, Node, Ending,Sol,Lista) :-
    Node \= Ending,
    move(Node, Node1, _,Carreira),
    \+ member(Node1, Path),
    depthfirst1([Node|Path], Node1, Ending,Sol,[Carreira|Lista]).


/**
  * 1 (Breadth-first)
  */

query1_bf(Node,Ending,L) :- findall(Solution,solve_bf_1(Node, Ending, Solution),L).

solve_bf_1(Start,Ending, Res) :- breadthfirst([[Start]],Ending, Res).
solve_df_1(_,_,([],[])).

breadthfirst([[Node|Path]|_],Node, D) :- inverso([Node|Path],D).
breadthfirst([[Node|Path]|Paths],Ending, Solution) :-
    Node \= Ending,
    extend([Node|Path], NewPaths),
    append(Paths, NewPaths, Paths1),
    breadthfirst(Paths1, Ending,Solution).

extend([Node|Path], NewPaths) :-
    findall([NewNode, Node|Path],
        (move(Node, NewNode,_,_),
        \+ member(NewNode,[Node|Path])),
        NewPaths).
extend(Path,[]).


/** 
  * 2 - Selecionar apenas algumas das operadoras de transporte para um determinado percurso
  */

solve_df_2(Node, Ending, Operadoras,Solution) :- depthfirst2([], Node, Ending,Operadoras,Solution,[]).
solve_df_2(_,_,_,([],[])).
depthfirst2(Path, Node, Node,_,(D,L),Lista) :- inverso([Node|Path],D),inverso(Lista,L).
depthfirst2(Path, Node, Ending,Operadoras,Sol,Lista) :-
    Node \= Ending,
    move(Node, Node1, _,Carreira),
    paragem(Node,_,_,_,_,_,Operador,_,_,_,_),
    member(Operador,Operadoras),
    paragem(Node1,_,_,_,_,_,Operador1,_,_,_,_),
    member(Operador1,Operadoras),
    \+ member(Node1, Path),
    depthfirst2([Node|Path], Node1, Ending,Operadoras,Sol,[Carreira|Lista]).

/**
  * 3 - Excluir um ou mais operadores de transporte para o percurso;
  */

solve_df_3(Node, Ending, Operadoras,Solution) :- depthfirst3([], Node, Ending,Operadoras,Solution,[]).
solve_df_3(_,_,_,([],[])).
depthfirst3(Path, Node, Node,_,(D,L),Lista) :- inverso([Node|Path],D),inverso(Lista,L).
depthfirst3(Path, Node, Ending,Operadoras,Sol,Lista) :-
    Node \= Ending,
    move(Node, Node1, _,Carreira),
    paragem(Node,_,_,_,_,_,Operador,_,_,_,_),
    \+ member(Operador,Operadoras),
    paragem(Node1,_,_,_,_,_,Operador1,_,_,_,_),
    \+ member(Operador1,Operadoras),
    \+ member(Node1, Path),
    depthfirst3([Node|Path], Node1, Ending,Operadoras,Sol,[Carreira|Lista]).

/**
  * 4 - Identificar quais as paragens com o maior número de carreiras num determinado percurso.
  */

solve_df_4(Node, Ending, Solution,ListaNums) :- depthfirst4([], Node, Ending,Solution,ListaNums,[],[]).
solve_df_4(_,_,([],[]),_).

depthfirst4(Path, Node, Node,(D,L),ListaNums,Lista,Nums) :- 
    inverso([Node|Path],D),
    inverso(Lista,L),
    paragem(Node,_,_,_,_,_,_,CarreirasL,_,_,_),
    length(CarreirasL,Num),
    inverso([Num|Nums],ListaNums).
depthfirst4(Path, Node, Ending,Sol,ListaNums,Lista,Nums) :-
    Ending \= Node,
    move(Node, Node1, _,Carreira),
    \+ member(Node1, Path),
    paragem(Node,_,_,_,_,_,_,CarreirasL,_,_,_),
    length(CarreirasL,Num),
    depthfirst4([Node|Path], Node1, Ending,Sol,ListaNums,[Carreira|Lista],[Num|Nums]).

/**
  * 5 - Escolher o menor percurso (usando critério menor número de paragens);
  */

query5_df(Nodo, Ending, S, Custo) :- findall((SS, CC), solve_df_5(Nodo, Ending,SS, CC), L), minimo(L, (S, Custo)).
aux5(Node,Ending,L) :- findall((SS,CC),solve_df_5(Node, Ending, SS,CC),L).

solve_df_5(Node, Ending, Solution,C) :- depthfirst5([], Node, Ending,Solution,C).
depthfirst5(Path, Node, Node,D,1) :- inverso([Node|Path],D).
depthfirst5(Path, Node, Ending,Sol,C) :-
    Ending \= Node,
    move(Node, Node1, _,_),
    \+ member(Node1, Path),
    depthfirst5([Node|Path], Node1, Ending,Sol,C2), C is C2+1.


/**
  * 6 - Escolher o percurso mais rápido (usando critério da distância);
  */

query6_df(Nodo, Ending, S, Custo) :- findall((SS, CC), solve_df_6(Nodo, Ending,SS, CC), L), minimo(L, (S, Custo)).

solve_df_6(Node, Ending, Solution,C) :- depthfirst6([], Node, Ending,Solution,C).
depthfirst6(Path, Node, Node,D,0) :- inverso([Node|Path],D).
depthfirst6(Path, Node, Ending,Sol,C) :-
    Ending \= Node,
    move(Node, Node1, Dist,_),
    \+ member(Node1, Path),
    depthfirst6([Node|Path], Node1, Ending,Sol,C2), C is C2+Dist.

% 6 gulosa

estima(Nodo,Destino,Estima) :- distance(Nodo,Destino,Estima).

distance(N1,N2,D) :- lat(N1,Lat1),lon(N1,Long1),
                     lat(N2,Lat2),lon(N2,Long2),
                     D is sqrt((Long2-Long1)*(Long2-Long1) + (Lat2-Lat1)*Lat2-Lat1).

lat(N,Lat) :- paragem(N,Lat,_,_,_,_,_,_,_,_,_).
lon(N,Lon) :- paragem(N,_,Lon,_,_,_,_,_,_,_,_).

resolve_gulosa(Nodo,Ending,Caminho/Custo):- estima(Nodo,Ending,Estima),
                                     agulosa([[Nodo]/0/Estima],InvCaminho/Custo/_,Ending),
                                     inverso(InvCaminho,Caminho).

agulosa(Caminhos,Caminho,Ending):- obtem_melhor_g(Caminhos,Caminho),
                            Caminho = [Nodo|_]/_/_,Nodo is Ending.

agulosa(Caminhos,SolucaoCaminho,Ending):-obtem_melhor_g(Caminhos,MelhorCaminho),
                                  seleciona(MelhorCaminho,Caminhos,OutrosCaminhos),
                                  expande_gulosa(MelhorCaminho,ExpCaminhos,Ending),
                                  append(OutrosCaminhos,ExpCaminhos,NovoCaminhos),
                                  agulosa(NovoCaminhos,SolucaoCaminho,Ending).

obtem_melhor_g([Caminho],Caminho):- !.

obtem_melhor_g([Caminho1/Custo1/Est1,_/Custo2/Est2|Caminhos],MelhorCaminho):- Est1 =< Est2,
                                                                              !,
                                                                              obtem_melhor_g([Caminho1/Custo1/Est1|Caminhos],MelhorCaminho).

                                                                            
obtem_melhor_g([_|Caminhos],MelhorCaminho):- obtem_melhor_g(Caminhos,MelhorCaminho).

expande_gulosa(Caminho,ExpCaminhos,Ending):- findall(NovoCaminho,adjacente(Caminho,NovoCaminho,Ending),ExpCaminhos).

adjacente([Nodo|Caminho]/Custo/_, [ProxNodo,Nodo|Caminho]/NovoCusto/Est,Ending) :-
    move(Nodo, ProxNodo, PassoCusto,_),
    \+ member(ProxNodo, Caminho),
    NovoCusto is Custo + PassoCusto,
    estima(ProxNodo,Ending, Est).

/** 
  * 7 - Escolher o percurso que passe apenas por abrigos com publicidade;
  */

solve_df_7(Node, Ending, Solution) :- depthfirst7([], Node, Ending,Solution,[]).
solve_df_7(_,_,([],[])).

depthfirst7(Path, Node, Node,(D,L),Lista) :- inverso([Node|Path],D),inverso(Lista,L).
depthfirst7(Path, Node, Ending,Sol,Lista) :-
    Ending \= Node,
    move(Node, Node1, _,Carreira),
    \+ member(Node1, Path),
    paragem(Node,_,_,_,_,Publi,_,_,_,_,_),
    Publi == "Yes",
    paragem(Node1,_,_,_,_,Publi1,_,_,_,_,_),
    Publi1 == "Yes",
    depthfirst7([Node|Path], Node1, Ending,Sol,[Carreira|Lista]).

/** 
  * 8 - Escolher o percurso que passe apenas por paragens abrigadas;
  */

solve_df_8(Node, Ending, Solution) :- depthfirst8([], Node, Ending,Solution,[]).
solve_df_8(_,_,([],[])).

depthfirst8(Path, Node, Node,(D,L),Lista) :- inverso([Node|Path],D),inverso(Lista,L).
depthfirst8(Path, Node, Ending,Sol,Lista) :-
    Ending \= Node,
    move(Node, Node1, _,Carreira),
    \+ member(Node1, Path),
    paragem(Node,_,_,_,Abrigo,_,_,_,_,_,_),
    Abrigo \= "Sem Abrigo",
    paragem(Node1,_,_,_,Abrigo1,_,_,_,_,_,_),
    Abrigo1 \= "Sem Abrigo",
    depthfirst8([Node|Path], Node1, Ending,Sol,[Carreira|Lista]).

/**
  * 9 - Escolher um ou mais pontos intermédios por onde o percurso deverá passar.
  */
