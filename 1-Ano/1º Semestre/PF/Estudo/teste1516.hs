module Teste1516 where

import System.Random

mynub :: Eq a => [a] -> [a] 
mynub [] = []
mynub (h:t) = if elem h t
    then h : mynub (removee h t)
    else h : mynub t

removee :: Eq a => a -> [a] -> [a]
removee _ [] = []
removee i (x:xs) = if i == x
    then removee i xs
    else x:removee i xs

zipWith1 :: (a->b->c) -> [a] -> [b] -> [c]
zipWith1 f [] _ = []
zipWith1 f _ [] = []
zipWith1 f (h:t) (x:xs) = f h x : zipWith1 f t xs

type MSet a = [(a,Int)]

-- (a)

converte :: Eq a => [a] -> MSet a
converte [] = []
converte (h:t) = (h , 1 + length (filter (h == ) t)) : converte ((filter (h /=) t))

-- (b)

intersect :: Eq a => MSet a -> MSet a -> MSet a
intersect [] _ = []
intersect _ [] = []
intersect ((a,i):t) l = if elemMSet a l
    then (a, min i (int (sacaMSet a l))) : intersect t l
    else intersect t l 

elemMSet :: Eq a => a -> MSet a -> Bool
elemMSet x [] = False
elemMSet x ((a,i):t) = if x == a
    then True
    else elemMSet x t

sacaMSet :: Eq a => a -> MSet a -> (a,Int)
sacaMSet x ((a,i):t) = if x == a
    then (a,i)
    else sacaMSet x t

int :: (a,Int) -> Int
int (x,y) = y

-- 3

data Prop = Var String 
          | Not Prop 
          | And Prop Prop 
          | Or Prop Prop

p1 :: Prop
p1 = Not (Or (And (Not (Var "A")) (Var "B")) (Var "C"))

-- (a)

instance Show Prop where
    show (Var x) = x
    show (Not x) = "-" ++ show x
    show (And x y) = "(" ++ show x ++ "/\\" ++ show y ++ ")"
    show (Or x y) = "(" ++ show x ++ "\\/" ++ show y ++ ")"

-- (b)

eval :: [(String,Bool)] -> Prop -> Bool
eval l (Var x) = sacaBool l (Var x)
eval l (Not x) = not (eval l x)
eval l (And x y) = eval l x && eval l y
eval l (Or x y) = eval l x || eval l y


sacaBool :: [(String,Bool)] -> Prop -> Bool
sacaBool [] _ = False
sacaBool ((s,b):t) (Var x) = if x == s
    then b
    else sacaBool t (Var x)

-- (c)

nnf :: Prop -> Prop
nnf (Not (Var a)) = Not (Var a)
nnf (Not (Not a)) = nnf a
nnf (Not (And a b)) = nnf (Or (Not a) (Not b))
nnf (Not (Or a b)) = nnf (And (Not a) (Not b))
nnf (Var a) = Var a
nnf (And a b) = And (nnf a) (nnf b)
nnf (Or a b) = Or (nnf a) (nnf b)

-- (d)

avalia :: Prop -> IO Bool 
avalia p = do
    putStrLn "Introduza uma lista composta por String's e o valor (Bool) correspondente"
    sb <- getLine;
    return (eval (read sb) p)

    -- [("A",True),("B",False),("C",True)]