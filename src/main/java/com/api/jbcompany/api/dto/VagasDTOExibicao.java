package com.api.jbcompany.api.dto;

public record VagasDTOExibicao(Long id, String descricao, String cep, String localizacao, String funcao, int salario,
        boolean status_vaga) {

}
