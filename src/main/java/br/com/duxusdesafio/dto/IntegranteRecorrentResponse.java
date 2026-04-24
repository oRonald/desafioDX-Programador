package br.com.duxusdesafio.dto;

import java.util.List;

public class IntegranteRecorrentResponse {

    private List<String> integrantes;

    public IntegranteRecorrentResponse() {
    }

    public IntegranteRecorrentResponse(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
}
