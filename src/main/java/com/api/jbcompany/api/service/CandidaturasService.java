package com.api.jbcompany.api.service;

import java.util.List;

import com.api.jbcompany.api.model.Candidaturas;

public interface CandidaturasService {
    List<Candidaturas> listarCandidaturasPorVagaId(Long vagaId);

    Candidaturas cadastrarCandidatura(Candidaturas Candidatura);

    void deletarCandidatura(Long id);

    Candidaturas pegarCandidaturaPorId(Long id);

    int contarCandidaturasPorEmail(String email);

}
