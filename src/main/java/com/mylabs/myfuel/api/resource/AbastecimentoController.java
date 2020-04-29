package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.dto.mapper.AbastecimentoMapper;
import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Abastecimento Rotas", tags = {"abastecimento"})
@RestController
@RequestMapping("abastecimento")
@RequiredArgsConstructor
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    private final AbastecimentoMapper abastecimentoMapper;

    @ApiOperation(value = "Salvar Abastecimento")
    @PostMapping
    public ResponseEntity<AbastecimentoModel> criar(@Valid @RequestBody AbastecimentoInput abastecimentoInput) {
        Abastecimento abastecimento =abastecimentoMapper.toEntity(abastecimentoInput);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(abastecimentoMapper.toModel(abastecimentoService.save(abastecimento)));
    }


}
