-- Seleciona todas as pessoas onde o campo pessoa está vazio
SELECT * FROM Pessoa WHERE pessoa = '';

-- Seleciona todas as pessoas ordenadas por email em ordem ascendente
SELECT * FROM pessoa ORDER BY email ASC;

-- Seleciona pessoas onde o email contém 'carlos' e o nome contém 'c' (case insensitive)
SELECT * FROM pessoa WHERE email LIKE '%carlos%' AND LOWER(nome) LIKE '%c%';

-- Seleciona pessoas onde o email contém 'carlos' ou 'jessica'
SELECT * FROM pessoa WHERE email LIKE '%carlos%' OR email like '%jessica%';

-- Seleciona pessoas onde o email não contém 'carlos'
SELECT * FROM pessoa where email NOT LIKE '%carlos%';