package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.dto.IntegranteResponse;
import br.com.duxusdesafio.dto.TimeRequest;
import br.com.duxusdesafio.dto.TimeResponse;
import br.com.duxusdesafio.model.Time;

import java.util.stream.Collectors;

public class TimeMapper {

    public Time toEntity(TimeRequest request){
        Time time = new Time();
        time.setNomeDoClube(request.getNomeDoClube());
        time.setData(request.getData());

        return time;
    }

    public TimeResponse toResponse(Time time){
        return new TimeResponse(
                time.getId(),
                time.getNomeDoClube(),
                time.getData(),
                // Busca todos os integrantes da composição e cria um IntegranteResponse
                time.getComposicaoTime().stream()
                        .map(composicaoTime -> new IntegranteResponse(
                                composicaoTime.getIntegrante().getId(),
                                composicaoTime.getIntegrante().getNome(),
                                composicaoTime.getIntegrante().getFuncao()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
