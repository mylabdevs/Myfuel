package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.dto.mapper.AbastecimentoMapper;
import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.exception.EntidadeNaoEncontradaException;
import com.mylabs.myfuel.domain.repository.projection.TotalDespesasMesDTO;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Abastecimento Rotas", tags = {"abastecimentos"})
@CrossOrigin
@RestController
@RequestMapping("abastecimentos")
@RequiredArgsConstructor
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    private final AbastecimentoMapper abastecimentoMapper;

    @ApiOperation(value = "Salvar Abastecimento")
    @PostMapping
    public ResponseEntity<AbastecimentoModel> criar(@Valid @RequestBody AbastecimentoInput abastecimentoInput) {
        Abastecimento abastecimento = abastecimentoMapper.toEntity(abastecimentoInput);
        abastecimento = abastecimentoService.save(abastecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(abastecimentoMapper.toModel(abastecimento));
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

    @ApiOperation(value = "Total abastecimento no mês")
    @GetMapping("/user/mesAtual/{idUser}")
    public ResponseEntity<List<TotalDespesasMesDTO>> findTotalMesByUser(@PathVariable Long idUser) {

        List<TotalDespesasMesDTO> list = abastecimentoService.findSumMesByUser(idUser);

        return ResponseEntity.ok(list);

    }

}
