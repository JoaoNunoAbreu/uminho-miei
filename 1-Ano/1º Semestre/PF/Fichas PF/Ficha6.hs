module Ficha6 where

import Data.List

-- 1

-- (a)

myany :: (a -> Bool) -> [a] -> Bool
myany f [] = False
myany f (h:t) = if f h
    then True
    else myany f t

-- (b)

zipWith1 :: (a->b->c) -> [a] -> [b] -> [c]
zipWith1 f [] _ = []
zipWith1 f _ [] = []
zipWith1 f (h1:t1) (h2:t2) = f h1 h2 : zipWith1 f t1 t2

-- (c)

takeWhile1 :: (a->Bool) -> [a] -> [a]
takeWhile1 f [] = []
takeWhile1 f (h:t) = if f h
    then h : takeWhile1 f t
    else []

-- (d)

dropWhile1 :: (a->Bool) -> [a] -> [a]
dropWhile1 f [] = []
dropWhile1 f (h:t) = if f h
    then dropWhile1 f t
    else h : t

-- (e)

myspan :: (a -> Bool) -> [a] -> ([a],[a])
myspan f list = aux1 f list []
                    where
                        aux1 f (h:t) list1 = if f h
                            then aux1 f t (list1 ++ [h])
                            else (list1,(h:t))

-- (f)

deleteBy1 :: (a -> a -> Bool) -> a -> [a] -> [a]
deleteBy1 f x [] = []
deleteBy1 f x (h:t) = if f x h 
    then t
    else h : deleteBy1 f x t

-- (g)

sortOn1 :: Ord b => (a -> b) -> [a] -> [a]
sortOn1 f [] = []
sortOn1 f (h:t) = insertOn f h (sortOn1 f t)

insertOn :: Ord b => (a -> b) -> a -> [a] -> [a]
insertOn f x [] = [x]
insertOn f x (h:t) = if f x < f h 
    then  x : h : t
    else h : insertOn f x t


-- 2

-- [(2,3), (3,4), (5,3), (4,5)]

-- (a)

type Polinomio = [Monomio]
type Monomio = (Float,Int)

selgrau :: Int -> Polinomio -> Polinomio
selgrau x [] = []
selgrau x p = filter (check x) p

check :: Int -> Monomio -> Bool
check x (m,n) = x == n

-- (b)

conta :: Int -> Polinomio -> Int 
conta x [] = 0
conta x p = length (selgrau x p)

-- (c)

grau :: Polinomio -> Int
grau l  = maximum (map snd l)

-- (d)

deriv :: Polinomio -> Polinomio 
deriv p = map f p
    where
        f :: Monomio -> Monomio
        f (m,n) = (m * (realToFrac n),n-1)

-- (e)

calcula :: Float -> Polinomio -> Float
calcula x p = sum (map f p)
    where
        f :: Monomio -> Float
        f (m,n) = m * (x^n)

-- (f)

simp :: Polinomio -> Polinomio 
simp xs = filter f xs
    where
        f :: Monomio -> Bool
        f (m,n) = m /= 0

-- (g)

mult :: Monomio -> Polinomio -> Polinomio
mult m p = map (f m) p
    where
        f :: Monomio -> Monomio -> Monomio
        f (c1,g1) (c2,g2) = (c1 * c2 , g1 + g2)

-- (h)

