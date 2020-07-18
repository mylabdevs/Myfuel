package com.mylabs.myfuel.domain.repository;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.repository.projection.TotalDespesasMesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {

    Page<Abastecimento> findAbastecimentosByVeiculoId(@Param("id") Long id, Pageable pageable);

    Page<Abastecimento> findAbastecimentosByVeiculoUsuarioId(@Param("id") Long id, Pageable pageable);

    List<Abastecimento> findAbastecimentosByVeiculoId(@Param("id") Long id);

    @Query("select v.id as idVeiculo, sum(a.valor) as valor " +
            "from Abastecimento a join a.veiculo v " +
            "where v.usuario.id = :idUser and month(a.data) = :mes " +
            "group by v.id")
    List<TotalDespesasMesDTO> findSumMesByUser(@Param("idUser") Long idUser, @Param("mes") int mes);
}
