module Ficha3 where

infixr ><
(><) :: Int -> Int -> Int
x >< 0 = 0
x >< y = x + (x >< y - 1)


div1 :: Int -> Int -> Int
div1 x y = if x < y 
    then 0 
    else 1 + (div1 (x-y) y)


mod1 :: Int -> Int -> Int
mod1 x y = if x < y 
    then y 
    else mod1 (x-y) y

power :: Int -> Int -> Int
power b 0 = 1
power b e = b * (power b (e-1))

type Polinomio = [Monomio]
type Monomio = (Float,Int) 

--2 a)

conta :: Int -> Polinomio -> Int 
conta n [] = 0
conta n ((x,y):t) = if n == y
    then 1 + conta n t
    else conta n t

--2 b)

grau :: Polinomio -> Int 
grau p = aux (listexp p)

aux :: [Int] -> Int
aux [x] = x
aux (x:y:t) = if x > y then aux (x:t) else aux (y:t)

listexp :: Polinomio -> [Int]
listexp [] = []
listexp ((x,y):t) = (y:listexp t)

--2 c)

selgrau :: Int -> Polinomio -> Polinomio
selgrau n [] = []
selgrau n ((x,y):t) = if n == y 
    then ((x,y):selgrau n t)
    else selgrau n t

--2 d)

deriv :: Polinomio -> Polinomio
deriv [] = []
deriv ((x,y):t) = (x * (realToFrac y),y-1) : deriv t

--2 e)

calcula :: Float -> Polinomio -> Float
calcula n [] = 0
calcula n ((x,y):t) = (x * n ^ y) + calcula n t

--2 f)

simp :: Polinomio -> Polinomio
simp [] = []
simp ((x,y):t) = if x == 0 then simp t else (x,y):simp t

--2 g)

--mult :: Monomio -> Polinomio -> Polinomio

--2 h)

normaliza :: Polinomio -> Polinomio
normaliza [] = []
normaliza [(x,y)] = [(x,y)]
normaliza ((x,y):t) = if elem1 y (snd t)
    then ((y + snd t):normaliza t)
    else normaliza t

elem1 :: Eq a => a -> [a] -> Bool
elem1 n [] = False 
elem1 n (x:xs) = if n == x then True else elem1 n xs

--dado um polinómio constrói um polinómio equivalente em que não podem aparecer
--varios monómios com o mesmo grau.

















--3 a)
{-
union :: Eq a => MSet a -> MSet a -> MSet a
union xs [] = xs
union xs (y:ys) = adicionaMSet y (union xs ys)

adicionaMSet :: (a,Int) -> MSet a -> MSet a  
adicionaMSet (y,yn) [] = [(y,yn)]
adicionaMSet (y,yn) ((x,xn):xs) = if y == x
    then (x,yn + xn):xs
    else (x,xn) : adicionaMSet (y,yn) xs
-}