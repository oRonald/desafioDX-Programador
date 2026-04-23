package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.IntegranteRequest;
import br.com.duxusdesafio.dto.IntegranteResponse;
import br.com.duxusdesafio.mapper.IntegranteMapper;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IntegranteService {

    private final IntegranteRepository integranteRepository;
    private final IntegranteMapper integranteMapper = new IntegranteMapper();

    public IntegranteService(IntegranteRepository integranteRepository) {
        this.integranteRepository = integranteRepository;
    }

    @Transactional
    public IntegranteResponse criarIntegrante(IntegranteRequest request){
        if(integranteRepository.existsByNomeAndFuncao(request.getNome(), request.getFuncao())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Integrante já cadastrado");
        }

        Integrante integrante = integranteMapper.toEntity(request);
        return integranteMapper.toResponse(integranteRepository.save(integrante));
    }

}
