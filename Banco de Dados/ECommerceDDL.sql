SET search_path TO techmarket;

CREATE SCHEMA IF NOT EXISTS techmarket;

CREATE TABLE techmarket.cliente(
	cliente_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_completo TEXT NOT NULL,
	senha TEXT NOT NULL,
	telefone TEXT,
	data_cadastro TIMESTAMPTZ
);

CREATE TABLE techmarket.produto (
	produto_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nome_produto TEXT NOT NULL,
	descricao TEXT,
	preco DECIMAL(14,4) NOT NULL,
	estoque_disponivel INT NOT NULL,
	imagem_url TEXT
);

CREATE TABLE techmarket.pedido (
	pedido_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	data_pedido TIMESTAMPTZ NOT NULL,
	valor_total NUMERIC (18,4) NOT NULL,
	status TEXT NOT NULL,
	cliente_id INT NOT NULL REFERENCES techmarket.cliente(cliente_id)
);

CREATE TABLE techmarket.pagamento (
	pagamento_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	pedido_id INT NOT NULL REFERENCES techmarket.pedido(pedido_id),
	forma_pagamento TEXT NOT NULL,
	status TEXT NOT NULL,
	data_pagamento TIMESTAMPTZ NOT NULL
);

CREATE TABLE techmarket.item_do_pedido (
	item_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	pedido_id INT NOT NULL REFERENCES techmarket.pedido(pedido_id),
	produto_id INT NOT NULL REFERENCES techmarket.produto(produto_id),
	quantidade INT NOT NULL
);