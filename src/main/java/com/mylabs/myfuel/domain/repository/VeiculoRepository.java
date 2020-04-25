package com.mylabs.myfuel.domain.repository;

import com.mylabs.myfuel.domain.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
