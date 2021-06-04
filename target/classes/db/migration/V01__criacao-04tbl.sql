CREATE TABLE cozinha (
	id serial NOT NULL,
	nome varchar(70) NOT NULL,
	CONSTRAINT cozinha_pk PRIMARY KEY (id)
);

CREATE TABLE grupo_usuario (
	id serial NOT NULL,
	nome varchar(70) NOT NULL
);

CREATE TABLE permissao (
	id serial NOT NULL,
	descricao varchar(255) NULL,
	CONSTRAINT permissao_pk PRIMARY KEY (id)
);



CREATE TABLE restaurante (
	id serial NOT NULL,
	nome varchar(70) NOT NULL,
	frete numeric(12,2) NULL,
	ativo bool NOT NULL DEFAULT true,
	aberto bool NULL,
	data_cadastro date NULL,
	data_atualizacao date NULL,
	cozinha_id INT8 NOT NULL, 
	CONSTRAINT restaurante_pk PRIMARY KEY (id),
	CONSTRAINT restaurante_x_cozinha_fk FOREIGN KEY (cozinha_id) REFERENCES cozinha(id)
);