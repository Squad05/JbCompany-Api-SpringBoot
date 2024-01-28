package com.api.jbcompany.api.service;

import java.util.List;

import com.api.jbcompany.api.model.CandidaturaCurso;

public interface CandidaturaCursoService {

    List<CandidaturaCurso> listarCandidaturaPorCursoId(Long cursoId);

    CandidaturaCurso cadastrarCandidatura(CandidaturaCurso candidaturaCurso);

    int contarCandidaturasCursoPorEmail(String email);
}
