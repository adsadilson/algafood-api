ALTER TABLE restaurante ADD end_cep varchar(13) NULL;
ALTER TABLE restaurante ADD end_logradouro varchar(100) NULL;
ALTER TABLE restaurante ADD end_numero varchar(10) NULL;
ALTER TABLE restaurante ADD end_bairro varchar(100) NULL;
ALTER TABLE restaurante ADD end_complemento varchar(100) NULL;
ALTER TABLE restaurante ADD end_cidade_id int8 NULL;
ALTER TABLE restaurante ADD CONSTRAINT restaurante_x_cidade_fk FOREIGN KEY (id) REFERENCES cidade(id);

ALTER TABLE grupo_usuario ADD CONSTRAINT grupo_usuario_pk PRIMARY KEY (id);



CREATE TABLE forma_pagto (
	id serial NOT NULL,
	descricao varchar(100) NOT NULL,
	CONSTRAINT forma_pagto_pk PRIMARY KEY (id)
);

INSERT INTO forma_pagto (id, descricao) values (1, 'Cartão de crédito');
INSERT INTO forma_pagto (id, descricao) values (2, 'Cartão de débito');
INSERT INTO forma_pagto (id, descricao) values (3, 'Dinheiro');

CREATE TABLE restaurante_forma_pagto (
	restaurante_id int8 NOT NULL,
	forma_pagto_id int8 NOT NULL,
	CONSTRAINT restaurante_x_res_forma_pagto_fk FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
	CONSTRAINT forma_pagto_x_res_forma_pagto_fk FOREIGN KEY (forma_pagto_id) REFERENCES forma_pagto(id)
);


INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao, end_cidade_id, end_cep, end_logradouro, end_numero, end_bairro) values (1, 'Thai Gourmet', 10, 1,  now(),  now(), 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4,  now(),  now());
INSERT INTO restaurante (id, nome, frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4,  now(),  now());


INSERT INTO restaurante_forma_pagto (restaurante_id, forma_pagto_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


CREATE TABLE produto (
	id serial NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(255) NULL,
	preco numeric(12,2) NULL,
	ativo boolean NOT NULL DEFAULT true,
	restaurante_id int8 NULL,
	CONSTRAINT produto_pk PRIMARY KEY (id),
	CONSTRAINT produto_x_restaurante_fk FOREIGN KEY (id) REFERENCES public.restaurante(id)
);


CREATE TABLE grupo_usuario_permissao (
	grupo_usuario_id int8 NOT NULL,
	permissao_id int8 NOT NULL,
	CONSTRAINT grupo_usuario_x_grupo_usuario_permissao_fk FOREIGN KEY (grupo_usuario_id) REFERENCES grupo_usuario(id),
	CONSTRAINT permissao_x_grupo_usuario_permissao_fk FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);


