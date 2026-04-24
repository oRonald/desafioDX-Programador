package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.*;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;
    private final ApiService apiService;

    public TimeController(TimeService timeService, ApiService apiService) {
        this.timeService = timeService;
        this.apiService = apiService;
    }

    @PostMapping
    public ResponseEntity<TimeResponse> criarTime(@RequestBody @Valid TimeRequest request){
        TimeResponse response = timeService.criarTime(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/por-data")
    public ResponseEntity<TimeDaDataResponse> timeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        Time time = apiService.timeDaData(data, timeService.retornaTodosOsTime());
        return ResponseEntity.ok(new TimeDaDataResponse(time));
    }

    @GetMapping("/integrantes-recorrentes")
    public ResponseEntity<IntegranteRecorrentResponse> integrantesMaisRecorrentes(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){

        List<String> integrantes = apiService.integrantesDoTimeMaisRecorrente(dataInicial, dataFinal, timeService.retornaTodosOsTime());
        return ResponseEntity.ok(new IntegranteRecorrentResponse(integrantes));
    }
}
