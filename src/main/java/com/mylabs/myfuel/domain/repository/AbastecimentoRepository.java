package com.mylabs.myfuel.domain.repository;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}
