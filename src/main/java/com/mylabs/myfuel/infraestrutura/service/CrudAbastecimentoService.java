package com.mylabs.myfuel.infraestrutura.service;

import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.entity.User;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.exception.NegocioException;
import com.mylabs.myfuel.domain.repository.AbastecimentoRepository;
import com.mylabs.myfuel.domain.repository.VeiculoRepository;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrudAbastecimentoService implements AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;

    private final VeiculoRepository veiculoRepository;

    @Override
    public Abastecimento save(Abastecimento abastecimento) {

        Veiculo veiculo = veiculoRepository.findById(abastecimento.getVeiculo().getId())
                .orElseThrow(() -> new NegocioException("Veiculo não encontrado"));

        abastecimento.setVeiculo(veiculo);

        return abastecimentoRepository.save(abastecimento);
    }
}
