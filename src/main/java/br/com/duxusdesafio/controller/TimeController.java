package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.TimeRequest;
import br.com.duxusdesafio.dto.TimeResponse;
import br.com.duxusdesafio.service.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<?> criarTime(@RequestBody @Valid TimeRequest request){
        TimeResponse response = timeService.criarTime(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
