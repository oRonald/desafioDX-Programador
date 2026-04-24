package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.TimeRequest;
import br.com.duxusdesafio.dto.TimeResponse;
import br.com.duxusdesafio.mapper.TimeMapper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;
    private TimeMapper mapper = new TimeMapper();

    public TimeService(TimeRepository timeRepository, IntegranteRepository integranteRepository) {
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
    }

    public TimeResponse criarTime(TimeRequest request){
        if(timeRepository.existsByNomeDoClubeAndData(request.getNomeDoClube(), request.getData())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O time " + request.getNomeDoClube() + " já foi definido para esta data");
        }

        Time time = mapper.toEntity(request);

        List<ComposicaoTime> composicao = request.getIdIntegrante().stream()
                // Busca integrantes a cada ID passado no request e cria um ComposicaoTime
                .map(id -> {
                    Integrante integrante = integranteRepository.findById(id)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Integrante não encontrado"));

                    ComposicaoTime composicaoTime = new ComposicaoTime();
                    composicaoTime.setIntegrante(integrante);
                    composicaoTime.setTime(time);

                    return composicaoTime;
                })
                .collect(Collectors.toList());

        time.setComposicaoTime(composicao);
        return mapper.toResponse(timeRepository.save(time));
    }

    public List<Time> retornaTodosOsTime(){
        List<Time> times = timeRepository.findAll();
        if(times.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe nenhum time para esta data");
        }

        return times;
    }
}
