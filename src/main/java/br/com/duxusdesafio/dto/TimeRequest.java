package br.com.duxusdesafio.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class TimeRequest {

    @NotBlank
    private String nomeDoClube;

    @NotNull
    private LocalDate data;

    @NotNull
    @NotEmpty(message = "O time deve conter pelo menos 1 integrante")
    private List<Long> idIntegrantes;

    public TimeRequest() {
    }

    public TimeRequest(String nomeDoClube, LocalDate data, List<Long> idIntegrantes) {
        this.nomeDoClube = nomeDoClube;
        this.data = data;
        this.idIntegrantes = idIntegrantes;
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

    public List<Long> getIdIntegrante() {
        return idIntegrantes;
    }

    public void setIdIntegrante(List<Long> idIntegrantes) {
        this.idIntegrantes = idIntegrantes;
    }
}
