module Ficha1 where

-- 1

{-

-- a

1º caso
(f . g) x = f (g x) = f (x + 1) = 2 * (x + 1) = 2 * x + 2 * 1 = 2x + 2
2º caso
(f . g) x = f (g x) = f (2 * x) = succ (2x) = 2x + 1
3º caso
(f . g) x = f (g x) = succ (length) = length + 1
4º caso
(f . g) x y = f (g(x, y)) = f (x + y) = succ . (*2) (x + y) = (*2) (x + y + 1) = 2x + 2y + 2

-}

-- 2

mylength :: [a] -> Int
mylength [] = 0
mylength (h:t) = 1 + mylength t

myreverse :: [a] -> [a]
myreverse [] = []
myreverse (h:t) = myreverse t ++ [h]

-- 3

catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : t) = catMaybes t
catMaybes (Just a : t) = a : catMaybes t

-- 4 

myuncurry :: (a -> b -> c) -> (a,b) -> c
myuncurry f (a,b) = f a b

mycurry :: ((a,b) -> c) -> a -> b -> c
mycurry f a b = f (a,b)

myflip :: (a -> b -> c) -> b -> a -> c 
myflip f b a = f a b

-- 5

data LTree a = Leaf a | Fork (LTree a, LTree a)

{-
(Fork(Fork(Leaf 2, Leaf 3), Leaf 1))
            -
           / \ 
          -   1
         / \
        2   3 
-}

flatten :: LTree a -> [a]
flatten (Leaf a) = [a]
flatten (Fork(x,y)) = flatten(x) ++ flatten(y)

mirror :: LTree a -> LTree a
mirror (Leaf a) = Leaf a
mirror (Fork(x,y)) = Fork (mirror y,mirror x)

myfmap :: (b -> a) -> LTree b -> LTree a
myfmap f (Leaf a) = Leaf (f a)
myfmap f (Fork(x,y)) = Fork(myfmap f x, myfmap f y)

-- 6

myfoldr :: (a -> b -> b) -> b -> [a] -> b 
myfoldr f z [] = z
myfoldr f z (h:t) = f h (myfoldr f z t)

lengthFoldr :: [a] -> Int
lengthFoldr = foldr (\x n -> n + 1) 0

reverseFoldr :: [a] -> [a]
reverseFoldr = foldr (\x n -> n ++ [x]) []

catMaybesFoldr :: [Maybe a] -> [a]
catMaybesFoldr = foldr aux []
    where
        aux :: Maybe a -> [a] -> [a]
        aux Nothing l = l
        aux (Just a) l = a:l

-- 7

myconcat :: [[a]] -> [a]
myconcat [] = []
myconcat (h:t) = h ++ myconcat t

-- 8

f :: [Int] -> [Int]
f s = [a + 1 | a <- s, a > 0] -- filter e map juntos


f1 :: [Int] -> [Int]
f1 = foldr (\x xs -> if x > 0 then x+1:xs else xs) []

f1' :: [Int] -> [Int]
f1' s = let s1 = filter(>0)
       in map (+1) (s1 s)

f2 :: [Int] -> [Int]
f2 = foldr aux []
    where
        aux :: Int -> [Int] -> [Int]
        aux x l | x > 0 = (x + 1):l
                | otherwise = l
-- ou

f2' :: [Int] -> [Int]
f2' = foldr (\x l -> if x > 0 then (x+1):l else l) []

-- 9

m :: (a -> b) -> [a] -> [b]
m f [] = []
m f (h:t) = (f h): m f t

m' :: (a -> b) -> [a] -> [b]
m' f = foldr aux []
    where
        aux :: a -> [b] -> [b]
        aux x y = ((f x) : y)
        

m2 :: (a -> b) -> [a] -> [b]
m2 f l = map f l

-- c) m :: [a] -> [[a]]
-- d)