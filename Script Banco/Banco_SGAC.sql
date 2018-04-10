CREATE DATABASE "Banco_SGAC";

CREATE TABLE public.evento
(
  idevento integer NOT NULL DEFAULT nextval('evento_idevento_seq'::regclass),
  nomeevento character varying(100) NOT NULL,
  dataevento date NOT NULL,
  idusuario integer NOT NULL,
  CONSTRAINT evento_idusuario_fkey FOREIGN KEY (idusuario)
      REFERENCES public.usuario (idusuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.usuario
(
  idusuario integer NOT NULL DEFAULT nextval('usuario_idusuario_seq'::regclass),
  email character varying(50) NOT NULL,
  senha character varying(30) NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (idusuario)
);