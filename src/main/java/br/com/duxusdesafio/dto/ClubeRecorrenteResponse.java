package br.com.duxusdesafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubeRecorrenteResponse {

    @JsonProperty("Clube")
    private String clube;

    public ClubeRecorrenteResponse() {
    }

    public ClubeRecorrenteResponse(String clube) {
        this.clube = clube;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }
}
