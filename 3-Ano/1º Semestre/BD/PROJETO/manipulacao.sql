USE testeclinico;

-- Saber número de testes que um dado Atleta realizou

DELIMITER $$
DROP PROCEDURE IF EXISTS numerotestesAtleta;
CREATE PROCEDURE numerotestesAtleta (IN nif int)
BEGIN

	SELECT count(*) FROM Boletim_Clinico bc
	WHERE bc.atleta_nif = nif;

END$$
DELIMITER ;

CALL numerotestesAtleta(200461184);

-- Profissionais saude com numero de testes pelos quais foram responsaveis

DROP VIEW IF EXISTS numTestesResp;
CREATE VIEW numTestesResp AS
SELECT ps.nome AS `Nome Médico`,count(ps.id) AS `Testes Realizados` FROM Boletim_Clinico bc,Profissional_de_Saude ps
WHERE bc.profsaude_id=ps.id
GROUP BY ps.nome
ORDER BY `Testes Realizados` DESC;

SELECT * FROM numTestesResp;

-- Profissionais saude com numero de testes em que participaram    

DROP VIEW IF EXISTS numTestesPart;
CREATE VIEW numTestesPart AS
SELECT ps.nome AS `Nome Médico`,count(ps.id) AS `Testes Realizados` FROM EQUIPA e,Profissional_de_Saude ps
WHERE e.profsaude_id = ps.id
GROUP BY ps.nome
ORDER BY `Testes Realizados` DESC;

-- Teste mais realizado numa cidade

DELIMITER $$
DROP PROCEDURE IF EXISTS testemaisRealCidade;
CREATE PROCEDURE testemaisRealCidade (IN cidade VARCHAR(45))

BEGIN

    SELECT t.nome AS `Nome`,count(t.id) AS `Maximo` FROM Codigo_Postal cp
    INNER JOIN Atleta a ON cp.localidade=cidade AND a.codigo_postal=cp.cod_postal
    INNER JOIN Boletim_Clinico bc ON bc.atleta_nif=a.nif
    INNER JOIN Teste_clinico t ON t.id=bc.testeclinico_id
    GROUP BY t.id
    ORDER BY `Maximo` DESC
    LIMIT 1;

END$$
DELIMITER ;

CALL testemaisRealCidade("Faro");

-- Total faturado pela clinica
DROP VIEW IF EXISTS totFaturado;
CREATE VIEW totFaturado AS
	SELECT sum(t.preco) AS `Total Faturado` FROM Boletim_Clinico bc
    INNER JOIN Teste_Clinico t ON t.id=bc.testeclinico_id AND bc.data_marcacao<=NOW();
    
SELECT * FROM totFaturado;

-- Saber quantos teste já foram realizados na clinica

DROP VIEW IF EXISTS totalTesteRealizados;
CREATE VIEW totalTesteRealizados AS
	SELECT count(bc.id) FROM Boletim_Clinico bc
    WHERE bc.data_marcacao<=NOW();
    
SELECT * FROM totalTesteRealizados;

-- Saber quanto faturou num mes a clinica

DROP PROCEDURE IF EXISTS faturadoMes;
DELIMITER $$
CREATE PROCEDURE faturadoMes (IN mes int,IN ano int)
BEGIN
	SELECT sum(t.preco) FROM Boletim_Clinico bc
    INNER JOIN Teste_Clinico T ON MONTH(bc.data_marcacao)=mes AND YEAR(bc.data_marcacao)=ano AND bc.pago=1 AND t.id=bc.testeclinico_id;
END$$
DELIMITER ;

CALL faturadoMes(12,2020);

-- Ver clubes que têm protocolo com a clinica
DROP VIEW IF EXISTS clubes;
CREATE VIEW clubes AS
SELECT c.id,c.designacao FROM Clube c;

SELECT * FROM clubes;

-- Numero de atletas registados por clube
DROP VIEW IF EXISTS clubeNmrAtletas;
CREATE VIEW clubeNmrAtletas AS
SELECT c.designacao AS `Nome Clube`,count(a.nif) AS `Numero de atletas` FROM Clube c
INNER JOIN Atleta a ON a.idClube=c.id 
GROUP BY c.designacao
ORDER BY `Numero de atletas` DESC;

SELECT * FROM clubeNmrAtletas;

DROP VIEW IF EXISTS top5testes;
CREATE VIEW top5testes AS
SELECT t.nome,count(bc.testeclinico_id) AS `Num testes realizados` FROM Boletim_Clinico bc
INNER JOIN Teste_Clinico t ON t.id=bc.testeclinico_id
GROUP BY t.nome
ORDER BY `Num testes realizados` DESC
LIMIT 5;

SELECT  * FROM top5testes;