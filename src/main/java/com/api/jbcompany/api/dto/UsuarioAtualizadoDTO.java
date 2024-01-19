package com.api.jbcompany.api.dto;

public record UsuarioAtualizadoDTO(Long id, String nome, String email, String senha, String telefone,
        String area_de_atuacao,
        String descricao) {

}
