CREATE TABLE abastecimento
(
    id                SERIAL      NOT NULL,
    veiculo_id        BIGINT      NOT NULL,
    posto             varchar(80) NOT NULL,
    data              DATE        NOT NULL,
    valor             DECIMAL(10, 2),
    preco_combustivel DECIMAL(10, 2),
    km_atual          DECIMAL(10, 2),

    PRIMARY KEY (id)

);

ALTER TABLE abastecimento
    ADD CONSTRAINT fk_abastecimento_veiculo
        FOREIGN KEY (veiculo_id) REFERENCES veiculo (id);