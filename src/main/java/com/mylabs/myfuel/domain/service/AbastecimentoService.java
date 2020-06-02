package com.mylabs.myfuel.domain.service;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.repository.projection.TotalDespesasMesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbastecimentoService {

    Abastecimento save(Abastecimento abastecimento);

    Optional<Abastecimento> findById(long abastecimentoId);

    void delete(Abastecimento abastecimento);

    Page<Abastecimento> findAbastecimentosByVeiculoId(Long id, Pageable pageable);

    Page<Abastecimento> findAbastecimentosByVeiculoUserId(Long id, Pageable pageable);

    List<TotalDespesasMesDTO> findSumMesByUser(Long idUser);
}
