package com.mylabs.myfuel.domain.repository;

import com.mylabs.myfuel.domain.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByUsuarioId(Long id);

    boolean existsByPlaca(String placa);
}
