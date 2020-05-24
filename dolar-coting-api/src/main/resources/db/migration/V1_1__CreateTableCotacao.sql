-- Table: public.cotacao

-- DROP TABLE public.cotacao;

CREATE TABLE public.cotacao
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    cotacaocompra numeric(19,2),
    cotacaovenda numeric(19,2),
    datahoracotacao timestamp without time zone,
    referencia date NOT NULL,
    CONSTRAINT cotacao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.cotacao
    OWNER to postgres;
