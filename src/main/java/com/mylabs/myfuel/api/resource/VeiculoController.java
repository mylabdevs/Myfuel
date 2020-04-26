package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.service.VeiculoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class  VeiculoController {

    private final VeiculoService veiculoService;

    @ApiOperation(value = "Salvar veiculo")
    @PostMapping
    public ResponseEntity<VeiculoModel> criar(@Valid @RequestBody VeiculoInput veiculoInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.save(veiculoInput));
    }

//    private VeiculoModel toModel(Veiculo veiculo) {
//        return modelMapper.map(veiculo, VeiculoModel.class);
//    }

//    private Veiculo toEntity(VeiculoInput veiculoInput) {
//        return modelMapper.map(veiculoInput, Veiculo.class);
//    }
}
