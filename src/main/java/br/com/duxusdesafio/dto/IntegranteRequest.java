package br.com.duxusdesafio.dto;

import javax.validation.constraints.NotBlank;

public class IntegranteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String funcao;

    public IntegranteRequest() {
    }

    public IntegranteRequest(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
