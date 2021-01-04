module Exame1516 where

import Data.Char

-- 1

-- (a)

intersperse :: a -> [a] -> [a]
intersperse x [] = [x]
intersperse x [h] = [h]
intersperse x (h:t) = h : x : intersperse x t

-- (b)

inits' :: [a]->[[a]]
inits' [] = [[]]
inits' (h:t) = [] : initsAux h (inits' t)

initsAux :: a -> [[a]] -> [[a]]
initsAux x [] = []
initsAux x (h:t) = ([x] ++ h) : initsAux x t 

-- 2

turma1 :: Turma
turma1 = (Nodo (5,"João",Aprov 20) (Nodo (4,"Nuno",Aprov 15) (Nodo (3,"Abreu",Rep) Vazia Vazia) Vazia) (Nodo (6,"JBB",Faltou) Vazia (Nodo (7,"Alcino",Aprov 21) Vazia Vazia)))

turma2 :: Turma
turma2 = (Nodo (5,"João",Faltou) (Nodo (4,"Nuno",Rep) (Nodo (3,"Abreu",Rep) Vazia Vazia) Vazia) (Nodo (6,"JBB",Faltou) Vazia (Nodo (7,"Alcino",Rep) Vazia Vazia)))

data Classificacao = Aprov Int | Rep | Faltou
data Turma = Vazia | Nodo Aluno Turma Turma
type Aluno = (Numero,Nome,Classificacao)
type Numero = Int
type Nome = String

-- (a)

inscNum :: Numero -> Turma -> Bool
inscNum n Vazia = False
inscNum n (Nodo (num,nome,clas) l r) | n == num = True
                                     | n <  num = inscNum n l
                                     | n >  num = inscNum n r

-- (b)

aprovAv :: Turma -> Float
aprovAv t = (aprovados t) / realToFrac (lengthTurma t) 

aprovados :: Turma -> Float
aprovados Vazia = 0
aprovados (Nodo x l r) = if testaSeAprov x
    then (1 + aprovados l + aprovados r) 
    else (aprovados l + aprovados r) 

lengthTurma :: Turma -> Int
lengthTurma Vazia = 0
lengthTurma (Nodo x l r) = 1 + lengthTurma l + lengthTurma r

testaSeAprov :: Aluno -> Bool
testaSeAprov (_,_,Aprov x) = True
testaSeAprov _ = False 

-- (c)

melhorNota :: Turma -> Maybe Int
melhorNota t = if listaNota t == []
    then Nothing 
    else Just (maximum (listaNota t)) 

listaNota :: Turma -> [Int]
listaNota Vazia = []
listaNota (Nodo (_,_,(Aprov x)) l r) = [x] ++ listaNota l ++ listaNota r
listaNota (Nodo (_,_,_) l r) = listaNota l ++ listaNota r 


-- 3

type Polinomio = [Coeficiente]
type Coeficiente = Float

p1 :: Polinomio
p1 = [1,3,2]

p2 :: Polinomio
p2 = [3,2,1]


--a)

add :: Polinomio -> Polinomio -> Polinomio
add [] y = y
add x [] = x
add (x:xs) (y:ys) = (x+y) : add xs ys

--b)

detfun :: Polinomio -> IO()
detfun p = do putStrLn $ showPoli p
              putStr "Indique um valor para x : "
              xChar <- getLine
              putStrLn $ show (calcularPoli xChar p)
              return ()
             where

showPoli :: Polinomio -> String
showPoli [] = []
showPoli (p:ps)
  = (show p) ++ showPoliAc ps 1
  where
  showPoliAc [] _ = []
  showPoliAc (p:ps) exp = " + " ++ show p ++ "*(x^" ++ show exp ++")"++ showPoliAc ps (exp + 1)

calcularPoli :: String -> Polinomio -> Float
calcularPoli xChar poli
  = let xFloat = read xChar :: Float in
    calcularPoliH xFloat poli 0
     where
     calcularPoliH _ [] _ = 0
     calcularPoliH x (p:ps) e = p*(x^e) + calcularPoliH x ps (e+1)

--c)

type Expoente = Int

multPolinomios :: Polinomio -> Polinomio -> Polinomio
multPolinomios xxs yys
  = multPolinomiosAc 0 xxs yys
  where
  multPolinomiosAc _ [] _ = []
  multPolinomiosAc n (x:xs) yys = add (multPolinomio (x,n) yys) (multPolinomiosAc (n+1) xs yys)


multPolinomio :: (Coeficiente,Expoente) -> Polinomio -> Polinomio
multPolinomio (c,e) p
  = multPolinomioAc (c,e) p
  where
  multPolinomioAc _ [] = []
  multPolinomioAc (c,0) p = map (c*) p
  multPolinomioAc (c,e) p = multPolinomioAc (c,e-1) (0:p)
