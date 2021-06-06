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


