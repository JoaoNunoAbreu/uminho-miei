
import Data.List
import Data.Char 
import Data.Either


-- 1
enumFromTo1 :: Int -> Int -> [Int]
enumFromTo1 x y | x >  y = [] 
                | x == y = x : []
                | x <  y = x : (enumFromTo1 (x+1) y)
 
-- 2
myEnumFromThenTo :: Int -> Int -> Int -> [Int]
myEnumFromThenTo a b c = if a <= c 
    then a : myEnumFromThenTo a (a+(b-a)) c
    else []

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

--6

myTake :: Int -> [a] -> [a]
myTake x [] = []
myTake x (h:t) = if x > 0
    then h:myTake (x-1) t
    else []

--7

myDrop :: Int -> [a] -> [a]
myDrop 0 l = l
myDrop n [] = []
myDrop n (h:t) = if n > 0
    then myDrop (n-1) t
    else []


--8

myZip :: [a] -> [b] -> [(a,b)]
myZip [] [] = []
myZip l [] = []
myZip [] l = []
myZip (h:t) (x:xs) = (h,x) : myZip t xs

--9

myElem :: Eq a => a -> [a] -> Bool
myElem n [] = False
myElem n (h:t) = if n == h
    then True
    else elem n t

--10

myReplicate :: Int -> a -> [a]
myReplicate 0 n2 = []
myReplicate n1 n2 = n2 : myReplicate (n1 -1) n2


--11

myIntersperce :: a -> [a] -> [a]
myIntersperce n [x] = x:[]
myIntersperce n (h:t) = h : n : myIntersperce n t

--12

group1 :: Eq a => [a] -> [[a]]
group1 [x] = [[x]]
group1 (h:t) = (h:(iguais h t)) : group1 (dif (h:t))

iguais :: Eq a => a -> [a] -> [a]
iguais x [] = []
iguais x (h:t) = if x == h
    then x: iguais x t
    else []

dif :: Eq a => [a] -> [a]
dif [] = []
dif (h:t) = if h == head t
    then dif t
    else t

group2 :: Eq a => [a] -> [[a]]
group2 [] = []
group2 (x:xs) = group2Aux [x] xs
        where
            group2Aux l [] = []
            group2Aux l (x:xs) = if elem x l
                then group2Aux (x:l) xs
                else l:group2Aux [x] xs

--13

myConcat :: [[a]] -> [a]
myConcat [] = []
myConcat (h:t) = h ++ myConcat t


--14

inits1 :: [a] -> [[a]]
inits1 [] = [[]]
inits1 (h:t) = ([]:initsAux h (inits1 t))

initsAux :: a -> [[a]] -> [[a]]
initsAux x [] = []
initsAux x (h:t) = (x:h): initsAux x t 

--15

tails1 :: [a] -> [[a]]
tails1 [] = [[]]
tails1 (h:t) = (h:t): tails1 t

--16

isPrefixOf1 :: Eq a => [a] -> [a] -> Bool
isPrefixOf1 n [] = False
isPrefixOf1 [] n = True
isPrefixOf1 (x:xs) (y:ys) = if x == y
    then isPrefixOf1 xs ys
    else isPrefixOf1 (x:xs) ys

--17

isSuffixOf1 :: Eq a => [a] -> [a] -> Bool
isSuffixOf1 l [] = False
isSuffixOf1 [] l = True
isSuffixOf1 (x:xs) (y:ys) = if (x:xs) == (y:ys)
    then True
    else isSuffixOf1 (x:xs) ys

--18

isSubsequenceOf1 :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf1 [] l = True
isSubsequenceOf1 l [] = False
isSubsequenceOf1 (x:xs) (y:ys) = if x == y
    then isSubsequenceOf1 xs ys
    else isSubsequenceOf1 (x:xs) ys

--19

elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 n [] = []
elemIndices1 n (h:t) = eAux 0 n (h:t)


eAux :: Eq a => Int -> a -> [a] -> [Int]
eAux i n [] = []
eAux i n (h:t) = if n == h
    then i: eAux (i+1) n t
    else eAux (i+1) n t

--20 

nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 [x] = [x]
nub1 (h:t) = if elem h t 
    then h: nub1 (nubAux h t)
    else h: nub1 t


nubAux :: Eq a => a -> [a] -> [a]
nubAux n [] = []
nubAux n (h:t) = if n == h
    then nubAux n t
    else h: nubAux n t 


--21

delete1 :: Eq a => a -> [a] -> [a]
delete1 n [] = []
delete1 n (h:t) = if n == h 
    then t
    else h:delete1 n t

--22

backslash :: Eq a => [a] -> [a] -> [a] 
backslash l [] = l
backslash [] l = []
backslash (x:xs) (y:ys) = if x == y
    then backslash xs ys
    else x:backslash xs (y:ys)

--23

union1 :: Eq a => [a] -> [a] -> [a] 
union1 l [] = l
union1 [] l = l
union1 l (h:t) = if elem h l
    then union1 l t
    else union1 (l ++ [h]) t

--24

intersect1 :: Eq a => [a] -> [a] -> [a] 
intersect1 l [] = []
intersect1 [] l = []
intersect1 (x:xs) l = if elem x l
    then x:intersect1 xs l
    else intersect1 xs l

--25

insert1 :: Ord a => a -> [a] -> [a]
insert1 n [] = []
insert1 n (h:t) | n > h = h : insert1 n t
                | otherwise = (n:h:t)

--26

unwords1 :: [String] -> String
unwords1 [] = " "
unwords1 [x] = x 
unwords1 (h:t) = h ++ " " ++ unwords1 t

--27

unlines1 :: [String] -> String
unlines1 [] = "\n"
unlines1 [x] = x ++ "\n"
unlines1 (h:t) = h ++ "\n" ++ unlines1 t

--28

pMaior :: Ord a => [a] -> Int 
pMaior [x] = 0
pMaior (h:t) = if h < myMaximum t
    then 1 + pMaior t
    else 0

myMaximum :: Ord a => [a] -> a
myMaximum [x] = x
myMaximum (h:h1:t) | h > h1 = myMaximum (h:t)
                   | h <= h1 = myMaximum (h1:t)

--29

temRepetidos :: Eq a => [a] -> Bool
temRepetidos [] = False
temRepetidos (h:t) = if elem h t
    then True
    else temRepetidos t

--30

algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (h:t) = if isDigit h 
    then h:algarismos t
    else algarismos t

--31

posImpares :: [a] -> [a]
posImpares [] = []
posImpares [x] = []
posImpares [x,y] = [y] 
posImpares (x:y:t) = y : posImpares t 

--32

posPares :: [a] -> [a]
posPares [] = []
posPares [x] = [x]
posPares [x,y] = [x]
posPares (x:y:t) = x : posPares t

--33

isSorted1 :: Ord a => [a] -> Bool 
isSorted1 [] = True
isSorted1 [x] = True
isSorted1 (h:t) = if h > head t
    then False
    else isSorted1 t

--34

iSort :: Ord a => [a] -> [a]
iSort [] = []
iSort (h:t) = insert h (iSort t)

--35

menor :: String -> String -> Bool
menor l [] = False
menor [] l = True
menor l1 l2 = (length l1) < (length l2)

--36

elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet n [] = False
elemMSet n ((x,y):t) = if n == x
    then True 
    else elemMSet n t 

--37

lengthMSet :: [(a,Int)] -> Int
lengthMSet [] = 0
lengthMSet ((x,y):t) = y + lengthMSet t

--38

converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((x,y):t) = converteMSetAux (x,y) ++ converteMSet t

converteMSetAux :: (a,Int) -> [a]
converteMSetAux (x,0) = []
converteMSetAux (x,y) = x:converteMSetAux (x,y-1)


--39

insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)] 
insereMSet n [] = [(n,1)]
insereMSet n ((x,y):t) = if n == x
    then ((x,y+1):t)
    else ((x,y): insereMSet n t)

--40

removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet n [(x,1)] = []
removeMSet n ((x,y):t) = if n == x
    then ((x,y-1):t)
    else ((x,y):removeMSet n t)

--41

constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet x = cMSetAux [] x

cMSetAux :: Eq a => [(a,Int)] -> [a] -> [(a,Int)]
cMSetAux x [] = x
cMSetAux x (h:t) = cMSetAux (insereMSet h x) t


--42


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


data Movimento = Norte | Sul | Este | Oeste
    deriving Show

-- 44

posicao :: (Int,Int) -> [Movimento] -> (Int,Int)
posicao (x,y) [] = (x,y)
posicao (x,y) (Norte:xs) = posicao (x,y+1) xs
posicao (x,y) (Sul:xs) = posicao (x,y-1) xs
posicao (x,y) (Oeste:xs) = posicao (x-1,y) xs
posicao (x,y) (Este:xs) = posicao (x+1,y) xs

-- 45

caminho :: (Int,Int) -> (Int,Int) -> [Movimento]
caminho (x,y) (a,b) | x == a && y == b = []
                    | y < b = (Norte:caminho (x,y+1) (a,b))
                    | y > b = (Sul:caminho (x,y-1) (a,b))
                    | x < a = (Este:caminho (x+1,y)(a,b))
                    | x > a = (Oeste:caminho (x-1,y)(a,b))

-- 46 

vertical :: [Movimento] -> Bool
vertical [] = True
vertical (Norte:xs) = vertical xs
vertical (Sul:xs) = vertical xs
vertical (x:xs) = False

data Posicao = Pos Int Int
    deriving Show

-- 47

maisCentral :: [Posicao] -> Posicao
maisCentral [p] = p
maisCentral ((Pos x y):(Pos a b):t) = maisCentral (maisCentralAux (Pos x y)(Pos a b):t) 


maisCentralAux :: Posicao -> Posicao -> Posicao
maisCentralAux (Pos x y) (Pos a b) = if n1 < n2
    then Pos x y
    else Pos a b
            where 
                n1 = (sqrt (realToFrac (x ^ 2)) + (realToFrac (y ^ 2)))  
                n2 = (sqrt (realToFrac (a ^ 2)) + (realToFrac (b ^ 2))) 


-- 48

vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos (Pos x y) ((Pos x1 y1):t) = if (y == y1 && x == (x1 +1)) || (y == y1 && x == (x1 -1)) || (x == x1 && y == (y1 +1)) || (x == x1 && y == (y1 -1)) 
        then (Pos x1 y1):vizinhos (Pos x y) t
        else vizinhos (Pos x y) t
vizinhos _ [] = []


-- 49

mesmaOrdenada :: [Posicao] -> Bool
mesmaOrdenada [Pos x y] = True
mesmaOrdenada ((Pos x1 y1):(Pos x2 y2):t) = if y1 == y2 
    then mesmaOrdenada ((Pos x1 y1):t)
    else False

data Semaforo = Verde | Amarelo | Vermelho
    deriving Show


-- 50

interseccaoOK :: [Semaforo] -> Bool
interseccaoOK l = aux12 l False

aux12 :: [Semaforo] -> Bool -> Bool
aux12 (Vermelho:t) v = aux12 t v
aux12 (_ :t) False = aux12 t True
aux12 (_ :t) True = False
aux12 [] _ = True
