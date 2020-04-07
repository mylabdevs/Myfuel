CREATE TABLE users (
    id serial,
    name varchar(50),
    password varchar,
    email varchar(100),
    role varchar(40),
    primary key (id),
    CONSTRAINT UC_Email UNIQUE (email)
);
