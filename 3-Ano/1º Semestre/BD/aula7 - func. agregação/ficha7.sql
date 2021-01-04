/* (a) */

SELECT AVG(idade(m.data_nascimento)) as Idade FROM MEDICO m
WHERE idade(m.data_inicio_servico) > 15;

/* (b) */

SELECT e.designacao as Designacao, AVG(idade(m.data_inicio_servico)) as Media FROM MEDICO m, Especialidade e
WHERE m.especialidade = e.id_especialidade
GROUP BY e.designacao
ORDER BY e.designacao; /* ordem alfabética */

/* (c) */

SELECT m.nome as Medico, COUNT(c.id_medico) as NumeroConsultas FROM MEDICO m
LEFT JOIN CONSULTA c
ON m.id_medico = c.id_medico
GROUP BY m.id_medico
ORDER BY NumeroConsultas desc; /* ordem decrescente de número de consultas */

/* (d) */

SELECT cp.localidade as Localidade, AVG(idade(p.data_nascimento)) as Idade FROM PACIENTE p, CODIGO_POSTAL cp
WHERE p.codigo_postal = cp.codigo_postal
GROUP BY cp.localidade;

/* (e) */

SELECT m.nome as Nome, ifnull(SUM(c.preco),0) as Facturado FROM MEDICO m
LEFT JOIN CONSULTA c
ON m.id_medico = c.id_medico
AND year(c.data_hora) = 2016
GROUP BY m.id_medico
ORDER BY Facturado desc, Nome desc; /* caso de empate */

/* (f) */

SELECT e.designacao as Especialidade, COUNT(m.id_medico) as NumeroMedicos FROM ESPECIALIDADE e
LEFT JOIN MEDICO m
ON m.especialidade = e.id_especialidade
GROUP BY e.id_especialidade;

/* (g) */

SELECT designacao, MIN(c.preco), MAX(c.preco), avg(c.preco)
FROM (SELECT e.id_especialidade, designacao, COUNT(m.id_medico) as Total FROM ESPECIALIDADE e
LEFT JOIN MEDICO m
ON m.especialidade = e.id_especialidade
GROUP BY e.id_especialidade) as partial_table, MEDICO m, CONSULTA c
WHERE m.id_medico = c.id_medico
AND m.especialidade = partial_table.id_especialidade
AND partial_table.total <= 2
GROUP BY partial_table.id_especialidade;

/* (h) */

SELECT m.nome,sum(preco) as faturado FROM CONSULTA c, MEDICO m
WHERE c.id_medico = m.id_medico
AND year(c.data_hora) = 2016
GROUP BY m.id_medico
HAVING sum(preco) > (SELECT avg(faturado2016.Faturado) FROM
	(SELECT m.nome, sum(preco) as Faturado FROM CONSULTA c, MEDICO m
	WHERE c.id_medico = m.id_medico
	AND year(c.data_hora) = 2016
	GROUP BY m.id_medico) as faturado2016);
    
/* (i) */

SELECT e.designacao as Designacao, SUM(c.preco) as Faturado FROM MEDICO m, CONSULTA c, ESPECIALIDADE e
WHERE m.id_medico = c.id_medico
AND m.especialidade = e.id_especialidade
AND year(c.data_hora) = 2016
GROUP BY e.id_especialidade
ORDER BY Faturado desc
LIMIT 5; /* aparece as top 5 */ 

/* (j) */

SELECT m.nome as Nome, COUNT(c.id_medico) as NumeroConsultas FROM MEDICO m
LEFT JOIN CONSULTA c
ON m.id_medico = c.id_medico
AND year(c.data_hora) = 2016
GROUP BY m.id_medico
ORDER BY NumeroConsultas desc, Nome
LIMIT 3;