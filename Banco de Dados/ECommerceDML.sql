SET search_path TO techmarket;

INSERT INTO techmarket.cliente (nome_completo, senha, telefone, data_cadastro) VALUES
('Fulano Silva', 'senha123', '(11) 98765-4321', '2023-01-15 10:30:00-03'),
('Teste Oliveira', 'senha456', '(21) 91234-5678', '2023-02-20 14:00:00-03'),
('Vinio Pereira', 'senha789', '(31) 99999-8888', '2023-03-05 09:15:00-03'),
('Samurai Costa', 'senhaABC', '(41) 98888-7777', '2023-04-10 18:45:00-03');

INSERT INTO techmarket.produto (nome_produto, descricao, preco, estoque_disponivel, imagem_url) VALUES
('Notebook Gamer Pro', 'Notebook de alta performance para jogos e trabalho pesado.', 7500.00, 15, 'http://example.com/images/notebook.jpg'),
('Smartphone X Plus', 'Smartphone com câmera de 108MP e tela AMOLED.', 3200.50, 50, 'http://example.com/images/smartphone.jpg'),
('Monitor UltraWide 34"', 'Monitor com tela curva para uma experiência imersiva.', 2800.75, 30, 'http://example.com/images/monitor.jpg'),
('Teclado Mecânico RGB', 'Teclado com switches blue e iluminação RGB customizável.', 350.00, 100, 'http://example.com/images/teclado.jpg'),
('Mouse Gamer Sem Fio', 'Mouse ergonômico com 16000 DPI e bateria de longa duração.', 450.99, 80, 'http://example.com/images/mouse.jpg');

INSERT INTO techmarket.pedido (data_pedido, valor_total, status, cliente_id) VALUES
('2023-05-01 11:00:00-03', 7850.00, 'Entregue', 1);

INSERT INTO techmarket.pedido (data_pedido, valor_total, status, cliente_id) VALUES
('2023-05-03 16:20:00-03', 3651.49, 'Enviado', 2);

INSERT INTO techmarket.pedido (data_pedido, valor_total, status, cliente_id) VALUES
('2023-05-04 08:50:00-03', 2800.75, 'Processando', 1);

INSERT INTO techmarket.item_do_pedido (pedido_id, produto_id, quantidade) VALUES
(1, 1, 1), -- 1 Notebook Gamer Pro
(1, 4, 1); -- 1 Teclado Mecânico RGB

INSERT INTO techmarket.item_do_pedido (pedido_id, produto_id, quantidade) VALUES
(2, 2, 1), -- 1 Smartphone X Plus
(2, 5, 1); -- 1 Mouse Gamer Sem Fio

INSERT INTO techmarket.item_do_pedido (pedido_id, produto_id, quantidade) VALUES
(3, 3, 1); -- 1 Monitor UltraWide 34"


INSERT INTO techmarket.pagamento (pedido_id, forma_pagamento, status, data_pagamento) VALUES
(1, 'Cartão de Crédito', 'Aprovado', '2023-05-01 11:05:00-03');

INSERT INTO techmarket.pagamento (pedido_id, forma_pagamento, status, data_pagamento) VALUES
(2, 'PIX', 'Aprovado', '2023-05-03 16:22:00-03');

INSERT INTO techmarket.pagamento (pedido_id, forma_pagamento, status, data_pagamento) VALUES
(3, 'Boleto Bancário', 'Pendente', '2023-05-04 08:55:00-03');
