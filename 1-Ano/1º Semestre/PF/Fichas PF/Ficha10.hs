import System.Random
import Data.Char
import Data.List


bingo :: IO()
bingo = do bingi []

bingi :: [Int] -> IO ()
bingi l = do 
    if length l == 10 
        then do { 
                putStrLn "Já saíram os 10 números";
                return ()
            }
        else do {
            x <- randomRIO (1,10);
            if elem x l 
                then do bingi l
                else do getChar
                        print x
                        bingi (x:l) 
            }


-- 2

data Aposta = Ap [Int] (Int,Int)

-- (a)

valida :: Aposta -> Bool
valida a = validaQnt a && validaIntervalo a && not (validaRepeticoes a)

validaIntervalo :: Aposta -> Bool
validaIntervalo (Ap [] _) = True
validaIntervalo (Ap (h:t) (x,y)) = if h >= 1 && h <= 50
    then x /= y && x >= 1 && x <= 9 && y >= 1 && y <= 9
    else False

validaQnt :: Aposta -> Bool
validaQnt (Ap l p) = length l == 5

validaRepeticoes :: Aposta -> Bool
validaRepeticoes (Ap [] _) = True
validaRepeticoes (Ap l _) = testaSeRepete l

testaSeRepete :: [Int] -> Bool
testaSeRepete [] = False
testaSeRepete (h:t) = if elem h t
    then True
    else testaSeRepete t

-- (b)

comuns :: Aposta -> Aposta -> (Int,Int)
comuns (Ap l1 p1) (Ap l2 p2) = (length (intersetaLista l1 l2) , intersetaPares p1 p2)

intersetaLista :: [Int] -> [Int] -> [Int]
intersetaLista [] _ = []
intersetaLista (h:t) l = if elem h l
    then h : intersetaLista t l
    else intersetaLista t l

intersetaPares :: (Int,Int) -> (Int,Int) -> Int
intersetaPares (m,n) (x,y) | m == x && n == y = 2
                           | m == y && n == x = 2
                           | m == x = 1
                           | m == y = 1
                           | n == x = 1
                           | n == y = 1
                           | otherwise = 0

-- (c)

instance Eq Aposta where
    (==) a b = comuns a b == (5,2) 

instance Show Aposta where
  show (Ap nums (x,y)) = "Os números registados foram " ++ show nums ++ "\nAs Estrelas Registadas foram " ++ show x ++ " e " ++ show y ++ "\n"

premio :: Aposta -> Aposta -> Maybe Int
premio x y = calcSomaPremios (comuns x y)

calcSomaPremios :: (Int,Int) -> Maybe Int
calcSomaPremios (x,y) | x == 5 && y == 2 = Just 1
                      | x == 5 && y == 1 = Just 2
                      | x == 5 && y == 0 = Just 3
                      | x == 4 && y == 2 = Just 4
                      | x == 4 && y == 1 = Just 5
                      | x == 4 && y == 0 = Just 6
                      | x == 3 && y == 2 = Just 7
                      | x == 2 && y == 2 = Just 8
                      | x == 3 && y == 1 = Just 9
                      | x == 3 && y == 0 = Just 10
                      | x == 1 && y == 2 = Just 11
                      | x == 2 && y == 1 = Just 12
                      | x == 2 && y == 0 = Just 13
                      | otherwise = Nothing

-- (d)

leAposta :: IO Aposta
leAposta = do 
            putStrLn "Insira os números you dumb shit"
            nums <- getLine;
            putStrLn "Insira as estrelas yo fuckin' faggot"
            est <- getLine;
            let ap = (Ap (read nums) (read est))
            if (valida ap)
                then return ap
                else do leAposta


joga :: Aposta -> IO () 
joga a = do
          putStrLn "Insira a aposta que queres oh boi"
          ap <- leAposta;
          case (premio a ap) of
            (Just n) -> putStrLn ("Parabéns, o seu prémio é " ++ show n)
            Nothing -> putStrLn "Não ganhaste nada burro"

-- (e)

geraChave :: IO Aposta
geraChave = do 
             putStrLn "Criando as merdas"
             n1 <- randomRIO (1,50)
             n2 <- randomRIO (1,50)
             n3 <- randomRIO (1,50)
             n4 <- randomRIO (1,50)
             n5 <- randomRIO (1,50)
             e1 <- randomRIO (1,9)
             e2 <- randomRIO (1,9)
             let l = (Ap [n1,n2,n3,n4,n5] (e1,e2))
             if valida l
              then return l
              else do geraChave;

-- (f)

ciclo :: Aposta -> IO ()
ciclo p = do
          x <- menu;
          case x of
            "1" -> do joga p;
                      ciclo p
            "2" -> do geraChave
                      ciclo p
            "0" -> do return ()
            _ -> do ciclo p


main :: IO ()
main = do ch <- geraChave
          ciclo ch

menu :: IO String
menu = do { putStrLn menutxt
; putStr "Opcao: "
; c <- getLine
; return c
}
  where menutxt = unlines ["",
                            "Apostar ........... 1",
                            "Gerar nova chave .. 2",
                            "",
                            "Sair .............. 0"]