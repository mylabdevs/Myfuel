package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.AbastecimentoRepository;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import com.mylabs.myfuel.domain.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrudAbastecimentoService implements AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;

    private final VeiculoService veiculoService;

    @Override
    public Abastecimento save(Abastecimento abastecimento) {

        Veiculo veiculo = veiculoService.findById(abastecimento.getVeiculo().getId())
                .orElseThrow(() -> new NegocioException("Veiculo não encontrado"));

        abastecimento.setVeiculo(veiculo);

        return abastecimentoRepository.save(abastecimento);
    }

    @Override
    public Optional<Abastecimento> findById(long abastecimentoId) {
        return abastecimentoRepository.findById(abastecimentoId);
    }

    @Override
    public void delete(Abastecimento abastecimento) {
        if (abastecimento == null || abastecimento.getId() == null) {
            throw new IllegalArgumentException("Abastecimento não pode ser null");
        }
        abastecimentoRepository.delete(abastecimento);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Abastecimento> findAbastecimentosByVeiculoId(Long id, Pageable pageable) {
        return abastecimentoRepository.findAbastecimentosByVeiculoId(id, pageable);
    }

    @Override
    public Page<Abastecimento> findAbastecimentosByVeiculoUserId(Long id, Pageable pageable) {
        return abastecimentoRepository.findAbastecimentosByVeiculoUserId(id, pageable);
    }


}
