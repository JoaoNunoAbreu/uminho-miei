module Ficha7 where

data BTree a = Empty
            | Node a (BTree a) (BTree a)
                deriving (Show)

t1 :: Num a => BTree a
t1 = (Node 3 (Node 2 (Node 1 Empty Empty) Empty) (Node 5 (Node 4 Empty (Node 6 Empty Empty)) Empty))

-- 1

--(a)

altura :: BTree a -> Int
altura Empty = 0
altura (Node x l r) = 1 + (max (altura l) (altura r))

-- (b)

contaNodos :: BTree a -> Int
contaNodos Empty = 0
contaNodos (Node x l r) = 1 + contaNodos l + contaNodos r

-- (c)

folhas :: BTree a -> Int
folhas Empty = 0
folhas (Node _ Empty Empty) = 1
folhas (Node x l r) = folhas l + folhas r

-- (d)

prune :: Int -> BTree a -> BTree a
prune 0 _ = Empty
prune i (Node x l r) = (Node x (prune (i-1) l) (prune (i-1) r))

-- (e)

path :: [Bool] -> BTree a -> [a]
path _ Empty = []
path [] (Node x _ _) = [x]
path (h:t) (Node x l r) = if h then x : path t r else x : path t l

-- (f)

mirror :: BTree a -> BTree a
mirror Empty = Empty
mirror (Node x l r) = (Node x (mirror r) (mirror l))

-- (g)

zipWithBT :: (a -> b -> c) -> BTree a -> BTree b -> BTree c
zipWithBT f Empty _ = Empty
zipWithBT f (Node x1 l1 r1) (Node x2 l2 r2) = (Node (f x1 x2) (zipWithBT f l1 l2) (zipWithBT f r1 r2))

-- (h)

unzipBT :: BTree (a,b,c) -> (BTree a,BTree b,BTree c)
unzipBT Empty = (Empty,Empty,Empty)
unzipBT (Node (a,b,c) lnode rnode) =  (Node a q r, Node b w t, Node c e y) where
    (q,w,e) = unzipBT lnode 
    (r,t,y) = unzipBT rnode

-- 2

-- (a)

minimo :: Ord a => BTree a -> a
minimo (Node x Empty _) = x 
minimo (Node x l r) = minimo l

-- (b)

semMinimo :: Ord a => BTree a -> BTree a
semMinimo Empty = Empty
semMinimo (Node x l r) = if x == minimo (Node x l r)
    then Empty
    else (Node x (semMinimo l) r)

-- (c)

minSmin :: Ord a => BTree a -> (a,BTree a)
minSmin (Node a Empty rnode) = (a,rnode)
minSmin (Node x lnode rnode) = (a, Node x b rnode)
                                where (a,b) = minSmin lnode

-- (d)

-- mal nas soluções

-- 3

t2 :: BTree Aluno
t2 = (Node (3,"Joao",ORD,Aprov 20) (Node (2,"Nuno",TE,Aprov 15) (Node (1,"Abreu",MEL,Rep) Empty Empty) Empty) (Node (5,"Cardoso",ORD,Faltou) (Node (4,"Goncalves",TE,Faltou) Empty Empty) (Node (6,"de",MEL,Aprov 10) Empty Empty)))

type Aluno = (Numero,Nome,Regime,Classificacao)
type Numero = Int
type Nome = String
data Regime = ORD | TE | MEL deriving Show
data Classificacao = Aprov Int
                   | Rep
                   | Faltou
                    deriving Show
type Turma = BTree Aluno -- ´arvore bin´aria de procura (ordenada por n´umero)

-- (a)

inscNum :: Numero -> Turma -> Bool
inscNum x Empty = False
inscNum x (Node (n,_,_,_) l r) | x < n = inscNum x l
                               | x > n = inscNum x r
                               | otherwise = True

-- (b)

inscNome :: Nome -> Turma -> Bool
inscNome x Empty = False
inscNome x (Node (_,n,_,_) l r) | x == n = True
                                | otherwise = inscNome x l || inscNome x r

-- (c)

trabEst :: Turma -> [(Numero,Nome)]
trabEst Empty = []
trabEst (Node (num,nome,TE,_) l r) = trabEst l ++ [(num,nome)] ++ trabEst r
trabEst (Node (num,nome,_,_) l r) = trabEst l ++ trabEst r

-- (d)

nota :: Numero -> Turma -> Maybe Classificacao
nota n Empty = Nothing
nota n (Node (num,_,_,clas) l r) | n == num = Just clas
                                 | n <  num = nota n l
                                 | n >  num = nota n r

-- (e)

percFaltas :: Turma -> Float
percFaltas Empty = 0
percFaltas t = lengthFaltou t / lengthTurmas t

lengthTurmas :: Turma -> Float
lengthTurmas Empty = 0
lengthTurmas (Node _ l r) = 1 + lengthTurmas l + lengthTurmas r

lengthFaltou :: Turma -> Float
lengthFaltou Empty = 0
lengthFaltou (Node (_,_,_,clas) l r) = if testaFaltar clas
    then 1 + lengthFaltou l + lengthFaltou r
    else lengthFaltou l + lengthFaltou r

testaFaltar :: Classificacao -> Bool
testaFaltar Faltou = True
testaFaltar _ = False 

-- (f)

mediaAprov :: Turma -> Float
mediaAprov Empty = 0
mediaAprov t = (realToFrac (sum (sacaAsNotas (listaNotas t)))) / lengthAprovados t

sacaAsNotas :: [Classificacao] -> [Int]
sacaAsNotas [] = []
sacaAsNotas ((Aprov x):t) = x : sacaAsNotas t

listaNotas :: Turma -> [Classificacao]
listaNotas Empty = []
listaNotas (Node (_,_,_,clas) l r) = if testaAprov clas
    then [clas] ++ listaNotas l ++ listaNotas r
    else listaNotas l ++ listaNotas r

testaAprov :: Classificacao -> Bool
testaAprov (Aprov x) = True
testaAprov _ = False

lengthAprovados :: Turma -> Float
lengthAprovados Empty = 0
lengthAprovados (Node (_,_,_,clas) l r) = if testaAprov clas
    then 1 + lengthAprovados l + lengthAprovados r
    else lengthAprovados l + lengthAprovados r

-- (g)

aprovAv :: Turma -> Float
aprovAv t = a / b
    where
        (a,b) = contaAvaliacoes t

contaAvaliacoes :: Turma -> (Float,Float)
contaAvaliacoes Empty = (0,0)
contaAvaliacoes (Node (_,_,_,Aprov x) lnode rnode) = (a+1,b+1)
                                        where (a,b) = (c+e,d+f)
                                              (c,d) = contaAvaliacoes lnode
                                              (e,f) = contaAvaliacoes rnode
contaAvaliacoes (Node (_,_,_, Rep) lnode rnode) = (a,b+1)
                                        where (a,b) = (c+e,d+f)
                                              (c,d) = contaAvaliacoes lnode
                                              (e,f) = contaAvaliacoes rnode       
contaAvaliacoes (Node (_,_,_, Faltou) lnode rnode) = (a,b)
                                        where (a,b) = (c+e,d+f)
                                              (c,d) = contaAvaliacoes lnode
                                              (e,f) = contaAvaliacoes rnode 