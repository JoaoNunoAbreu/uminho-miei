/* (a) */
SELECT MEDICO.nome FROM Clinica.MEDICO WHERE idade(data_inicio_servico) > 10;

/* (b) */
SELECT m.nome as Nome, e.designacao as Especialidade from MEDICO m, ESPECIALIDADE e
WHERE m.especialidade = e.id_especialidade;

/* (c) */
SELECT p.nome,p.morada FROM Clinica.PACIENTE p
INNER JOIN CODIGO_POSTAL cp
ON p.codigo_postal = cp.codigo_postal
WHERE cp.localidade = 'Braga';

/* (d) */
SELECT m.nome as Nome from MEDICO m, ESPECIALIDADE e
WHERE m.especialidade = e.id_especialidade 
AND e.designacao = 'Oftalmologia';

/* (e) */
SELECT m.nome as Nome, idade(m.data_nascimento) as Idade from MEDICO m, ESPECIALIDADE e
WHERE m.especialidade = e.id_especialidade 
AND e.designacao = 'Clinica Geral' 
AND idade(m.data_nascimento) > 40;

/* (f) */
SELECT DISTINCT m.nome as Nome FROM CONSULTA c, MEDICO m
WHERE m.id_medico = c.id_medico
AND c.id_medico IN(
	SELECT m.id_medico from MEDICO m, ESPECIALIDADE e
		WHERE m.especialidade = e.id_especialidade
		AND e.designacao = 'Oftalmologia'
)
AND c.id_paciente IN(
	SELECT p.id_paciente FROM PACIENTE p, CODIGO_POSTAL cp
		WHERE p.codigo_postal = cp.codigo_postal
        AND cp.localidade = 'Braga'
);

/* (g) */
SELECT DISTINCT m.nome as Nome, idade(data_inicio_servico) as Anos_Servico FROM CONSULTA c, MEDICO m, PACIENTE p
WHERE m.id_medico = c.id_medico
AND c.id_paciente = p.id_paciente
AND idade(m.data_nascimento) > 50
AND hour(c.data_hora) > 12
AND idade(p.data_nascimento) < 20;

/* (h) */
SELECT DISTINCT p.nome as Nome, idade(p.data_nascimento) as Idade FROM PACIENTE p, CONSULTA c, MEDICO m, ESPECIALIDADE e 
WHERE p.id_paciente = c.id_paciente
AND idade(p.data_nascimento) > 10
AND e.id_especialidade = m.especialidade
AND m.id_medico = c.id_medico
AND c.id_paciente NOT IN(
	SELECT p.id_paciente FROM MEDICO m,CONSULTA c,PACIENTE p, ESPECIALIDADE e 
    WHERE c.id_paciente = p.id_paciente 
    AND m.id_medico = c.id_medico 
    AND e.id_especialidade = m.especialidade 
    AND e.designacao = 'Oftalmologia'
);

/* (i) */

SELECT DISTINCT e.designacao as Especialidade FROM CONSULTA c, ESPECIALIDADE e, MEDICO m
WHERE month(c.data_hora) = 1
AND year(c.data_hora) = 2016
AND m.id_medico = c.id_medico
AND m.especialidade = e.id_especialidade;

/* (j) */
SELECT m.nome as Nome FROM MEDICO m
WHERE idade(m.data_nascimento) > 30
OR idade(m.data_inicio_servico) < 5;

/* (k) */
SELECT DISTINCT m.nome as Nome, idade(m.data_nascimento) as Idade FROM MEDICO m, ESPECIALIDADE e, CONSULTA c
WHERE m.especialidade = e.id_especialidade
AND e.designacao = 'Clinica Geral'
AND m.id_medico = c.id_medico
AND m.id_medico NOT IN(
	SELECT m.id_medico FROM MEDICO m, CONSULTA c
	WHERE m.id_medico = c.id_medico
	AND month(c.data_hora) = 1
	AND year(c.data_hora) = 2016
);

/* (l) */
SELECT nome, idade(data_nascimento) FROM PACIENTE p
WHERE id_paciente IN(
	SELECT id_paciente FROM CONSULTA 
	WHERE id_medico IN(
		SELECT id_medico FROM MEDICO
	)
	GROUP BY id_paciente HAVING COUNT(*) = (
		SELECT COUNT(*) FROM MEDICO
	)
);

/* (m) *//*
SELECT DISTINCT e.designacao FROM CONSULTA c
INNER JOIN MEDICO m
ON m.id_medico = c.id_medico
INNER JOIN ESPECIALIDADE e
ON e.id_especialidade = m.especialidade
WHERE month(c.data_hora) = 1
OR month(c.data_hora) = 3
AND year(c.data_hora) = 2016;

/* (n) */


/* (o) 4 gajos*//*
SELECT DISTINCT p.id_paciente, p.nome FROM PACIENTE p, CONSULTA c
WHERE p.id_paciente = c.id_paciente
AND c.id_paciente NOT IN(
	SELECT p.id_paciente FROM PACIENTE p, CONSULTA c, MEDICO m, ESPECIALIDADE e
    WHERE p.id_paciente = c.id_paciente
    AND m.especialidade = e.id_especialidade
    AND m.id_medico = c.id_medico
    AND c.id_medico NOT IN(
		
    )
)
