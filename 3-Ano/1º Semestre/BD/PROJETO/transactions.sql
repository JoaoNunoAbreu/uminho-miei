USE testeclinico;

-- 1. Adicionar Atleta,se não exister modalidade adiciona na base de dados a modalidade

DROP procedure IF EXISTS addAtleta;
DELIMITER $$
CREATE PROCEDURE addAtleta(IN nif INT,IN nome VARCHAR(55), IN morada VARCHAR(200),IN data_de_nascimento DATE, IN contacto int(9), 
														IN sexo ENUM('M','F'),
														IN nacionalidade VARCHAR(45),IN cod_postal VARCHAR(45),
                                                        IN modalidade VARCHAR(45),IN categoria VARCHAR(45),IN idClube int)
BEGIN
	   DECLARE v_error BOOL default 0;
       DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
 
	
		SET AUTOCOMMIT = OFF;
	    START TRANSACTION;
        -- criar atleta
        BEGIN
			INSERT INTO Atleta (nif,nome,morada,data_de_nascimento,contacto,sexo,nacionalidade,codigo_postal,acumulado,idClube)
			VALUES (nif,nome,morada,data_de_nascimento,contacto,sexo,nacionalidade,cod_postal,0,idClube);
            -- se não existir modalidade cria registo
			IF NOT EXISTS(SELECT * from Modalidade WHERE designacao=modalidade AND categoria=categoria) THEN 
				INSERT INTO Modalidade(designacao,categoria)
				VALUES (modalidade,categoria);
			END IF;
           -- adiciona à relação do atleta com a modalidade
			INSERT INTO Atleta_has_Modalidade(atleta_nif,modalidade_designacao,modalidade_categoria)
			VALUES (nif,modalidade,categoria);
        END;
        
        IF(v_error) THEN
          ROLLBACK;
        END IF;
        
		COMMIT;
    
END $$
DELIMITER ;

CALL addAtleta(123831292,"Josssao","Rua de Silvares","1999-11-14",919917168,'F',"Portuguesa","4000-113","Natacao","100m",null);

-- 2 Adiciona um boletim

DROP procedure IF EXISTS addBoletim;
DELIMITER $$

CREATE PROCEDURE addBoletim(IN id Int,IN data_marcacao Datetime,IN atleta_nif int, IN testeclinico_id int, IN profsaude_id int,IN pago TINYINT)
BEGIN
    DECLARE v_error BOOL default 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
    SET AUTOCOMMIT = OFF;
    
    START TRANSACTION;
        BEGIN
            -- Vê se o médico está disponível
            IF EXISTS(Select * FROM Boletim_Clinico
                WHERE Boletim_Clinico.profsaude_id=profsaude_id AND Boletim_Clinico.data_marcacao=data_marcacao) THEN
            SET v_error = 1;
            END IF;
            -- Vê se o profissional de sáude tem especialidade para o teste
            IF NOT EXISTS( 
                SELECT id FROM Profissional_de_Saude
                WHERE id = profsaude_id
                AND especialidade_id IN(
                    SELECT Especialidade_id FROM Teste_Clinico_has_Especialidade t
                    WHERE t.Teste_Clinico_id = testeclinico_id
                )
             )
			THEN SET v_error = 1;
            END IF;
            	INSERT INTO Boletim_Clinico(id,data_marcacao,atleta_nif,testeclinico_id,profsaude_id,reagendadom,pago)
			     VALUES (id,data_marcacao,atleta_nif,testeclinico_id,profsaude_id,0,pago);
          
            
        END;
        
    IF(v_error) THEN
        ROLLBACK;
    END IF;
        
    COMMIT;
    
END $$
DELIMITER ;

-- CALL addBoletim(1,"1999-12-12 22:00:00",4);

-- 3 Adiciona código postal

DELIMITER $$
DROP PROCEDURE IF EXISTS addCodPostal;
CREATE PROCEDURE addCodPostal(IN cod_postal VARCHAR(45),IN localidade VARCHAR(45))

BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        -- criar profissional de saude
        BEGIN
          INSERT INTO Codigo_Postal (cod_postal,localidade)
          VALUES (cod_postal,localidade);
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addCodPostal("4600-632","Amarante");

-- 4 Adiciona Especialidade

DELIMITER $$
DROP PROCEDURE IF EXISTS addEsp;
CREATE PROCEDURE addEsp(IN designacao VARCHAR(45))

BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
  
  START TRANSACTION;
        -- criar Especialidade
        BEGIN
          INSERT INTO Especialidade(id,designacao)
          VALUES (NULL,designacao);
        END;
        
 IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addEsp("Novo");

-- 5 Adiciona na relação especialidade teste clinico para saber que especialidade estão aptas a realizar um teste clinico

DELIMITER $$
DROP procedure IF EXISTS addEspecialidadeTesteClinico;
CREATE PROCEDURE addEspecialidadeTesteClinico(IN especialidade_id int,IN teste_clinico_id int)
BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        BEGIN
         INSERT INTO Teste_Clinico_has_Especialidade (especialidade_id,teste_clinico_id)
          VALUES (especialidade_id,teste_clinico_id);
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addEspecialidadeTesteClinico(1,4);

-- 6 Adiciona profissional de saúde

DELIMITER $$
DROP procedure IF EXISTS addProf;
CREATE PROCEDURE addProf(IN id INT,IN morada VARCHAR(200), IN contacto INT,IN data_de_nascimento DATE,IN nome VARCHAR(45),IN codigo_postal VARCHAR(45),IN especialidade_id INT)

BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        -- criar profissional de saude
        BEGIN
          INSERT INTO Profissional_de_Saude (id,morada,contacto,data_de_nascimento,nome,codigo_postal,especialidade_id)
          VALUES (id,morada,data_de_nascimento,nome,contacto,codigo_postal,especialidade_id);
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addProf(1,"Rua de Silvares",919917168,"1999-11-14","jojo","4000-113",1);

-- 7 Adiciona um médico a uma equipa responsável por um boletim,adiciona na relação equipa

DELIMITER $$
DROP procedure IF EXISTS addProfSaudeEquipaBoletim;
CREATE PROCEDURE addProfSaudeEquipaBoletim(IN idprofsaude Int,IN idbolclinico int)
BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        BEGIN
           INSERT INTO Equipa (profsaude_id,bolclinico_id)
           VALUES (idprofsaude,idbolclinico);
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addProfSaudeEquipaBoletim(1,1);

-- 8 Adiciona um teste clinico

DELIMITER $$
DROP PROCEDURE IF EXISTS addTest;
CREATE PROCEDURE addTest(IN nome VARCHAR(45))

BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
  
  START TRANSACTION;
        -- criar Teste Clínico
        BEGIN
          INSERT INTO Teste_clinico(id,nome)
          VALUES (NULL,nome);
        END;
        
 IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL addTest("gg bois");

-- 9 Reagenda um teste

DROP procedure IF EXISTS reagendarTeste;
DELIMITER $$
CREATE PROCEDURE reagendarTeste(IN id Int,IN data_marcacao Datetime,IN profsaude_Id int)
BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        BEGIN
            IF EXISTS(Select * FROM Boletim_Clinico
                      WHERE Boletim_Clinico.profsaude_id=profsaude_id AND Boletim_Clinico.data_marcacao=data_marcacao) THEN
             SET v_error=1;
            END IF;
             -- Vê se o profissional de sáude tem especialidade para o teste
            IF NOT EXISTS( 
                SELECT id FROM Profissional_de_Saude ps
                WHERE ps.id = profsaude_id
                AND profsaude_id IN(
                    SELECT Especialidade_id FROM Teste_Clinico_has_Especialidade t 
                    WHERE t.Teste_Clinico_id = (SELECT testeclinico_id FROM Boletim_Clinico bc WHERE  bc.id=id)
                )
             )
             
			THEN SET v_error = 1;
            END IF;
          UPDATE Boletim_Clinico 
          SET Boletim_Clinico.profsaude_id=profsaude_id,Boletim_Clinico.data_marcacao=data_marcacao,reagendado=1
          WHERE Boletim_Clinico.id=id;
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

CALL reagendarTeste(1,"2055-12-12 22:00:00",10);

DELIMITER $$
CREATE PROCEDURE addClube(IN designacao VARCHAR(45))

BEGIN
  DECLARE v_error BOOL default 0;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
  
  SET AUTOCOMMIT = OFF;
    
  START TRANSACTION;
        BEGIN
          INSERT INTO Clube (id,designacao)
          VALUES (null,designacao);
        END;
        
  IF(v_error) THEN
    ROLLBACK;
  END IF;
        
  COMMIT;
    
END $$
DELIMITER ;

