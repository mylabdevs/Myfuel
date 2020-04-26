CREATE TABLE veiculo
(
    id         SERIAL      NOT NULL,
    user_id    BIGINT      NOT NULL,
    modelo     VARCHAR(60) NOT NULL,
    marca      VARCHAR(60) NOT NULL,
    km         NUMERIC(10, 2),
    cap_tangue NUMERIC(10, 2),
    ano        NUMERIC(4, 0),

    PRIMARY KEY (id)
);

ALTER TABLE veiculo
    ADD CONSTRAINT fk_veiculo_users
        FOREIGN KEY (user_id) REFERENCES users (id);