CREATE TABLE estado (
	id serial NOT NULL,
	nome varchar(80) NULL,
	sigla varchar(2) NULL,
	capital varchar(80) NULL,
	regiao varchar(80) NULL,
	CONSTRAINT estado_pk PRIMARY KEY (id)
);

CREATE TABLE cidade (
	id serial NOT NULL,
	nome varchar(80) NOT NULL,
	estado_id int8 NOT NULL,
	CONSTRAINT cidade_pk PRIMARY KEY (id),
	CONSTRAINT cidade_x_estado_fk FOREIGN KEY (id) REFERENCES estado(id)
);

CREATE TABLE cozinha (
	id serial NOT NULL,
	nome varchar(70) NOT NULL UNIQUE,
	CONSTRAINT cozinha_pk PRIMARY KEY (id)
);

CREATE TABLE forma_pagto (
	id serial NOT NULL,
	nome varchar(20) NOT NULL UNIQUE,
	descricao varchar(100) NOT NULL,
	CONSTRAINT forma_pagto_pk PRIMARY KEY (id)
);

CREATE TABLE grupo_usuario (
	id serial NOT NULL,
	nome varchar(70) NOT NULL UNIQUE,
	CONSTRAINT grupo_usuario_pk PRIMARY KEY (id)
);

CREATE TABLE permissao (
	id serial NOT NULL,
	nome varchar(150) NOT NULL,
	descricao varchar(150) NOT NULL,
	CONSTRAINT permissao_pk PRIMARY KEY (id)
);

CREATE TABLE grupo_usuario_permissao (
	grupo_usuario_id serial NOT NULL,
	permissao_id int8 NOT NULL,
	CONSTRAINT grupo_usuario_x_grupo_usuario_permissao_fk FOREIGN KEY (grupo_usuario_id) REFERENCES grupo_usuario(id),
	CONSTRAINT permissao_x_grupo_usuario_permissao_fk FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);

CREATE TABLE usuario (
	id serial NOT NULL,
	nome varchar(150) NOT NULL,
	email varchar(255) NOT NULL,
	senha varchar NOT NULL,
	data_cadastro date NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id)
);

CREATE TABLE usuario_grupo_usuario (
	grupo_usuario_id serial NOT NULL,
	usuario_id int8 NOT NULL,
	CONSTRAINT usuario_grupo_x_usuario_fk FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT usuario_grupo_x_usuario_fk_1 FOREIGN KEY (grupo_usuario_id) REFERENCES grupo_usuario(id)
);

CREATE TABLE restaurante (
	id serial NOT NULL,
	nome varchar(70) NOT NULL,
	frete numeric(12,2) NULL,
	ativo bool NOT NULL DEFAULT true,
	aberto bool NULL,
	data_cadastro date NULL,
	data_atualizacao date NULL,
	cozinha_id int8 NOT NULL,
	end_cep varchar(15) NULL,
	end_logradouro varchar(100) NULL,
	end_numero varchar(10) NULL,
	end_bairro varchar(100) NULL,
	end_complemento varchar(100) NULL,
	end_cidade_id int8 NULL,
	CONSTRAINT restaurante_pk PRIMARY KEY (id),
	CONSTRAINT restaurante_x_cidade_fk FOREIGN KEY (id) REFERENCES cidade(id),
	CONSTRAINT restaurante_x_cozinha_fk FOREIGN KEY (cozinha_id) REFERENCES cozinha(id)
);

CREATE TABLE restaurante_forma_pagto (
	restaurante_id int8 NOT NULL,
	forma_pagto_id int8 NOT NULL,
	CONSTRAINT restaurante_x_res_forma_pagto_fk FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT forma_pagto_x_res_forma_pagto_fk FOREIGN KEY (forma_pagto_id) REFERENCES forma_pagto(id)
);

CREATE TABLE restuarante_usuario_responsavel (
	restaurante_id int8 NOT NULL,
	usuario_id int8 NOT NULL,
	CONSTRAINT restuarante_usuario_responsavel_fk FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT restuarante_usuario_responsavel_fk_1 FOREIGN KEY (usuario_id) REFERENCES restaurante(id)
);

CREATE TABLE produto (
	id serial NOT NULL,
	nome varchar(100) NOT NULL UNIQUE,
	descricao varchar(150) NULL,
	preco numeric(12,2) NULL,
	ativo boolean NOT NULL DEFAULT true,
	restaurante_id int8 NULL,
	CONSTRAINT produto_pk PRIMARY KEY (id),
	CONSTRAINT produto_x_restaurante_fk FOREIGN KEY (restaurante_id) REFERENCES restaurante(id)
);

CREATE TABLE pedido (
	id serial NOT NULL,
	codigo varchar(36) NOT NULL UNIQUE,
	sub_total numeric(12,2) NULL,
	taxa_frete numeric(12,2) NULL,
	valor_total numeric(12,2) NULL,
	status varchar(30) NULL,
	data_criacao date NOT NULL,
	hora time(0) NOT NULL,
	data_confirmacao date NULL,
	data_cancelamento date NULL,
	data_entregue date NULL,
	forma_pagto_id int8 NOT NULL,
	restaurante_id int8 NOT NULL,
	cliente_id int8 NOT NULL,
	end_cep varchar(15) NULL,
	end_logradouro varchar(80) NULL,
	end_numero varchar(15) NULL,
	end_bairro varchar(80) NULL,
	end_complemento varchar(70) NULL,
	end_cidade_id int8,
	CONSTRAINT pedido_pk PRIMARY KEY (id),
	CONSTRAINT pedido_fk_forma_pagto FOREIGN KEY (forma_pagto_id) REFERENCES forma_pagto(id),
	CONSTRAINT pedido_fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT pedido_fk_usuario FOREIGN KEY (cliente_id) REFERENCES usuario(id)
);

CREATE TABLE item_pedido (
	id serial NOT NULL,
	preco_unitario numeric(12,2) NULL,
	preco_total numeric(12,2) NULL,
	quantidade int4 NULL,
	observacao varchar NULL,
	pedido_id int8 NOT NULL,
	produto_id int8 NOT NULL,
	CONSTRAINT item_pedido_pk PRIMARY KEY (id),
	CONSTRAINT item_pedido_fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
	CONSTRAINT item_pedido_fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);


INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Acre', 'AC', 'Rio Branco', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Alagoas', 'AL', 'Maceió', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Amapá', 'AP', 'Macapá', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Amazonas', 'AM', 'Manaus', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Bahia', 'BA', 'Salvador', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Ceará', 'CE', 'Fortaleza', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Distrito Federal', 'DF', 'Brasília', 'Centro-Oeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Espírito Santo', 'ES', 'Vitória', 'Sudeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Goiás', 'GO', 'Goiânia', 'Centro-Oeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Maranhão', 'MA', 'São Luís', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Mato Grosso', 'MT', 'Cuiabá', 'Centro-Oeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Mato Grosso do Sul', 'MS', 'Campo Grande', 'Centro-Oeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Minas Gerais', 'MG', 'Belo Horizonte', 'Sudeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Paraná', 'PR', 'Curitiba', 'Sul');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Paraíba', 'PB', 'João Pessoa', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Pará', 'PA', 'Belém', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Pernambuco', 'PE', 'Recife', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Piauí', 'PI', 'Teresina', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Rio Grande do Norte', 'RN', 'Natal', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Rio Grande do Sul', 'RS', 'Porto Alegre', 'Sul');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Rio de Janeiro', 'RJ', 'Rio de Janeiro', 'Sudeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Rondônia', 'RO', 'Porto Velho', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Roraima', 'RR', 'Boa Vista', 'Norte');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Santa Catarina', 'SC', 'Florianópolis', 'Sul');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Sergipe', 'SE', 'Aracaju', 'Nordeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('São Paulo', 'SP', 'São Paulo', 'Sudeste');
INSERT INTO estado (nome, sigla, capital, regiao) VALUES('Tocantins', 'TO', 'Palmas', 'Norte');

INSERT INTO cidade(nome, estado_id) VALUES('Planalto', 5);
INSERT INTO cidade(nome, estado_id) VALUES('Jequie', 5);
INSERT INTO cidade(nome, estado_id) VALUES('Vitoria da Conquista', 5);
INSERT INTO cidade(nome, estado_id) VALUES('São Paulo', 26);
INSERT INTO cidade(nome, estado_id) VALUES('Garulhos', 26);
INSERT INTO cidade(nome, estado_id) VALUES('Campinas', 26);

INSERT INTO forma_pagto (nome, descricao) values ('Cartão de crédito','Cartão de crédito');
INSERT INTO forma_pagto (nome, descricao) values ('Cartão de débito', 'Cartão de débito');
INSERT INTO forma_pagto (nome, descricao) values ('Dinheiro','A vista em dinheiro');
INSERT INTO forma_pagto (nome, descricao) values ('Cheque A vista','A vista');
INSERT INTO forma_pagto (nome, descricao) values ('Cheuqe Pre-datado','A prazo');
INSERT INTO forma_pagto (nome, descricao) values ('Nota a Receber','A prazo');

INSERT INTO cozinha(nome) VALUES('Brasileira');
INSERT INTO cozinha(nome) VALUES('Italiana');
INSERT INTO cozinha(nome) VALUES('Japonesa');
INSERT INTO cozinha(nome) VALUES('Bahiana');
INSERT INTO cozinha(nome) VALUES('Mineira');
INSERT INTO cozinha(nome) VALUES('Internacional');

INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Thai Gourmet', 10.00, true, true, '2021-08-26', '2021-08-26', 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', NULL, 1);
INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Thai Delivery', 9.50, true, true, '2021-08-26', '2021-08-26', 1, '45190-000', 'Rua Sete de Setembro', '713', 'Centro', NULL, 2);
INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Tuk Tuk Comida Indiana', 15.00, true, true, '2021-08-26', '2021-08-26', 2, '48500-001', 'AV. Jhon Kendy', '005', 'Piturba', NULL, 2);
INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Java Steakhouse', 12.00, true, true, '2021-08-26', '2021-08-26', 3, '47500-011', 'Rua Jardim da Saudade', 'SN', 'Centro', NULL, 1);
INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Lanchonete do Tio Sam', 11.00, true, true, '2021-08-26', '2021-08-26', 4, '47501-200', 'Rua José Pinheiro', '788', 'Alvorada', NULL, 1);
INSERT INTO restaurante
(nome, frete, ativo, aberto, data_cadastro, data_atualizacao, cozinha_id, end_cep, end_logradouro, end_numero, end_bairro, end_complemento, end_cidade_id)
VALUES('Bar da Maria', 6.00, true, true, '2021-08-26', '2021-08-26', 4, '46006-000', 'AV. Jetulio Vagas', '25', 'Centro', NULL, 1);


UPDATE restaurante SET aberto = true;

INSERT INTO grupo_usuario (nome) VALUES ('Administrador'), ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

INSERT INTO permissao (nome, descricao) VALUES ('Cadastar Usuário','Permitir cadastrar um novo usuário'),
('Editar Usuário', 'Permitir atualizar o cadastro de usuário'), ('Excluir Usuário','Permitir excluir um usuário'), 
('Cadastrar Permissão' ,'Permitir cadastar uma nova permissão');

INSERT INTO restaurante_forma_pagto (restaurante_id, forma_pagto_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3),
(4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

INSERT INTO grupo_usuario_permissao (grupo_usuario_id, permissao_id) VALUES (1,1), (1,2), (1,3), (1,4), (2,1), (2,2);

INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('APSSYSTEM', 'apssystem@apss.com.br', '123', '2021-01-01'),
('CLEITON', 'cleiton@apss.com.br', '456', '2021-01-05'), ('CAMILA', 'camila@apss.com.br', '6654', '2021-01-10'),
('FERNANDA', 'fernanda@apss.com.br', '456', '2021-01-30'), ('MARCELO', 'marcelo@apss.com.br', '879454', '2021-03-01');

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Almôndega caseira', 'Almôndega caseira', 35.40, true, 1);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Estrogonofe', 'Estrogonofe', 25, true, 1);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Costelinha de porco assada', 'Costelinha de porco assada', 45, true, 2);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Filé de atum assado no forno', 'Filé de atum assado no forno', 75.40, true, 2);

INSERT INTO produto (nome, descricao, preco, ativo, restaurante_id)
VALUES ('Salada de feijão fradinho simples', 'Salada de feijão fradinho simples', 30, true, 2);

INSERT INTO pedido (codigo, restaurante_id, cliente_id, forma_pagto_id, end_cidade_id, end_cep, end_logradouro,
end_numero, end_complemento, end_bairro, status, data_criacao, hora, sub_total, taxa_frete, valor_total)
VALUES ('85se878e' ,1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', '2021-08-24', '06:00', 95.80, 10, 85.80);

INSERT INTO pedido (codigo, restaurante_id, cliente_id, forma_pagto_id, end_cidade_id, end_cep, end_logradouro,
end_numero, end_complemento, end_bairro, status, data_criacao, hora, sub_total, taxa_frete, valor_total)
VALUES ('81se8785', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', '2021-08-24', '06:23',
150.8, 0, 150.8);

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
VALUES (1, 1, 2, 35.4, 70.8, null);

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
VALUES (1, 2, 1, 25, 25, 'Menos picante, por favor');

INSERT INTO item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
VALUES (2, 4, 2, 75.4, 150.8, 'Ao ponto');

INSERT INTO usuario_grupo_usuario (grupo_usuario_id, usuario_id) VALUES (1,1), (1,2), (1,3), (1,4), (1,5);





