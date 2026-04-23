package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeResponse {

    private long id;

    private String nomeDoClube;

    private LocalDate data;

    private List<IntegranteResponse> integrantes;

    public TimeResponse() {
    }

    public TimeResponse(long id, String nomeDoClube, LocalDate data, List<IntegranteResponse> integrantes) {
        this.id = id;
        this.nomeDoClube = nomeDoClube;
        this.data = data;
        this.integrantes = integrantes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDoClube() {
        return nomeDoClube;
    }

    public void setNomeDoClube(String nomeDoClube) {
        this.nomeDoClube = nomeDoClube;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<IntegranteResponse> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<IntegranteResponse> integrantes) {
        this.integrantes = integrantes;
    }
}
