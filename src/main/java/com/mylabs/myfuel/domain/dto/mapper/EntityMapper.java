package com.mylabs.myfuel.domain.dto.mapper;

public interface EntityMapper <E, I, M> {

    E inputToEntity(I input);

    M entityToModel(E entity);
}
