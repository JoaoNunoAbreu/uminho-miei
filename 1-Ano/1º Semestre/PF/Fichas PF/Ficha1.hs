module Ficha1 where

perimetro :: Float -> Float

perimetro r = 2 * pi * r

dist :: (Double,Double) -> (Double,Double) -> Double

dist (x1,y1) (x2,y2) = sqrt (dy ^ 2 + dx ^ 2)
    where
        dx = abs (x1-x2)
        dy = abs (y1-y2)

primUlt :: [Int] -> (Int,Int)
primUlt x = (head x, last x)

multiplo :: Int -> Int -> Bool
multiplo m n = mod m n == 0

trancaImpar :: [Int] -> [Int]
trancaImpar x = if mod (length x) 2 /= 0
    then tail x
    else x

max2 :: Int -> Int -> Int
max2 x y = if x > y
    then x
    else y

max3 :: Int -> Int -> Int -> Int
max3 x y z = max2 (max2 x y) z

type Hora = (Int,Int)

hora :: Hora -> Bool
hora (x,y) = x >= 0 && x < 24 && y >= 0 && y < 60

comparar :: Hora -> Hora -> Bool
comparar (h1,m1) (h2,m2) = if h1 == h2
    then m1 > m2
    else h1 > h2

horaMinutos :: Hora -> Int
horaMinutos (h,m) = h * 60 + m

minHoras :: Int -> Hora
minHoras m = (div m 60, mod m 60)

diferencaHoras :: Hora -> Hora -> Int
diferencaHoras h1 h2 = abs (horaMinutos h1 - horaMinutos h2)

adMin :: Hora -> Int -> Hora
adMin (h1,m1) m = minHoras (horaMinutos (h1,m1) + m)

nRaizes :: Double -> Double -> Double -> Int
nRaizes a b c = if d > 0
    then 2
    else if d < 0
        then 0
        else 1
    where
    d = b ^ 2 - 4 * a * c


data Ponto = Cartesiano Double Double | Polar Double Double 
        deriving (Show, Eq)

toCartesiano :: Ponto -> Ponto
toCartesiano (Cartesiano x y) = (Cartesiano x y)
toCartesiano (Polar a d) = (Cartesiano (a*cos d) (a*sin d))  

posx :: Ponto -> Double
posx (Cartesiano x y) = abs x
posx p = posx (toCartesiano p)

posy :: Ponto -> Double
posy (Cartesiano x y) = abs y
posy p = posy (toCartesiano p)

raio :: Ponto -> Double
raio (Polar d a) = d
raio p = raio (toPolar p)

toPolar :: Ponto -> Ponto
toPolar (Polar d a) = Polar d a
toPolar (Cartesiano x y) = Polar d a
    where
        d = sqrt (x^2 + y^2)
        a = anguloXY x y 

anguloXY :: Double -> Double -> Double
anguloXY x y   | x > 0 && y > 0 = atan (y / x)
               | x<= 0 && y > 0 = pi - atan (y / (-x))
               | x > 0 && y<= 0 = pi + atan (y / (-x))
               | otherwise = -atan (-y / x)

angulo :: Ponto -> Double
angulo (Polar d a) = a
angulo p = angulo (toPolar p)

data Figura = Circulo Ponto Double
            | Rectangulo Ponto Ponto
            | Triangulo Ponto Ponto Ponto
            deriving (Show, Eq)

poligono :: Figura -> Bool
poligono (Circulo x y) = False
poligono (Triangulo x y z) = True
poligono (Rectangulo x y) = True

vertices :: Figura -> [Ponto]
vertices (Circulo x y) = []
vertices (Triangulo p1 p2 p3) = [p1,p2,p3] 
vertices (Rectangulo (Cartesiano x1 y1) (Cartesiano x2 y2)) = [Cartesiano x1 y1,
Cartesiano x1 y2, Cartesiano x2 y1, Cartesiano x2 y2]

