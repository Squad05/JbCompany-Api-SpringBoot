package com.api.jbcompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.jbcompany.api.model.CandidaturaCurso;

@Repository
public interface CandidaturaCursoRepository extends JpaRepository<CandidaturaCurso, Long> {
    List<CandidaturaCurso> findByCursosId(Long cursoId);

    int countByCandidataEmail(String email);
}
