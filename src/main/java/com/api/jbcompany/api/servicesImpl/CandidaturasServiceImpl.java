package com.api.jbcompany.api.servicesImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.jbcompany.api.model.Candidaturas;
import com.api.jbcompany.api.repository.CandidaturasRepository;
import com.api.jbcompany.api.service.CandidaturasService;

@Service
public class CandidaturasServiceImpl implements CandidaturasService {

    @Autowired
    private CandidaturasRepository candidaturasRepository;

    @Override
    public Candidaturas cadastrarCandidatura(Candidaturas candidatura) {
        return candidaturasRepository.save(candidatura);
    }

    @Override
    public void deletarCandidatura(Long id) {
        candidaturasRepository.deleteById(id);
    }

    @Override
    public Candidaturas pegarCandidaturaPorId(Long id) {
        Optional<Candidaturas> candidatura = candidaturasRepository.findById(id);
        return candidatura.orElseThrow(() -> new NoSuchElementException("Candidatura não encontrada com o ID: " + id));
    }

    @Override
    public List<Candidaturas> listarCandidaturasPorVagaId(Long vagaId) {
        return candidaturasRepository.findByVagasId(vagaId);
    }

    @Override
    public int contarCandidaturasPorEmail(String email) {
        return candidaturasRepository.countByCandidataEmail(email);
    }

}
