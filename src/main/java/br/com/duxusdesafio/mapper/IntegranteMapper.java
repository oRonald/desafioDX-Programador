package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.dto.IntegranteRequest;
import br.com.duxusdesafio.dto.IntegranteResponse;
import br.com.duxusdesafio.model.Integrante;

import java.util.ArrayList;

public class IntegranteMapper {

    public Integrante toEntity(IntegranteRequest request){
        Integrante integrante = new Integrante();
        integrante.setNome(request.getNome());
        integrante.setFuncao(request.getFuncao());
        integrante.setComposicaoTime(new ArrayList<>());

        return integrante;
    }

    public IntegranteResponse toResponse(Integrante integrante){
        return new IntegranteResponse(
                integrante.getId(),
                integrante.getNome(),
                integrante.getFuncao()
        );
    }
}
