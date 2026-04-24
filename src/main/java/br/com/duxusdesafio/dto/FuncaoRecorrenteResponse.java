package br.com.duxusdesafio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FuncaoRecorrenteResponse {

    @JsonProperty("Função")
    private String funcaoRecorrente;

    public FuncaoRecorrenteResponse() {
    }

    public FuncaoRecorrenteResponse(String funcaoRecorrente) {
        this.funcaoRecorrente = funcaoRecorrente;
    }

    public String getFuncaoRecorrente() {
        return funcaoRecorrente;
    }

    public void setFuncaoRecorrente(String funcaoRecorrente) {
        this.funcaoRecorrente = funcaoRecorrente;
    }
}
