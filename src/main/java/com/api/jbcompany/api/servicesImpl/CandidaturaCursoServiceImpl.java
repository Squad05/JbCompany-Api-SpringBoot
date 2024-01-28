package com.api.jbcompany.api.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jbcompany.api.model.CandidaturaCurso;
import com.api.jbcompany.api.repository.CandidaturaCursoRepository;
import com.api.jbcompany.api.service.CandidaturaCursoService;

@Service
public class CandidaturaCursoServiceImpl implements CandidaturaCursoService {

    @Autowired
    private CandidaturaCursoRepository candidaturaCursoRepository;

    @Override
    public List<CandidaturaCurso> listarCandidaturaPorCursoId(Long cursoId) {
        return candidaturaCursoRepository.findByCursosId(cursoId);
    }

    @Override
    public CandidaturaCurso cadastrarCandidatura(CandidaturaCurso candidaturaCurso) {
        return candidaturaCursoRepository.save(candidaturaCurso);
    }

    @Override
    public int contarCandidaturasCursoPorEmail(String email) {
        return candidaturaCursoRepository.countByCandidataEmail(email);
    }

    @Override
    public int contarCandidaturasCursoPorEmpresa(Long empresaId) {
        return candidaturaCursoRepository.countByCursosEmpresasId(empresaId);
    }

}
