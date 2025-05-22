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

-- ISBN
-- 978-3-16-148410-0
-- 978-0-306-40615-7
-- 978-1-56619-909-4
-- 978-0-545-01022-1
-- 978-1-4028-9462-6
-- 978-0-7432-7356-5
-- 978-0-141-03435-8
-- 978-0-679-60139-5
-- 978-0-394-82337-9
-- 978-0-452-28423-4