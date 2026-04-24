package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.model.Time;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TimeDaDataResponse {

    private LocalDate data;

    private String clube;

    private List<String> integrantes;

    public TimeDaDataResponse() {
    }

    public TimeDaDataResponse(Time time) {
        this.data = time.getData();
        this.clube = time.getNomeDoClube();
        this.integrantes = time.getComposicaoTime().stream()
                .map(integrantes -> integrantes.getIntegrante().getNome())
                .collect(Collectors.toList());
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getClube() {
        return clube;
    }

    public void setNomeDoClube(String clube) {
        this.clube = clube;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
}
