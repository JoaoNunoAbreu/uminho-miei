module Ficha9 where

data Frac = F Integer Integer

ex :: Frac
ex = F (-33) (-51)

-- (a)

normaliza :: Frac -> Frac
normaliza (F x y) | y < 0 = F (-div x n) (-div y n)
                  | otherwise = F (div x n) (div y n)
        where
            n = mdc (abs x) (abs y)

mdc :: Integer -> Integer -> Integer
mdc a b | a == b = a
mdc a b | a > b = mdc (a-b) b
mdc a b | b > a = mdc a (b-a)

-- (b)

instance Eq Frac where
    -- (==) :: Frac -> Frac -> Bool
    f1 == f2 = x1 == x2 && y1 == y2
        where 
            (F x1 y1) = normaliza f1
            (F x2 y2) = normaliza f2

instance Ord Frac where
    -- compare :: Frac -> Frac -> Ordering
    compare f1 f2 | y1 > y2 = LT
                  | y1 < y2 = GT
                  | y1 == y2 = compare x1 x2
        where
            (F x1 y1) = normaliza f1
            (F x2 y2) = normaliza f2

instance Show Frac where
    show (F x y) = "(" ++ show x ++ "/" ++ show y ++ ")"

instance Num Frac where
    f1 + f2 | y1 == y2 = normaliza (F (x1 + x2) y1)
            | y1 > y2 = normaliza ((F (x1 * y2) (y1 * y2)) + (F (x2 * y1) (y1 * y2)))
        where
            (F x1 y1) = normaliza f1
            (F x2 y2) = normaliza f2
    (F x1 y1) * (F x2 y2) = (F (x1*x2) (y1*y2))

    negate f1 = normaliza (F (-a) b)
        where (F a b) = f1

    abs f1 =
        let (F a b) = normaliza f1
            in (F (abs a) (abs b))

    signum f1 =
        let (F a b) = normaliza f1 in
            if a > 0 then 1 else (-1)

    fromInteger f1 = (F f1 1)


compara :: Frac -> [Frac] -> [Frac]
compara f t = filter (func f) t
    where
        func :: Frac -> Frac -> Bool
        func f1 f2 = (2 * f1) < f2


-- 2

data Exp a = Const a
           | Simetrico (Exp a)
           | Mais (Exp a) (Exp a)
           | Menos (Exp a) (Exp a)
           | Mult (Exp a) (Exp a)
-- (a)

instance Show a => Show (Exp a) where
    show (Const x) = show x
    show (Simetrico x) = "-" ++ show x
    show (Mais x y) = "(" ++ show x ++ "+" ++ show y ++ ")"
    show (Menos x y) = "(" ++ show x ++ "-" ++ show y ++ ")"
    show (Mult x y) = "(" ++ show x ++ "*" ++ show y ++ ")"

instance Eq a => Eq (Exp a) where
    
    Const a == Const b = a == b
    Simetrico a == Simetrico b = a == b
    Mais x y == Mais a b   = (x == a && y == b) || (x == b && y == a)
    Menos x y == Menos a b = (x == a && y == b)
    Mult x y == Mult a b   = (x == a && y == b) || (x == b && y == a)
    _ == _ = False
