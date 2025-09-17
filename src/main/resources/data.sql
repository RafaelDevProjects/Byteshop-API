-- Inserção de dados iniciais para teste
INSERT INTO cliente (nome, email, documento) VALUES
('Ana Silva', 'ana@email.com', '123.456.789-00'),
('Carlos Souza', 'carlos@email.com', '987.654.321-00'),
('Maria Oliveira', 'maria@email.com', '111.222.333-44');

INSERT INTO produto (nome, preco, categoria, descricao, ativo) VALUES
('Notebook', 3500.00, 'Informática', 'Notebook básico', TRUE),
('Mouse', 120.00, 'Acessórios', 'Mouse USB', TRUE),
('Teclado', 200.00, 'Acessórios', 'Teclado mecânico', TRUE),
('Monitor', 800.00, 'Informática', 'Monitor 24 polegadas', TRUE);