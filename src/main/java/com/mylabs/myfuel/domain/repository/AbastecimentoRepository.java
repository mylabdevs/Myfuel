package com.mylabs.myfuel.domain.repository;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    Page<Abastecimento> findAbastecimentosByVeiculoId(@Param("id") Long id, Pageable pageable);

    Page<Abastecimento> findAbastecimentosByVeiculoUserId(@Param("id") Long id, Pageable pageable);

    List<Abastecimento> findAbastecimentosByVeiculoId(@Param("id") Long id);
}
