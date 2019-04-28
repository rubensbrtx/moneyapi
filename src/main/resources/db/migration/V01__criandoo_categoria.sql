CREATE TABLE categoria
(
  codigo             serial,
  nome         character varying(50) NOT NULL
);

ALTER TABLE categoria
  ADD CONSTRAINT categoria_pkey PRIMARY KEY (codigo);