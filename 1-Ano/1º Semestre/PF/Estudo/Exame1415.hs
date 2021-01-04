module Exame1415 where

-- 1

type TurmaL = [(Numero,Aluno)]
type Aluno = (Nome,Nota)
type Numero = Int
type Nome = String
type Nota = Float

t1 :: TurmaL
t1 = [(1,("Joao",10)),(2,("Nuno",5)),(3,("Abreu",9.4)),(4,("JBB",15)),(5,("Alcino",20))]

tordenada :: TurmaL 
tordenada = [(1,("Joao",0)),(2,("Nuno",2)),(3,("Abreu",9.4)),(4,("JBB",15)),(5,("Alcino",20))]

-- (a)

taxaAp :: TurmaL -> Float
taxaAp l = lenghtAprovados l / lengthTurma l

lengthTurma :: TurmaL -> Float
lengthTurma [] = 0
lengthTurma (h:t) = 1 + lengthTurma t

lenghtAprovados :: TurmaL -> Float
lenghtAprovados [] = 0
lenghtAprovados ((num,(nome,nota)):t) = if nota >= 9.5
    then 1 + lenghtAprovados t
    else lenghtAprovados t 

-- (b)

top :: Int -> TurmaL -> [String]
top i t = nomes i (ordenaReverse t)

nomes :: Int -> TurmaL -> [String]
nomes 0 _ = []
nomes x ((num,(nome,nota)):r) = nome : nomes (x-1) r

ordenaReverse :: TurmaL -> TurmaL
ordenaReverse t = reverse (ordena t)

ordena :: TurmaL -> TurmaL
ordena [] = []
ordena (h:t) = (insert h (ordena t))

insert :: (Numero,Aluno) -> TurmaL -> TurmaL
insert x [] = [x]
insert (num1,(nome1,n1)) ((num2,(nome2,n2)):t) = if n1 > n2
    then (num2,(nome2,n2)) : insert (num1,(nome1,n1)) t
    else (num1,(nome1,n1)) : (num2,(nome2,n2)):t

-- (c)

lNomeMax :: TurmaL -> Int
lNomeMax [] = 0
lNomeMax t = maximum (map (length) (nomeEmListas t))

nomeEmListas :: TurmaL -> [String]
nomeEmListas [] = []
nomeEmListas ((nr,(nome,nota)):t) = nome : nomeEmListas t

-- (d)

listaT :: TurmaL -> IO ()
listaT [] = return ()
listaT ((nr,(nome,nota)):r) = do {putStrLn ((show nr) ++ " ");
                                  putStrLn nome;
                                  if nota >= 9.5
                                    then putStrLn (show (round nota));
                                    else putStrLn "R";
                              listaT r}

-- 2

data TurmaA = Al (Numero,Aluno)
            | Fork (Numero,Numero) TurmaA TurmaA