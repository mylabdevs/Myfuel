package com.mylabs.myfuel.api.resource;

import com.mylabs.myfuel.domain.dto.mapper.VeiculoMapper;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoInput;
import com.mylabs.myfuel.domain.dto.veiculo.VeiculoModel;
import com.mylabs.myfuel.domain.entity.Veiculo;
import com.mylabs.myfuel.domain.service.VeiculoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    private final VeiculoMapper veiculoMapper;

    @ApiOperation(value = "Salvar veiculo")
    @PostMapping
    public ResponseEntity<VeiculoModel> criar(@Valid @RequestBody VeiculoInput veiculoInput) {
        Veiculo veiculo = veiculoMapper.toEntity(veiculoInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoMapper.toModel(veiculoService.save(veiculo)));
    }

    @ApiOperation(value = "Buscar veiculo por ID")
    @GetMapping("{veiculoId}")
    public ResponseEntity<VeiculoModel> findById(@PathVariable Long veiculoId) {
        Optional<Veiculo> veiculo = veiculoService.findById(veiculoId);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculoMapper.toModel(veiculo.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Deletar veiculo")
    @DeleteMapping("/{veiculoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long veiculoId) {
        Veiculo veiculo = veiculoService.findById(veiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        veiculoService.delete(veiculo);
    }
}
