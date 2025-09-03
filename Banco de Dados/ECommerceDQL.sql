-- DQL - Consulta
SELECT nome_completo, telefone
FROM techmarket.cliente;

SELECT nome, idade, cpf
FROM clinica.paciente;

SELECT * FROM clinica.medico;


SELECT * FROM clinica.consulta;

-- JOIN (Juntar)
-- Com o JOIN posso juntar tabelas
-- Utilizando a Chave Estrangeira

-- Listar, Data, Valor, Nome Medico
SELECT 
	cs.data,
	cs.valor,
	med.nome
FROM clinica.consulta AS cs
JOIN
	clinica.medico AS med
ON cs.id_medico =
med.id_medico
WHERE cs.valor > 100
-- WHERE - Filtro


SELECT pedido_id, nome_completo
FROM techmarket.pedido
JOIN techmarket.cliente
ON techmarket.pedido.cliente_id = 
techmarket.cliente.cliente_id


SELECT COUNT(id_medico)
FROM clinica.medico;





