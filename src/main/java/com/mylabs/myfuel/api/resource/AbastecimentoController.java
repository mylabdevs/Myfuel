package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoInput;
import com.mylabs.myfuel.domain.dto.abastecimento.AbastecimentoModel;
import com.mylabs.myfuel.domain.dto.mapper.AbastecimentoMapper;
import com.mylabs.myfuel.domain.entity.Abastecimento;
import com.mylabs.myfuel.domain.service.AbastecimentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


}
