package br.com.duxusdesafio.service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
public class ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        if(data == null || todosOsTimes == null){
            return null;
        }

        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if(todosOsTimes == null){
            return null;
        }

        return todosOsTimes.stream()
                // Filtra os times pelo período e se as datas não forem null
                .filter(time -> dataInicial == null || !time.getData().isBefore(dataInicial))
                .filter(time -> dataFinal == null || !time.getData().isAfter(dataFinal))

                // Pega as composições dos times filtrados
                .flatMap(time -> time.getComposicaoTime().stream())

                // Pega os integrantes destas composições
                .map(ComposicaoTime::getIntegrante)

                // Conta quantas vezes os integrantes apareceram
                .collect(Collectors.groupingBy(integrante -> integrante, Collectors.counting()))
                .entrySet().stream()

                // Retorna o integrante mais frequente
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais recorrente dentro do período.
     * OBS: Time é o clube + composição em determinada data
     */
    public List<String> integrantesDoTimeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if(todosOsTimes == null || todosOsTimes.isEmpty()){
            return new ArrayList<>();
        }

        return todosOsTimes.stream()
                // Filtra os times pelo período e se as datas forem null
                .filter(time -> dataInicial == null || !time.getData().isBefore(dataInicial))
                .filter(time -> dataFinal == null || !time.getData().isAfter(dataFinal))

                // Conta quantas vezes os times aparecem
                .collect(Collectors.groupingBy(time -> time, Collectors.counting()))
                .entrySet().stream()

                // Pega o time mais recorrente
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)

                // Pega a composição desse time e retorna uma lista com os nomes dos integrantes
                .map(time -> time.getComposicaoTime()
                        .stream()
                        .map(composicaoTime -> composicaoTime.getIntegrante().getNome())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    /**
     * Vai retornar a função mais recorrente nos times dentro do período
     */
    public String funcaoMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        if(todosOsTimes == null || todosOsTimes.isEmpty()){
            return null;
        }

        return todosOsTimes.stream()
                // Filtra os times pelo período e se as datas forem null
                .filter(time -> dataInicial == null || !time.getData().isBefore(dataInicial))
                .filter(time -> dataFinal == null || !time.getData().isAfter(dataFinal))

                // Pega todas as funções dos integrantes dos times filtrados
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicaoTime -> composicaoTime.getIntegrante().getFuncao())

                // Conta a frequencia que as funções aparecem
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()))
                .entrySet().stream()

                // Retorna a função mais recorrente
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Vai retornar o nome do Clube mais comum dentro do período
     */
    public String clubeMaisRecorrente(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        if(todosOsTimes == null || todosOsTimes.isEmpty()){
            return null;
        }

        return todosOsTimes.stream()
                .filter(time -> dataInicial == null || !time.getData().isBefore(dataInicial))
                .filter(time -> dataFinal == null || !time.getData().isAfter(dataFinal))

                // Agrupa pelos nomes dos clubes
                .map(Time::getNomeDoClube)
                .collect(Collectors.groupingBy(clube -> clube, Collectors.counting()))
                .entrySet().stream()

                // Retorna o clube mais recorrente
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    /**
     * Vai retornar o número (quantidade) de aparições de cada Clube participante no período
     */
    public Map<String, Long> contagemDeClubesNoPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> dataInicial == null || !time.getData().isBefore(dataInicial))
                .filter(time -> dataFinal == null || !time.getData().isAfter(dataFinal))
                .map(Time::getNomeDoClube)
                .collect(Collectors.groupingBy(clube -> clube, Collectors.counting()));
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                // Filtro para os times nas datas podendo ser null ou  exatamente dentro do período
                .filter(time -> dataInicial == null || time.getData().isAfter(dataInicial))
                .filter(time -> dataFinal == null || time.getData().isBefore(dataFinal))

                // Pego todas as funções dos jogadores
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicaoTime -> composicaoTime.getIntegrante().getFuncao())

                // Retorno um Map com a função e a quantidade de aparições
                .collect(Collectors.groupingBy(funcao -> funcao, Collectors.counting()));
    }

}
