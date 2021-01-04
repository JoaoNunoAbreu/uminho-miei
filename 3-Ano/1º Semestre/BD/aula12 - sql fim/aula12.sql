Use Caderneta;

SET GLOBAL log_bin_trust_function_creators = 1;

-- (1)

SELECT * FROM Cromo c, Jogador j, Posicao pos
WHERE c.jogador = j.Nr
AND (j.Equipa = 'SCB' OR j.Equipa = 'RAFC')
AND j.Posicao = pos.id
AND pos.designacao = 'Defesa';

-- (2)

SELECT j.nome FROM Jogador j, Equipa e
WHERE j.nr NOT IN(
	SELECT j.nr FROM Jogador j, Posicao p, Equipa e
    WHERE j.posicao = p.id
    AND (p.designacao = 'Defesa' OR p.designacao = 'Médio'))
    AND e.id = j.equipa
    AND (e.treinador = 'Jorge Jesus' OR e.treinador = 'Nuno Espírito Santo');

-- (3)

DROP VIEW IF EXISTS CromoEmFalta;
CREATE VIEW  CromoEmFalta AS 
	SELECT j.Nome, e.designacao, c.Nr FROM Equipa e, Jogador j, Cromo c
    WHERE e.id = j.equipa
    AND c.jogador = j.nr 
    AND c.Adquirido = 'N';
    
SELECT * FROM CromoEmFalta;

-- (4)

DELIMITER $$

DROP PROCEDURE IF EXISTS ListaCromosEquipa;
CREATE PROCEDURE ListaCromosEquipa(IN equipaX varchar(45))
BEGIN
	SELECT c.* FROM Equipa e, Cromo c, Jogador j
	WHERE c.jogador = j.Nr
	AND j.equipa = e.id
	AND e.designacao = equipaX
	ORDER BY c.PagCaderneta, c.Nr ASC;
END$$

DELIMITER ;

CALL ListaCromosEquipa('Sport Lisboa e Benfica');

-- (5)

DELIMITER $$

DROP PROCEDURE IF EXISTS ListaCromosCompleta;
CREATE PROCEDURE ListaCromosCompleta()
BEGIN
	SELECT Cromo.nr, TipoCromo.Descricao, Jogador.nome, Equipa.designacao, Cromo.Adquirido FROM Cromo
    LEFT OUTER JOIN Jogador ON Cromo.jogador = Jogador.nr
    LEFT OUTER JOIN Equipa ON Jogador.equipa = Equipa.id
    INNER JOIN TipoCromo ON Cromo.Tipo = TipoCromo.nr;
END$$

DELIMITER ;

CALL ListaCromosCompleta();

-- (6)

DELIMITER $$
DROP FUNCTION IF EXISTS CromoRepetido;
CREATE FUNCTION CromoRepetido(numeroCromo INT) RETURNS char(1) NOT DETERMINISTIC
BEGIN
	DECLARE adq CHAR(1);
    
    SELECT Cromo.Adquirido INTO adq FROM Cromo
    WHERE Cromo.nr = numeroCromo;
    
    RETURN (adq);
END $$

DELIMITER ;

SELECT CromoRepetido(87);
    
-- (7)

DELIMITER $$
DROP FUNCTION IF EXISTS tipoCromo;
CREATE FUNCTION tipoCromo(numeroCromo INT) RETURNS varchar(200) NOT DETERMINISTIC
BEGIN
	DECLARE tipo varchar(75);
    DECLARE nome varchar(75);
    DECLARE equipa varchar(45);
    
    SELECT tc.descricao,j.nome,p.Designacao INTO tipo,nome,equipa FROM Cromo c, Jogador j, Equipa p, TipoCromo tc
    WHERE c.Nr = numeroCromo
    AND c.Jogador = j.Nr
    AND j.Equipa = p.Id
    AND tc.Nr = c.Tipo;
    
    RETURN (CONCAT(tipo,", ",nome,", ", equipa));
END $$

DELIMITER ;

SELECT tipoCromo(87);

-- (8)

DROP TABLE IF EXISTS audCromos;
CREATE TABLE audCromos(
	NrCromo INT, 
    date DATETIME
);

-- DROP TRIGGER IF EXISTS dataAdquirido;
DELIMITER $$
CREATE TRIGGER dataAdquirido AFTER UPDATE ON Cromo
	FOR EACH ROW
    BEGIN
		IF(OLD.Adquirido = 'N' AND NEW.Adquirido = 'S') THEN
			INSERT INTO audCromos VALUES (NEW.Nr, NOW());
		END IF;
    END $$
    
DELIMITER ;

UPDATE Cromo SET Adquirido = 'S'
WHERE Nr = 37;

SELECT * FROM audCromos;



























