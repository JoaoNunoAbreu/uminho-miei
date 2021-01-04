module CinquentaPerguntas where

import Data.Char

-- 1
enumFromTo1 :: Int -> Int -> [Int]
enumFromTo1 x y | x >  y = [] 
                | x == y = x : []
                | x <  y = x : (enumFromTo1 (x+1) y)
 
-- 2
myEnumFromThenTo :: Int -> Int -> Int -> [Int]
myEnumFromThenTo x y z = [x,y..z] 

-- 3

(+++) :: [a] -> [a] -> [a]
(+++) [] l = l
(+++) (x:xs) l = x : ((+++) xs l)

-- 4
(!!!) :: [a] -> Int -> a
(!!!) (x:xs) 0 = x
(!!!) (x:xs) n = (!!!) xs (n-1)

-- 5
myReverse :: [a] -> [a]
myReverse [] = []
myReverse l = last l: myReverse (init l) 

-- 6

take1 :: Int -> [a] -> [a]
take1 0 n = []
take1 l [] = []
take1 l (x:xs) | l > 0 = x : take1 (l-1) xs
               | otherwise = [] 

-- 7.

drop1 :: Int -> [a] -> [a]
drop1 0 l = l
drop1 n [] = []
drop1 n (x:xs) | n > 0 = drop1 (n-1) xs
               | otherwise = x:xs

--8

zip1 :: [a] -> [b] -> [(a,b)]
zip1 [] [] = [] 
zip1 (x:xs) (y:ys) = (x,y) : zip1 xs ys

--9

elem1 :: Eq a => a -> [a] -> Bool
elem1 n [] = False 
elem1 n (x:xs) = if n == x then True else elem1 n xs

--10

replicate1 :: Int -> a -> [a]
replicate1 0  n2 = []
replicate1 n1 n2 = n2 : replicate1 (n1-1) n2

--11

intersperse1 :: a -> [a] -> [a]
intersperse1 n [] = []
intersperse1 n (x:xs) = x : n : intersperse1 n xs

--12

--myGroup :: Eq a => [a] -> [[a]]

--13

concat1 :: [[a]] -> [a]
concat1 [] = []
concat1 (x:xs) = (+++) x (concat1 xs)

--14

inits1 :: [a] -> [[a]]
inits1 [] = [[]]
inits1 (h:t) = ([] : adiciona h (inits1 t))

adiciona :: a -> [[a]] -> [[a]]
adiciona x [] = []
adiciona x (h:t) = (x:h) : adiciona x t 

--15

myTails :: [a] -> [[a]]
myTails [] = [[]]
myTails (h:t) = (h:t) : myTails t

--16

isPrefixOf :: Eq a => [a] -> [a] -> Bool
isPrefixOf l [] = False
isPrefixOf [] l = True
isPrefixOf (x:xs) (y:ys) = if x == y
    then isPrefixOf xs ys
    else False  

--17

isSuffixOf :: Eq a => [a] -> [a] -> Bool
isSuffixOf l [] = False
isSuffixOf [] l = True
isSuffixOf l1 l2 = if l1 == l2
    then True
    else isSuffixOf l1 (tail l2)


--18

isSubsequenceOf :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf [] l = True
isSubsequenceOf l [] = False
isSubsequenceOf (x:xs) (y:ys) = if x == y
    then isSubsequenceOf xs ys 
    else isSubsequenceOf (x:xs) ys



--19

elemIndices :: Eq a => a -> [a] -> [Int]
elemIndices x [] = []
elemIndices x (h:t) = elemIndex 0 x (h:t) 

elemIndex :: Eq a => Int -> a -> [a] -> [Int]
elemIndex i x [] = []
elemIndex i x (h:t) = if x == h
    then i:elemIndex (i+1) x t
    else elemIndex (i+1) x t


--20

myNub :: Eq a => [a] -> [a]
myNub [] = []
myNub (h:t) = h : myNub ((myRemove h t))

myRemove :: Eq a => a -> [a] -> [a]
myRemove x [] = []
myRemove x (h:t) = if h==x
    then (myRemove x t)
    else h:(myRemove x t)

--21

myDelete :: Eq a => a -> [a] -> [a]    
myDelete n [] = []
myDelete n (x:t) | n == x = t
                 | otherwise = x : myDelete n t

--22

(\\\) :: Eq a => [a] -> [a] -> [a]
(\\\) [] l = []
(\\\) l [] = l
(\\\) (x:xs) (y:ys) = if x == y
    then (\\\) xs ys
    else x:(\\\) xs (y:ys) 

--23

myUnion :: Eq a => [a] -> [a] -> [a]
myUnion [] l = noRepeats l
myUnion l [] = l
myUnion l (h:t) = if elem1 h l
    then myUnion l t
    else myUnion ((+++) l [h]) t

noRepeats :: Eq a => [a] -> [a]
noRepeats [] = []
noRepeats (h:t) = if elem1 h t
    then h:noRepeats (myRemove h t)
    else h:noRepeats t

--24

myIntersect :: Eq a => [a] -> [a] -> [a]
myIntersect [] l = []
myIntersect (x:xs) l = if elem1 x l
    then x:myIntersect xs l
    else myIntersect xs l 

--25

myInsert :: Ord a => a -> [a] -> [a]
myInsert n [] = [n]
myInsert n (x:xs) = if n < x
    then (n:x:xs)
    else x:myInsert n xs    

--26

myUnwords :: [String] -> String
myUnwords [] = " "
myUnwords [x] = x
myUnwords (h:t) = (+++) ((+++) h " ") (myUnwords t)

--27

myUnlines :: [String] -> String
myUnlines [] = ""
myUnlines (h:t) = (+++) ((+++) h "\n") (myUnlines t)

--28

pMaior :: Ord a => [a] -> Int
pMaior [x] = 0
pMaior (h:t) | h < myMaximum t = 1 + pMaior t
             | h > myMaximum t = 0
             | otherwise = 1 + pMaior t

myMaximum :: Ord a => [a] -> a
myMaximum [x] = x
myMaximum (h:h1:t) | h > h1 = myMaximum (h:t)
                   | h <= h1 = myMaximum (h1:t)

--29

temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = False
temRepetidos (h:t) = if elem1 h t == True || temRepetidos t == True then True else False

--30

algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (h:t) = if isDigit h
    then h : algarismos t
    else algarismos t

--31

posImpares :: [a] -> [a]
posImpares [] = []
posImpares l = posImpAux False l

posImpAux :: Bool -> [a] -> [a]
posImpAux x [] = []
posImpAux True (h:t) = h:posImpAux False t
posImpAux False (h:t) = posImpAux True t

--32

posPares :: [a] -> [a]
posPares [] = []
posPares l = posParAux True l

posParAux :: Bool -> [a] -> [a]
posParAux x [] = []
posParAux True (h:t) = h : posParAux False t
posParAux False (h:t) = posParAux True t

--33

isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (h:h1:t) = h <= h1 && isSorted (h1:t)

--34

iSort :: Ord a => [a] -> [a]
iSort [] = []
iSort (h:t) = myInsert h (iSort t)


--35

menor :: String -> String -> Bool
menor _ [] = False
menor [] _ = True
menor (h1:t1) (h2:t2) = (length t1 + 1) < (length t2 + 1)

--36

elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet n [] = False
elemMSet n ((x,y):t) = if n == x then True else elemMSet n t

--37

lengthMSet :: [(a,Int)] -> Int
lengthMSet [] = 0
lengthMSet ((x,y):t) = y + lengthMSet t

--38

converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet (p:t) = (+++) (converteMSetAux p)  (converteMSet t)


converteMSetAux :: (a,Int) -> [a]
converteMSetAux (x,0) = []
converteMSetAux (x,y) = x : converteMSetAux (x,y-1) 

--39

insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet n [] = [(n,1)]
insereMSet n ((x,y):t) = if n == x 
    then ((x,y+1):t)
    else ((x,y):insereMSet n t)

--40

removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet n [] = []
removeMSet n ((x,y):t) = if n == x
    then 
        if y == 1 
            then t
            else ((x,y-1):t)
    else (x,y):removeMSet n t    

--41

constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet x = cMSetAux [] x

cMSetAux :: Eq a => [(a,Int)] -> [a] -> [(a,Int)]
cMSetAux x [] = x
cMSetAux x (h:t) = cMSetAux (insereMSet h x) t

--42
 
--43

--44

data Movimento = Norte | Sul | Este | Oeste
    deriving Show

posicao :: (Int,Int) -> [Movimento] -> (Int,Int)
posicao (x,y) [] = (x,y)
posicao (x,y) (Norte:xs) = posicao (x,y+1) xs
posicao (x,y) (Sul:xs) = posicao (x,y-1) xs
posicao (x,y) (Oeste:xs) = posicao (x-1,y) xs
posicao (x,y) (Este:xs) = posicao (x+1,y) xs

--45

caminho :: (Int,Int) -> (Int,Int) -> [Movimento]
caminho (a,b) (c,d) | (a == c) && (b == d) = []
                    | (a > c) = (Oeste: caminho (a-1,b) (c,d))
                    | (a < c) = (Este: caminho (a+1,b) (c,d))
                    | (b > d) = (Sul: caminho (a,b-1) (c,d))
                    | (b < d) = (Norte: caminho (a,b+1) (c,d))

--46

vertical :: [Movimento] -> Bool
vertical [] = True
vertical (Norte:xs) = vertical xs
vertical (Sul:xs) = vertical xs
vertical (_:xs) = False

data Posicao = Pos Int Int
    deriving Show

--47

maisCentral :: [Posicao] -> Posicao
maisCentral [p] = p
maisCentral (Pos x y : Pos a b : t) = maisCentral (maisCentralAux (Pos x y) (Pos a b):t)

maisCentralAux :: Posicao -> Posicao -> Posicao
maisCentralAux (Pos x y) (Pos a b) | (sqrt (realToFrac (x ^ 2)) + (realToFrac (y ^ 2))) < (sqrt (realToFrac (a ^ 2)) + (realToFrac (b ^ 2))) = Pos x y
                                   | otherwise = Pos a b



--48

vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos _ [] = []
vizinhos (Pos x y) ((Pos a b):t) = undefined


--49

mesmaOrdenada :: [Posicao] -> Bool
mesmaOrdenada [] = True
mesmaOrdenada [x] = True
mesmaOrdenada (Pos x y : Pos a b : t) = if x == a 
    then mesmaOrdenada ((Pos a b): t)
    else False

data Semaforo = Verde | Amarelo | Vermelho
    deriving Show

--50

interseccaoOK :: [Semaforo] -> Bool
interseccaoOK l = aux12 l False

aux12 :: [Semaforo] -> Bool -> Bool
aux12 (Vermelho:t) v = aux12 t v
aux12 (_ :t) False = aux12 t True
aux12 (_ :t) True = False
aux12 [] _ = True















--Funções auxiliares uteis


{-

myMaximum :: Ord a => [a] -> a
myMaximum [x] = x
myMaximum (h:h1:t) | h > h1 = myMaximum (h:t)
                   | h <= h1 = myMaximum (h1:t)

(+++) :: [a] -> [a] -> [a]
(+++) [] l = l
(+++) (x:xs) l = x : ((+++) xs l)

elem1 :: Eq a => a -> [a] -> Bool
elem1 n [] = False 
elem1 n (x:xs) = if n == x then True else elem1 n xs

myLenght :: [a] -> Int
myLenght [] = 0
myLenght (h:t) = 1 + myLenght t

myReplicate1 :: Int -> a -> [a]
myReplicate1 0  n2 = []
myReplicate1 n1 n2 = n2 : myReplicate1 (n1-1) n2


-}