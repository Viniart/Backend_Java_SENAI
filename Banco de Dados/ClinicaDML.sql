-- DML (Data Manipulation Language)
-- INSERT (Cadastrar)
-- UPDATE (Atualizar)
-- DELETE (Apaga)

-- OPCIONAL
-- SET search_path TO clinica;
-- clinica.paciente -> paciente

-- INSERT INTO <SCHEMA>.<TABELA>(<COLUNAS>) VALUES
INSERT INTO clinica.medico(nome, especialidade, crm) VALUES
('Vinicio', 'Ortopedia', '123456'),
('Ariel', 'Cardiologia', '654321')

-- DQL (Data Query Language) - Linguagem de Consulta
-- de Dados
-- SELECT
-- SELECT <QUAIS_COLUNAS> FROM <SCHEMA>.<TABELA>
SELECT * FROM clinica.medico;

INSERT INTO clinica.paciente(nome, idade, data_nascimento)
VALUES
('Fulano', 23, '2002-07-20')

SELECT * FROM clinica.paciente;

INSERT INTO clinica.clinica(nome, descricao, endereco)
VALUES
('Clinica São Caetano', 'A Clinica do ABC', 'Rua Niterói, 180')

SELECT * FROM clinica.clinica;

INSERT INTO clinica.consulta(data, valor, id_medico,
id_clinica, id_paciente)
VALUES
('2025-08-28 09:30:00-03', 350.75, 1, 1, 2)

SELECT * FROM clinica.consulta;

-- UPDATE
UPDATE clinica.consulta
SET valor = 200.50
WHERE id_consulta = 1;

UPDATE clinica.consulta
SET valor = 530
WHERE valor > 500 AND valor < 1000;
--				  OR

-- DELETE
DELETE FROM clinica.medico
WHERE id_medico = 1;



