-- Table: public.requisicao

-- DROP TABLE public.requisicao;

CREATE TABLE public.requisicao
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    requsisicao timestamp without time zone,
    CONSTRAINT requisicao_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.requisicao OWNER to postgres;
