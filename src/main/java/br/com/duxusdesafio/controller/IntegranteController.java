package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteRequest;
import br.com.duxusdesafio.dto.IntegranteResponse;
import br.com.duxusdesafio.service.IntegranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/integrantes")
public class IntegranteController {

    private final IntegranteService integranteService;

    public IntegranteController(IntegranteService integranteService) {
        this.integranteService = integranteService;
    }

    @PostMapping
    public ResponseEntity<IntegranteResponse> criarIntegrante(@RequestBody @Valid IntegranteRequest request){
        IntegranteResponse response = integranteService.criarIntegrante(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
