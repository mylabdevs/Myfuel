alter table veiculo
    add column placa varchar(10) not null unique;
alter table veiculo
    add column cor varchar(10) default '#FFF';