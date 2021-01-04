module Teste1617 where

type MSet a = [(a,Int)]

-- moda [('b',4),('a',2),('c',1)]

-- (a)

cardMSet :: MSet a -> Int 
cardMSet [] = 0
cardMSet ((a,i):t) = i + cardMSet t 

-- (b)

moda :: MSet a -> [a]
moda [] = []
moda [(a1,i1)] = [a1]
moda ((a1,i1):(a2,i2):t) | i1 > i2 = [a1]
                         | otherwise = a1 : moda ((a2,i2):t)


-- (c)

converteMSet :: MSet a -> [a]
converteMSet [] = []
converteMSet ((a,i):t) = (replicate i a) ++ converteMSet t

-- (d)

addNcopies :: Eq a => MSet a -> a -> Int -> MSet a
addNcopies [] a i = [(a,i)]
addNcopies ((a1,i1):t) a2 i2 | i2 < i1 = (a1,i1):addNcopies t a2 i2
                             | otherwise = (a2,i2):(a1,i1):t


-- 2

data SReais = AA Double Double 
            | FF Double Double
            | AF Double Double 
            | FA Double Double
            | Uniao SReais SReais

t1 :: SReais
t1 = Uniao (Uniao (AA 4.2 5.5) (AF 3.1 7.0)) (FF (-12.3) 30.0)

-- (a)

instance Show SReais where
    show (AA x y) = "]" ++ show x ++ "," ++ show y ++ "["
    show (FF x y) = "[" ++ show x ++ "," ++ show y ++ "]"
    show (AF x y) = "]" ++ show x ++ "," ++ show y ++ "]"
    show (FA x y) = "[" ++ show x ++ "," ++ show y ++ "["
    show (Uniao x y) = "(" ++ show x ++ "U" ++ show y ++ ")"

-- (b)

pertence :: Double-> SReais -> Bool
pertence x (AA m n) = m < x && x < n
pertence x (FF m n) = m <= x && x <= n 
pertence x (AF m n) = m < x && x <= n
pertence x (FA m n) = m <= x && x < n
pertence x (Uniao m n) = pertence x m || pertence x n

-- (c)

tira :: Double -> SReais -> SReais
tira x (AA m n) = (Uniao (AA m x) (AA x n))
tira x (FF m n) = (Uniao (FA m x) (AF x n))
tira x (AF m n) = (Uniao (AA m x) (AF x n))
tira x (FA m n) = (Uniao (FA m x) (AA x n))
tira x (Uniao m n) = (Uniao (tira x m) (tira x n))

-- 3

data RTree a = R a [RTree a]

arv1 :: RTree Int
arv1 = R 1 [R 3 [R 5 [], R 7 [R 6 [],R 11 []]], R 8 [R 3 [], R 2 []]]

-- (a)

percorre :: [Int] -> RTree a -> Maybe [a]
percorre [] (R x _) = Just [x]
percorre (h:t) (R x l) = if h > length l
    then Nothing
    else case (percorre t (l!!(h-1))) of
                                          Just c -> Just (x:c)
                                          Nothing -> Nothing












percorre' :: [Int] -> RTree a -> Maybe [a]
percorre' [] (R x _) = Just [x]
percorre' (h:t) (R x l) | h>(length l) = Nothing
                       | otherwise = case (percorre' t ((!!) l (h-1))) of
                                          Just c -> Just (x:c)
                                          Nothing -> Nothing