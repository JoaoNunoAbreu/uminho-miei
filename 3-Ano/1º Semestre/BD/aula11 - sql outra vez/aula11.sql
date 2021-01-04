USE Caderneta;

-- 1

DROP TABLE IF EXISTS ListaFaltas;
CREATE TABLE ListaFaltas(
	NrCromo INT PRIMARY KEY
);

INSERT INTO ListaFaltas SELECT Nr FROM Cromo WHERE Adquirido = 'N';

-- 2

-- 3

DELIMITER //
Drop PROCEDURE IF EXISTS ex3;
Create PROCEDURE ex3(IN _NrCromo INT)
BEGIN
	DECLARE v_error BOOL default 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
    
    SET AUTOCOMMIT = OFF;
    
    START transaction;
    
    -- BEGIN Logica
    UPDATE Cromo set adquirido = 'S' where nr = _NrCromo;
    DELETE FROM ListaFaltas where NrCromo = _NrCromo;
    -- END Logica
    
    IF(v_error) THEN
		rollback;
	END IF;
    
    commit;
    
END//

DELIMITER ;

CALL ex4(37);

-- 4

DELIMITER //
Drop PROCEDURE IF EXISTS ex4;
Create PROCEDURE ex4(IN _NrCromo INT)
BEGIN
	DECLARE v_error BOOL default 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
    
    SET AUTOCOMMIT = OFF;
    
    START transaction;
    
    -- BEGIN Logica
    UPDATE Cromo SET adquirido = 'N' WHERE nr = _NrCromo;
    INSERT INTO ListaFaltas VALUES(_NrCromo);
    -- END Logica
    
    IF(v_error) THEN
		rollback;
	END IF;
    
    commit;
    
END//
 
DELIMITER ;

CALL ex4(37);

-- 5

DROP TRIGGER IF EXISTS atualizaListaFaltas;

DELIMITER //
CREATE TRIGGER atualizaListaFaltas AFTER UPDATE ON Cromo
	FOR EACH ROW
    BEGIN
		IF(OLD.Adquirido = 'N' AND NEW.Adquirido = 'S') THEN
			DELETE FROM ListaFaltas WHERE NrCromo = OLD.Nr;
		END IF;
    END //
    
DELIMITER ;


-- 6

DELIMITER //
DROP PROCEDURE IF EXISTS listagem;
CREATE PROCEDURE listagem(IN idEquipa CHAR(3), OUT result_list varchar(2000))
BEGIN
	DECLARE v_finished integer default 0;
    DECLARE v_treinador varchar(50);
    DECLARE v_nome_equipa varchar(45);
    DECLARE v_nome_jogador varchar(75);
    DECLARE v_posicao varchar(20);

	DECLARE jogador_cursor CURSOR FOR
		SELECT j.Nome, p.Designacao FROM
        Jogador j, Posicao p WHERE j.posicao = p.id
        AND j.Equipa = idEquipa;
        
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_finished = 1;
    
    SELECT treinador,designacao INTO v_treinador,v_nome_equipa
		FROM Equipa WHERE Id = idEquipa;
        
	SET result_list = CONCAT(v_nome_equipa,";",v_treinador);
    
    OPEN jogador_cursor;
    get_jogador : LOOP
		IF v_finished = 1 THEN
			LEAVE get_jogador;
		END IF;
	FETCH jogador_cursor INTO v_nome_jogador, v_posicao;
    SET result_list = CONCAT(v_nome_jogador,":",v_posicao,";",result_list);
    END LOOP;
    CLOSE jogador_cursor;
END//
DELIMITER ;

SET @res = "";
CALL listagem('FCA',@res);
SELECT @res;

