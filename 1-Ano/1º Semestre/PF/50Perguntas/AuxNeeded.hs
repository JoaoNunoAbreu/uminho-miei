import Data.List
import Data.Char 
import Data.Either

----------------------------------------------

group2 :: Eq a => [a] -> [[a]]
group2 [] = []
group2 (x:xs) = group2Aux [x] xs
        where
            group2Aux l [] = []
            group2Aux l (x:xs) = if elem x l
                then group2Aux (x:l) xs
                else l:group2Aux [x] xs

----------------------------------------------

inits1 :: [a] -> [[a]]
inits1 [] = [[]]
inits1 (h:t) = ([]:initsAux h (inits1 t))

initsAux :: a -> [[a]] -> [[a]]
initsAux x [] = []
initsAux x (h:t) = (x:h): initsAux x t 

----------------------------------------------

elemIndices1 :: Eq a => a -> [a] -> [Int]
elemIndices1 n [] = []
elemIndices1 n (h:t) = eAux 0 n (h:t)


eAux :: Eq a => Int -> a -> [a] -> [Int]
eAux i n [] = []
eAux i n (h:t) = if n == h
    then i: eAux (i+1) n t
    else eAux (i+1) n t

----------------------------------------------

nub1 :: Eq a => [a] -> [a]
nub1 [] = []
nub1 [x] = [x]
nub1 (h:t) = if elem h t 
    then h: nub1 (nubAux h t)
    else nub1 t


nubAux :: Eq a => a -> [a] -> [a]
nubAux n [] = []
nubAux n (h:t) = if n == h
    then nubAux n t
    else h: nubAux n t 

----------------------------------------------

pMaior :: Ord a => [a] -> Int 
pMaior [x] = 0
pMaior (h:t) = if h < myMaximum t
    then 1 + pMaior t
    else 0

myMaximum :: Ord a => [a] -> a
myMaximum [x] = x
myMaximum (h:h1:t) | h > h1 = myMaximum (h:t)
                   | h <= h1 = myMaximum (h1:t)

----------------------------------------------

converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((x,y):t) = converteMSetAux (x,y) ++ converteMSet t

converteMSetAux :: (a,Int) -> [a]
converteMSetAux (x,0) = []
converteMSetAux (x,y) = x:converteMSetAux (x,y-1)

----------------------------------------------

constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet [] = []
constroiMSet x = cMSetAux [] x

cMSetAux :: Eq a => [(a,Int)] -> [a] -> [(a,Int)]
cMSetAux x [] = x
cMSetAux x (h:t) = cMSetAux (insereMSet h x) t

insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet n [] = [(n,1)]
insereMSet n ((x,y):t) = if n == x 
    then ((x,y+1):t)
    else ((x,y):insereMSet n t)

----------------------------------------------

data Posicao = Pos Int Int
    deriving Show


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


----------------------------------------------

data Semaforo = Verde | Amarelo | Vermelho
    deriving Show


interseccaoOK :: [Semaforo] -> Bool
interseccaoOK l = aux12 l False

aux12 :: [Semaforo] -> Bool -> Bool
aux12 (Vermelho:t) v = aux12 t v
aux12 (_ :t) False = aux12 t True
aux12 (_ :t) True = False
aux12 [] _ = True
