CREATE TABLE usuario
(
    id            serial       NOT NULL,
    name          varchar(80)  NOT NULL,
    password      varchar(120)  NOT NULL,
    email         varchar(100) NOT NULL,
    data_cadastro date         NOT NULL,
    role          varchar(40)  NOT NULL,
    primary key (id),
    CONSTRAINT UC_Email UNIQUE (email)
);
