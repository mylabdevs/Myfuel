package com.mylabs.myfuel.domain.dto.mapper;

import java.util.List;

public interface EntityMapper<I, M, E> {

    E toEntity(I input);

    M toModel(E entity);

    List<E> toEntits(List<I> inputList);

    List<M> toModels(List<E> entityList);
}
