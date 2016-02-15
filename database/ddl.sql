--drop schema public cascade;
--create schema public;
--create database controledeentregas;

/*******************************************************************************
* tabela situacoes
*******************************************************************************/

create sequence situacoes_id_seq;

create table situacoes(
    id integer not null default nextval ('situacoes_id_seq'),
    ativo boolean not null,
    descricao varchar(40) not null,

    constraint pk_situacao primary key (id),
    constraint uk_situacoes_descricao unique(descricao));


/*******************************************************************************
* tabela produtos
*******************************************************************************/

create sequence produtos_id_seq;

create table produtos(
    id integer not null default nextval ('produtos_id_seq'),
    ativo boolean not null,
    codigo varchar(13) not null,
    descricao varchar(50) not null,
    valor numeric(8,2) not null,

    constraint pk_produtos primary key (id),
    constraint uk_produtos_codigo unique (codigo));


/*******************************************************************************
* tabela entregas
*******************************************************************************/

create sequence entregas_codigo_seq;

create table entregas(
    codigo integer not null default nextval ('entregas_codigo_seq'),
    idsituacao integer not null,
    datacriacao date not null,
    dataprevista date not null,
    dataentrega date,
    descricao varchar(100) not null,
    valor numeric(8,2) not null,

    constraint pk_entregas primary KEY (codigo),
    constraint fk_entregas FOREIGN key (idsituacao)
    	references situacoes (id));


/*******************************************************************************
* tabela produtosxentregas
*******************************************************************************/

create sequence produtosxentregas_id_seq;

create table produtosxentregas(
    id integer not null default nextval ('produtosxentregas_id_seq'),
    codigoentrega integer not null,
    idproduto integer not null,
    quantidade numeric(18,2) not null,

    constraint pk_produtosxentregas primary key (id),
    constraint fk_produtosxentregas_entregas foreign key (codigoentrega)
    	references entregas (codigo)
        on update cascade
        on delete cascade,
    constraint fk_produtosxentregas_produtos foreign key (idproduto)
    	references produtos (id));