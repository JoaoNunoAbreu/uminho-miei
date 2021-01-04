import Data.List
import Data.Char 
import Data.Either

-------------------------------------------------

group2 :: Eq a =>  [a] -> [[a]]
group2 [] = [[]]
group2 (x:xs) = aux [x] xs
    where
        aux l [] = []
        aux l (x:xs) = if elem x l
            then aux (x:l) xs
            else l: aux [x] xs

-------------------------------------------------

inits1 :: [a] -> [[a]] 
inits1 [] = [[]]
inits1 (h:t) = []:inits1Aux h (inits1 t)

inits1Aux :: a -> [[a]] -> [[a]]
inits1Aux a [] = []
inits1Aux a (h:t) = (a:h):inits1Aux a t

-------------------------------------------------

elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 n [] = []
elemIndices1 n (h:t) = elemIndicesAux 0 n (h:t)


elemIndicesAux :: Eq a => Int -> a -> [a] -> [Int]
elemIndicesAux i n [] = []
elemIndicesAux i n (h:t) = if n == h
    then i:elemIndicesAux (i+1) n t
    else elemIndicesAux (i+1) n t

-------------------------------------------------

nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 [x] = [x]
nub1 (h:t) = if elem h t
    then h:nub1 (nub1Aux h t)
    else h:nub1 t

nub1Aux :: Eq a => a -> [a] -> [a]
nub1Aux n [] = []
nub1Aux n (h:t) = if n == h
    then nub1Aux n t
    else h:nub1Aux n t

-------------------------------------------------

pMaior :: Ord a => [a] -> Int
pMaior [x] = 0
pMaior (h:t) = if h < pMaiorAux t
    then 1 + pMaior t
    else 0 

pMaiorAux :: Ord a => [a] -> a
pMaiorAux [x] = x
pMaiorAux (h1:h2:t) = if h1 < h2
    then pMaiorAux (h2:t)
    else pMaiorAux (h1:t)

-------------------------------------------------

converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((x,y):t) = converteMSetAux (x,y) ++ converteMSet t

converteMSetAux :: (a,Int) -> [a]
converteMSetAux (x,0) = []
converteMSetAux (x,y) = x:converteMSetAux (x,y-1)

-------------------------------------------------

constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet (h:t) = cMSetAux [] (h:t)

cMSetAux :: Eq a => [(a,Int)] -> [a] -> [(a,Int)]
cMSetAux x [] = x
cMSetAux x (h:t) = cMSetAux (insereMSet h x) t

insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet n [] = [(n,1)]
insereMSet n ((x,y):t) = if n == x
    then (x,y+1):t 
    else (x,y):insereMSet n t