-- Table: public.requisicao_cotacao

-- DROP TABLE public.requisicao_cotacao;

CREATE TABLE public.requisicao_cotacao
(
    requisicao_id uuid NOT NULL,
    cotacoes_id uuid NOT NULL,
    CONSTRAINT fk_requisicao FOREIGN KEY (requisicao_id)
        REFERENCES public.requisicao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL,
    CONSTRAINT fk_cotacao FOREIGN KEY (cotacoes_id)
        REFERENCES public.cotacao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
)

TABLESPACE pg_default;

ALTER TABLE public.requisicao_cotacao OWNER to postgres;
