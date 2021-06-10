create sequence cidade_id_seq start 1 increment 1
create sequence cozinha_id_seq start 1 increment 1
create sequence estado_id_seq start 1 increment 1
create sequence forma_pagto_seq start 1 increment 50
create sequence grupo_usuario_id_seq start 1 increment 1
create sequence permissao_id_seq start 1 increment 1
create sequence produto_seq start 1 increment 50
create sequence restaurante_id_seq start 1 increment 1
create table cidade (id int8 not null, nome varchar(255) not null, estado_id int8 not null, primary key (id))
create table cozinha (id int8 not null, nome varchar(65) not null, primary key (id))
create table estado (id int8 not null, capital varchar(255), nome varchar(255), regiao varchar(255), sigla varchar(2), primary key (id))
create table forma_pagto (id int8 not null, descricao varchar(255) not null, primary key (id))
create table grupo_usuario (id int8 not null, nome varchar(255) not null, primary key (id))
create table grupo_usuario_permissao (grupo_usuario_id int8 not null, permissao_id int8 not null)
create table permissao (id int8 not null, descricao varchar(255) not null, primary key (id))
create table produto (id int8 not null, ativo boolean not null, descricao varchar(255), nome varchar(255) not null, preco numeric(19, 2), restaurante_id int8, primary key (id))
create table restaurante (id int8 not null, aberto boolean not null, ativo boolean not null, data_atualizacao date, data_cadastro date, end_bairro varchar(255), end_cep varchar(255), end_complemento varchar(255), end_logradouro varchar(255), end_numero varchar(255), frete numeric(12, 2), nome varchar(255) not null, cozinha_id int8 not null, end_cidade_id int8, primary key (id))
create table restaurante_forma_pagto (restaurante_id int8 not null, forma_pagto_id int8 not null)
alter table if exists cozinha add constraint UK_21inunwxqp3wdrnbji4sp1vli unique (nome)
alter table if exists grupo_usuario add constraint UK_1d2ov45a9e2vhc82r1qmj52fc unique (nome)
alter table if exists restaurante add constraint UK_jd0r4hoyk81uwt7jb88va5noa unique (nome)
alter table if exists cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado
alter table if exists grupo_usuario_permissao add constraint FK43wtrrjiot1t4o9i8c4ab3qjd foreign key (permissao_id) references permissao
alter table if exists grupo_usuario_permissao add constraint FK88b0q0f8nfma67e9ulr2ty0s0 foreign key (grupo_usuario_id) references grupo_usuario
alter table if exists produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante
alter table if exists restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha
alter table if exists restaurante add constraint FKck0d6af2o9winr9c8jk6tm3q5 foreign key (end_cidade_id) references cidade
alter table if exists restaurante_forma_pagto add constraint FKk73d7o13x1iwesrmsrju9t7hc foreign key (forma_pagto_id) references forma_pagto
alter table if exists restaurante_forma_pagto add constraint FKmis6b9habvcoot9by03uc7v28 foreign key (restaurante_id) references restaurante
