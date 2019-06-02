CREATE TABLE categoria
(
  codigo             serial,
  nome         character varying(50) NOT NULL
);

ALTER TABLE categoria
  ADD CONSTRAINT categoria_pkey PRIMARY KEY (codigo);

CREATE TABLE pessoa
(
  codigo             serial,
  nome         character varying(50) NOT NULL,
  logradouro         character varying(50),
  numero         character varying(50),
  complemento         character varying(50),
  bairro         character varying(50),
  cep         character varying(50),
  cidade         character varying(50),
  estado         character varying(50),
  ativo       boolean  not null

);

ALTER TABLE pessoa
  ADD CONSTRAINT pessoa_pkey PRIMARY KEY (codigo);