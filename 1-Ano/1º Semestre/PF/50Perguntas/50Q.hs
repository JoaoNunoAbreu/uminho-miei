module Questoes50 where

import Data.List
import Data.Char 
import Data.Either

-- 1

enumFromTo1 :: Int -> Int -> [Int]
enumFromTo1 x y | x <= y = x : enumFromTo1 (x + 1) y
                | otherwise = []

-- 2 

enumFromThenTo1 :: Int -> Int -> Int -> [Int]
enumFromThenTo1 x y z | x <= z = x : enumFromThenTo1 (x + (y - x)) (y + (y - x)) z 
                      | otherwise = []

-- 3

(+++) :: [a]->[a]->[a] 
[] +++ l2 = l2
(x:xs) +++ l2 = x : xs +++ l2 

-- 4

(!!!) :: [a] -> Int -> a
(!!!) (x:xs) 0 = x
(!!!) (x:xs) n = (!!!) xs (n - 1) 

(!!!!) :: [a] -> Int -> a
(x:xs) !!!! 0 = x
(x:xs) !!!! n = xs !!!! (n - 1) 

-- 5

reverse1 :: [a] -> [a]
reverse1 [] = []
reverse1 (x:xs) = reverse1 xs ++ [x] 

reverse2 :: [a] -> [a]
reverse2 [] = []
reverse2 p = last p : (reverse2 $ init p) 

-- 6

take1 :: Int -> [a] -> [a]
take1 _ [] = [] 
take1 0 l  = []
take1 c (x:xs) = x : take1 (c - 1) xs

-- 7

drop1 :: Int -> [a] -> [a]
drop1 _ [] = []
drop1 0 l  = l 
drop1 c (x:xs) = drop1 (c - 1) xs

-- 8

zip1 :: [a] -> [b] -> [(a,b)]
zip1 [] _ = []  
zip1 _ [] = []  
zip1 (x:xs) (y:ys) = (x,y) : zip1 xs ys

-- 9

elem1 :: Eq a => a -> [a] -> Bool
elem1 _ [] = False
elem1 c l  = not $ null $ filter (== c) l

-- 10

replicate1 :: Int -> a -> [a] 
replicate1 0 _ = []
replicate1 m n = n : replicate1 (m - 1) n

-- 11 

intersperse1 :: a -> [a] -> [a]
intersperse1 _ []  = []
intersperse1 _ [x] = [x]
intersperse1 y (x:xs) = x : y : intersperse1 y xs

-- 12

group1 :: Eq a => [a] -> [[a]]
group1 [x]   = [[x]]
group1 (h:t) = (h:(iguais h t)) : group1 (dif (h:t))

iguais :: Eq a => a -> [a] -> [a]
iguais _ [] = [] 
iguais x l | x == head l = x : iguais x (tail l)
           | otherwise = []

dif :: Eq a => [a] -> [a]
dif [] = []
dif (h:t) | h == head t = dif t 
          | otherwise = t

group2 :: Eq a => [a] -> [[a]]
group2 []  = []
group2 [x] = [[x]]
group2 l = takeWhile (== head l) l : group2 (dropWhile (== head l) l) 

-- 13

concat1 :: [[a]] -> [a]
concat1 [] = []
concat1 (x:xs) = x ++ concat1 xs 

-- 14

inits1 :: [a] -> [[a]]
inits1 [] = [[]]
inits1 l  = aux 0 l

aux :: Int -> [a] -> [[a]]
aux n l | n <= (length l) = take n l : aux (n+1) l 
        | otherwise = []

-- 15

tails1 :: [a]->[[a]]
tails1 l = aux1 (length l) l 

aux1 :: Int -> [a] -> [[a]]
aux1 n l | n >= 0  = take n l : aux (n+1) l 
         | otherwise = []

-- 16 

isPrefixOf1 :: Eq a => [a] -> [a] -> Bool 
isPrefixOf1 [] _ = True
isPrefixOf1 _ [] = False
isPrefixOf1 (x:xs) (y:ys) | x == y = isPrefixOf1 xs ys 
                          | otherwise = False

isPrefixOf2 :: Eq a => [a] -> [a] -> Bool
isPrefixOf2 [] _ = True
isPrefixOf2 _ [] = False 
isPrefixOf2 (x:xs) (y:ys) = (x == y) && isPrefixOf2 xs ys

-- 17

isSuffixOf1 :: Eq a => [a] -> [a] -> Bool
isSuffixOf1 l0 l1 = isPrefixOf2 (reverse l0) (reverse l1)

-- 18

isSubsequenceOf1 :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf1 [] _ = True
isSubsequenceOf1 _ [] = False 
isSubsequenceOf1 (x:xs) (y:ys) | x == y = isSubsequenceOf1 xs ys 
                               | otherwise = isSubsequenceOf1 (x:xs) ys

-- 19

elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 c l = aux 0 c l
                   where aux c n (x:xs) | (n == x)  = c : aux (c + 1) n xs
                                        | otherwise = aux (c + 1) n xs 
                         aux _ _ [] = []

-- 20

nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 (x:xs) | elem x xs = x : nub1 xs 
            | otherwise = nub1 xs

-- 21

delete1 :: Eq a => a -> [a] -> [a]
delete1 _ [] = []
delete1 c (x:xs) | c == x = xs 
                 | otherwise = x : delete1 c xs 

-- 22

(\\\) :: Eq a => [a] -> [a] -> [a]
(\\\) [] _ = []
(\\\) p [] = p 
(\\\) (x:xs) (y:ys) | x == y = xs \\\ ys
                    | otherwise =  x : (xs \\\ (y:ys))

-- 23

union1 :: Eq a => [a] -> [a] -> [a]
union1 p1 [] = p1
union1 [] p2 = p2
union1 p (y:ys) | elem y p = union1 p ys
                | otherwise = union (p ++ [y]) ys

-- 24

intersect1 :: Eq a => [a] -> [a] -> [a]
intersect1 _ [] = []
intersect1 [] p = [] 
intersect1 (x:xs) (y:ys) | x == y = x : intersect1 xs (y:ys)
                         | otherwise = intersect1 xs ys

-- 25

insert1 :: Ord a => a -> [a] -> [a]
insert1 c [] = [c]
insert1 c (h:t) | c > h = h : insert1 c t 
                | otherwise = [c] ++ dropWhile (< c) (h:t)

-- 26

unwords1 :: [String] -> String
unwords1 []  = ""
unwords1 [x] = x 
unwords1 (x:xs) = x ++ " " ++ unwords1 xs

-- 27

unlines1 :: [String] -> String
unlines1 []  = ""
unlines1 [x] = x ++ "\n"
unlines1 (x:xs) = x ++ "\n" ++ unlines1 xs

-- 28

pMaior :: Ord a => [a] -> Int
pMaior p = index 0 (maximum p) p
           where index _ _ [] = 0 
                 index n c (h:t) | c /= h = 1 + index (n + 1) c t 
                                 | otherwise = 0

-- 29

temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = False 
temRepetidos (x:xs) = elem x xs || temRepetidos xs

-- 30

algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (x:xs) | isDigit x = x : algarismos xs 
                  | otherwise = algarismos xs

-- 31

posImpares :: [a] -> [a]
posImpares []  = []
posImpares [x] = []
posImpares (x:y:xs) = y : posImpares xs

-- 32 

posPares :: [a] -> [a]
posPares []  = []
posPares [x] = [x]
posPares (x:y:xs) = x : posPares xs

-- 33 

isSorted :: Ord a => [a] -> Bool
isSorted []  = False
isSorted [x] = True 
isSorted (x:xs) | x <= head xs = isSorted xs 
                | otherwise = False

-- 34

iSort :: Ord a => [a] -> [a]
iSort [] = []
iSort (x:xs) = insert1 x (iSort xs)

-- 35

menor :: String -> String -> Bool
menor [] p = True  
menor (x:xs) (y:ys) | (x == y) = menor xs ys 
                    | otherwise = False

-- 36

elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet n p = elem n (map fst p)

elemMSet1 :: Eq a => a -> [(a,Int)] -> Bool
elemMSet1 _ [] = False 
elemMSet1 n ((x,y):xs) | (n == x) = True
                       | otherwise = elemMSet1 n xs

-- 37

lengthMSet :: [(a,Int)] -> Int
lengthMSet = sum . map snd

-- 38

converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((x,y):xs) = replicate y x ++ converteMSet xs

-- 39

insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet x [] = [(x,1)]
insereMSet c ((x,y):xs) | c == x = (x, y + 1) : xs
                        | otherwise = (x,y) : insereMSet c xs

-- 40

removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet _ [] = []
removeMSet c ((x,y):xs) | (c == x) && (y > 1)  = (x, y - 1) : xs
                        | (c == x) && (y == 1) = xs
                        | otherwise = (x,y) : removeMSet c xs

-- 41 

constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet x = (head x, length $ f x) : constroiMSet (p x)  
                 where f x = takeWhile (== head x) x
                       p x = dropWhile (== head x) x

-- 42

partitionEithers1 :: [Either a b] -> ([a],[b])
partitionEithers1 p = (left p, right p)
                      where left  (Left a : xs)   = a : left xs 
                            left  (Right b : xs)  = left xs
                            left _  = []
                            right (Left a : xs)   = right xs
                            right (Right a : xs)  = a : right xs
                            right _ = []

-- 43

catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Just a : xs)  = a : catMaybes xs
catMaybes (Nothing : xs) = catMaybes xs


data Movimento = Norte | Sul | Este | Oeste deriving Show

-- 44


posicao :: (Int,Int) -> [Movimento] -> (Int,Int)
posicao (x0,y0) p = (x0 - este p + oeste p, y0 - norte p + sul p) 
                       where norte (Norte:xs) = 1 + norte xs
                             norte (_:xs)     = norte xs 
                             norte _ = 0
                             sul (Sul:xs)     = 1 + sul xs
                             sul (_:xs)       = sul xs
                             sul _ = 0
                             este (Este:xs)   = 1 + este xs
                             este (_:xs)      = este xs
                             este _ = 0
                             oeste (Oeste:xs) = 1 + oeste xs
                             oeste (_:xs)     = oeste xs
                             oeste _ = 0


-- 45

caminho :: (Int,Int) -> (Int,Int) -> [Movimento]
caminho (xi,yi) (xf,yf) | (xi == xf) && (yf < yi)  = Norte : caminho (xi, yi - 1) (xf, yf)
                        | (xi == xf) && (yi < yf)  = Sul   : caminho (xi, yi + 1) (xf, yf)
                        | (xi < xf)  && (yi <= yf) = Oeste : caminho (xi + 1, yi) (xf, yf)
                        | (xf > xi)  && (yi <= yf) = Este  : caminho (xi - 1, yi) (xf, yf)
                        | (xi < xf)  && (yi > yf)  = Oeste : caminho (xi + 1, yi) (xf, yf)
                        | (xi > xf)  && (yi > yf)  = Este  : caminho (xi - 1, yi) (xf, yf)
                        | otherwise = []

-- 46

vertical :: [Movimento] -> Bool
vertical [] = True 
vertical (Norte:xs) = vertical xs
vertical (Sul:xs)   = vertical xs
vertical (_:xs)     = False


data Posicao = Pos Int Int deriving Show

-- 47

maisCentral :: [Posicao] -> Posicao
maisCentral [Pos x y] = Pos x y 
maisCentral ((Pos x0 y0):(Pos x1 y1):xs) | (x0 < x1) || (x0 == x1 && y0 <= y1)  = maisCentral ((Pos x0 y0):xs)      
                                         | otherwise = maisCentral ((Pos x1 y1):xs)

-- 48

vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos (Pos x0 y0) [] = [] 
vizinhos (Pos x0 y0) ((Pos x y):xs) | ((x == x0 + 1) && (y == y0))     || 
                                      ((x == x0 - 1) && (y == y0))     || 
                                      ((x == x0)     && (y == y0 + 1)) ||
                                      ((x == x0)     && (y == y0 - 1))  = (Pos x y) : vizinhos (Pos x0 y0) xs 
                                    | otherwise = vizinhos (Pos x0 y0) xs

-- 49

mesmaOrdenada :: [Posicao] -> Bool
mesmaOrdenada [Pos x y] = True 
mesmaOrdenada ((Pos x0 y0):(Pos x y):xs) | y0 == y = mesmaOrdenada ((Pos x y):xs)
                                         | otherwise = False

data Semaforo = Verde | Amarelo | Vermelho deriving Show

-- 50

interseccaoOK :: [Semaforo] -> Bool
interseccaoOK p | my_filter p > 1 = False
                | otherwise = True
                where my_filter (Verde:xs) = 1 + my_filter xs
                      my_filter (_:xs)     = my_filter xs
                      my_filter _ = 0





