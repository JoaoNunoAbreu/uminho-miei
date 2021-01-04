module Ficha8 where

data ExpInt = Const Int
            | Simetrico ExpInt
            | Mais ExpInt ExpInt
            | Menos ExpInt ExpInt
            | Mult ExpInt ExpInt



-- 1

-- (a)

calcula :: ExpInt -> Int
calcula (Const x) = x
calcula (Simetrico x) = calcula x * (-1)
calcula (Mais x y) = calcula x + calcula y
calcula (Menos x y) = calcula x - calcula y
calcula (Mult x y) = calcula x * calcula y

-- (b)

infixa :: ExpInt -> String
infixa (Const x) = show x
infixa (Simetrico x) = "-" ++ infixa x
infixa (Mais x y) = "(" ++ infixa x ++ "+" ++ infixa y ++ ")"
infixa (Menos x y) = "(" ++ infixa x ++ "-" ++ infixa y ++ ")"
infixa (Mult x y) = "(" ++ infixa x ++ "*" ++ infixa y ++ ")"

-- (c)

posfixa :: ExpInt -> String
posfixa (Const x) = show x
posfixa (Simetrico x) = posfixa x ++ "-"
posfixa (Mais x y) = posfixa x ++ " " ++ posfixa y ++ " +"
posfixa (Menos x y) = posfixa x ++ " " ++ posfixa y ++ " -"
posfixa (Mult x y) = posfixa x ++ " " ++ posfixa y ++ " *"

-- 2

data RTree a = R a [RTree a]
    deriving (Show)

r1 :: Num a => RTree a
r1 = R 5 [(R 2 [(R 1 []) ,(R 3 []) , (R 4 [])]) , (R 6 [])]

-- (a)

soma :: Num a => RTree a -> a
soma (R x []) = x
soma (R x l) = x + sum (map soma l)

-- (b)

altura :: RTree a -> Int
altura (R x []) = 1
altura (R x l) = 1 + maximum (map altura l)

-- (c)

prune :: Int -> RTree a -> RTree a
prune 1 (R x l) = (R x [])
prune i (R x l) = R x (map (prune (i-1)) l) 

-- (d)

mirror :: RTree a -> RTree a
mirror (R x []) = (R x [])
mirror (R x l) = (R x (map mirror (reverse l)))

-- (e)

postorder :: RTree a -> [a]
postorder (R x l) = foldr (++) [x] (map postorder l)

-- 3

data BTree a = Empty | Node a (BTree a) (BTree a)
data LTree a = Tip a | Fork (LTree a) (LTree a)

l1 :: Num a => LTree a
l1 = (Fork (Fork (Tip 2) (Tip 4)) (Tip 6))

-- (a)

ltSum :: Num a => LTree a -> a
ltSum (Tip x) = x
ltSum (Fork x y) = ltSum x + ltSum y

-- (b)

listaLT :: LTree a -> [a] 
listaLT (Tip x) = [x]
listaLT (Fork x y) = (listaLT x) ++ (listaLT y)

-- (c)

ltHeight :: LTree a -> Int
ltHeight (Tip x) = 1
ltHeight (Fork x y) = ltHeight x + ltHeight y

-- 3

data FTree a b = Leaf b | No a (FTree a b) (FTree a b)

-- (a)

splitFTree :: (FTree a b) -> (BTree a, LTree b) 
splitFTree (Leaf x) = (Empty, Tip x)
splitFTree (No x l r) = (Node x l1 r1, Fork l2 r2) 
                                where (l1,l2) = splitFTree l
                                      (r1,r2) = splitFTree r

-- (b)

joinTrees :: (BTree a) -> (LTree b) -> Maybe (FTree a b)
joinTrees Empty (Tip x) = Just (Leaf x)
joinTrees (Node x lnode rnode) (Fork l r) = case (joinTrees lnode l) of
    Nothing -> Nothing
    Just l1-> case (joinTrees rnode r) of
        Nothing -> Nothing
        Just r1 -> Just (No x l1 r1)
joinTrees _ _ = Nothing 
