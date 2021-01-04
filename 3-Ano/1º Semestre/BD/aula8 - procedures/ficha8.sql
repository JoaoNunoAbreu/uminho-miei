SET GLOBAL log_bin_trust_function_creators = 1;

-- (a)

DELIMITER $$
CREATE PROCEDURE AtualizarPrecoEspecialidade(IN perc int, IN ano int)
BEGIN

DECLARE v_finished integer default 0;
DECLARE v_designacao varchar(100);
DECLARE v_media decimal(5,2);

DECLARE designacao_cursor CURSOR FOR

SELECT e.designcao, avg(c.preco) FROM ESPECIALIDADE e, MEDICO m, CONSULTA c
WHERE e.id_especialidade = m.especialidade 
AND m.id_medico = c.id_medico
AND year(c.data_hora) = ano
GROUP BY e.designacao;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_finished = 1; -- Fim do ciclo

OPEN designacao_cursor;
get_designacao : LOOP
	FETCH designacao_cursor INTO v_designacao, v_media;
    IF v_finished = 1 THEN
		LEAVE get_designacao;
	END IF;
    UPDATE ESPECIALIDADE SET preco = (v_media*(perc/100)) WHERE designacao = v_designacao;
    
END LOOP;
CLOSE designacao_cursor;

END$$


-- (b)

DELIMITER $$
CREATE FUNCTION TemConsultas(id int) RETURNS BOOL NOT DETERMINISTIC
BEGIN

	DECLARE temConsultas BOOL;
	DECLARE val INT;

	SELECT COUNT(1) INTO val FROM MEDICO m, CONSULTA c
	WHERE m.id_medico = c.id_medico
	AND m.id_medico = id;

	if(val < 1) THEN
		SET temConsultas = false;
	else
		SET temConsultas = true;
	END IF;

	RETURN temConsultas;
END$$

DELIMITER ;

INSERT INTO MEDICO VALUES(12,'medico1','rua','4700-001','1999-09-09',1,'2000-01-01');

DELETE FROM MEDICO WHERE temConsutlas(id_medico) = 0;

SELECT * FROM MEDICO;


-- (c)

DELIMITER $$

CREATE FUNCTION CodigoPostalUsado(CodPostal varchar(8)) RETURNS BOOL NOT DETERMINISTIC
BEGIN

	DECLARE usado bool;
    DECLARE resultado int;
    
	SELECT COUNT(1) into resultado FROM MEDICO m, PACIENTE p
    WHERE m.codigo_postal = CodPostal
    OR p.codigo_postal = CodPostal;
    
    if(resultado < 1) THEN
		SET usado = false;
	else
		SET usado = true;
	END IF;
    
    RETURN usado;
END$$

DELIMITER ;

DELETE FROM CODIGO_POSTAL WHERE CodigoPostalUsado(codigo_postal) = 0;

-- SELECT CodigoPostalUsado


-- (d)

ALTER TABLE MEDICO 
	ADD COLUMN TotalFaturado decimal(10,2);

-- Usar o cursor aqui da alínea (a) para o total faturado em vez dos preços das consultas

SELECT m.id_medico, sum(preco) as faturado FROM CONSULTA c, MEDICO m
WHERE c.id_medico = m.id_medico
GROUP BY m.id_medico;

DELIMITER $$

CREATE TRIGGER acumularFaturado AFTER INSERT
   ON CONSULTA FOR EACH ROW
BEGIN

	UPDATE MEDICO
    SET totalFaturado = totalFaturado + new.preco
    WHERE m.id_medico = new.id_medico;

END$$

DELIMITER ;


-- (e)

CREATE VIEW AcumuladoPaciente AS
SELECT id_paciente, month(data_hora) as mes, year(data_hora) as ano, sum(preco) as valor
FROM CONSULTA c
GROUP BY c.id_paciente, mes, ano;


-- (f)

CREATE VIEW MensalEspecialidade AS 
SELECT m.especialidade as Id, month(c.data_hora) as Mes, year(c.data_hora) as Ano, count(m.especialidade) as quantidade, sum(c.preco) as valor
FROM MEDICO m, CONSULTA c
WHERE m.id_medico = c.id_medico
GROUP BY m.especialidade, mes, ano;









