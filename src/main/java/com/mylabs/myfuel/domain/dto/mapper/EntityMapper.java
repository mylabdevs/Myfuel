package com.mylabs.myfuel.domain.dto.mapper;

import java.util.List;

public interface EntityMapper<Ï, M, E> {

    E toEntity(Ï input);

    M toModel(E entity);

    List<E> toEntits(List<Ï> inputList);

    List<M> toModels(List<E> entityList);
}
