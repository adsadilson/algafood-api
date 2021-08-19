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
	CONSTRAINT produto_x_restaurante_fk FOREIGN KEY (id) REFERENCES public.restaurante(id)
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

INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao, end_cidade_id, end_cep, end_logradouro, end_numero, end_bairro) values (1, 'Thai Gourmet', 10, 1,  now(),  now(), 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4,  now(),  now());

update restaurante set aberto = true;

insert into grupo_usuario (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');



INSERT INTO restaurante_forma_pagto (restaurante_id, forma_pagto_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);





