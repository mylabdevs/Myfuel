package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.dto.mapper.AbastecimentoMapper;
import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.exception.EntidadeNaoEncontradaException;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api(value = "Abastecimento Rotas", tags = {"abastecimento"})
@CrossOrigin
@RestController
@RequestMapping("abastecimentos")
@RequiredArgsConstructor
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    private final AbastecimentoMapper abastecimentoMapper;

    @ApiOperation(value = "Salvar Abastecimento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AbastecimentoModel criar(@Valid @RequestBody AbastecimentoInput abastecimentoInput) {
        Abastecimento abastecimento = abastecimentoMapper.toEntity(abastecimentoInput);
        abastecimento = abastecimentoService.save(abastecimento);
        return abastecimentoMapper.toModel(abastecimento);
    }

    @ApiOperation(value = "Obter detalhes abastecimento")
    @GetMapping("/{abastecimentoId}")
    public AbastecimentoModel get(@PathVariable Long abastecimentoId) {
        return abastecimentoService.findById(abastecimentoId)
                .map(abastecimento -> abastecimentoMapper.toModel(abastecimento))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Abastecimento não encontrado"));
    }


    @ApiOperation(value = "Obter detalhes abastecimento por veiculo")
    @GetMapping("/veiculo/{veiculoId}")
    public Page<AbastecimentoModel> getAbastecimentoByVeiculo(@PathVariable Long veiculoId, Pageable pageable) {

        Page<AbastecimentoModel> page = abastecimentoService.findAbastecimentosByVeiculoId(veiculoId, pageable)
                .map(abastecimentoMapper::toModel);

        if (page.getContent().isEmpty()) {
            throw new EntidadeNaoEncontradaException("Não foi encontrado nenhum abastecimento para o veiculo");
        }

        return page;
    }

    @ApiOperation(value = "Obter detalhes abastecimento por usuario")
    @GetMapping("/user/{userId}")
    public Page<AbastecimentoModel> getAbastecimentoByUser(@PathVariable Long userId, Pageable pageable) {

        Page<AbastecimentoModel> page = abastecimentoService.findAbastecimentosByVeiculoUserId(userId, pageable)
                .map(abastecimentoMapper::toModel);

        if (page.getContent().isEmpty()) {
            throw new EntidadeNaoEncontradaException("Não foi encontrado nenhum abastecimento para o veiculo");
        }

        return page;
    }

    @ApiOperation(value = "Deletar abastecimento")
    @DeleteMapping("/{abastecimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long abastecimentoId) {
        Abastecimento abastecimento = abastecimentoService.findById(abastecimentoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Não encontrado na base de dados"));
        abastecimentoService.delete(abastecimento);
    }

}
