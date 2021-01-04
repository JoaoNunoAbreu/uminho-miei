module Ficha2 where

--1. a) Soma os quadrados dos números da lista
funA :: [Float] -> Float
funA [] = 0
funA (y:ys) = y^2 + (funA ys)
{-
funA [2,3,5,1]
= funA (2:[3,5,1])
= 2² + funA [3,5,1]
= 2² + 3² + funA [5,1]
= 2² + 3² + 5² + funA [1]
= 2² + 3² + 5² + 1² + funA []
= 2² + 3² + 5² + 1² + 0 = 39
-}

--1. b) Diz quais são os números pares da lista
funB :: [Int] -> [Int]
funB [] = []
funB (x:xs) = if (mod x 2)==0 then 
    x : (funB xs)
    else (funB xs)

{-
funB [8,5,12]
= 8 : (funB [5,12])
= 8 : (funB [12])
= 8 : (12 : funB [])
= 8 : 12 : []
= [8,12]
-}    

--1. c) Elimina todos os elementos da lista
funC (x:y:ys) = funC ys
funC [x] = []
funC [] = []

{-
funC [1,2,3,4,5]
= funC [3,4,5]
= funC [5]
= []
-}

--1. d)Troca a ordem de uma string
funD l = g [] l
g l [] = l
g l (h:t) = g (h:l) t

--2. a)
dobros :: [Float] -> [Float]
dobros [] = []
dobros (x:xs) = x*2 : (dobros xs) 

--2. b)
numOcorre :: Char -> String -> Int
numOcorre x [] = 0
numOcorre y (x:xs) = if x == y 
    then 1+numOcorre y xs
    else numOcorre y xs

--2. c)
positivos :: [Int] -> Bool
positivos [x] = x > 0 
positivos (x:xs) = if x > 0 then positivos xs else False

--2. d)
soPos :: [Int] -> [Int]
soPos [x] = if x > 0 then [x] else []
soPos (x:xs) = if x > 0 then x : soPos (xs) else soPos xs 

--2. e)
somaNeg :: [Int] -> Int
somaNeg [x] = x
somaNeg (x:xs) = if x < 0 then  x + somaNeg xs else somaNeg xs

--2. f)
tresUlt :: [a] -> [a]
tresUlt [] = []
tresUlt [x] = [x]
tresUlt [x,y] = [x,y]
tresUlt [x,y,z] = [x,y,z]
tresUlt (x:xs) = tresUlt xs

--2. g)
primeiros :: [(a,b)] -> [a]
primeiros [] = []
primeiros ((x,y):ys) = x : primeiros ys

--3. a)
soDigitos :: [Char] -> [Char]


