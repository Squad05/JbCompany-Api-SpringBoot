package com.api.jbcompany.api.controller;

import com.api.jbcompany.api.model.Candidaturas;
import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.model.Vagas;
import com.api.jbcompany.api.service.CandidaturasService;
import com.api.jbcompany.api.service.EmailService;
import com.api.jbcompany.api.service.VagasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturasController {

    @Autowired
    private CandidaturasService candidaturasService;

    @Autowired
    private VagasService vagasService;

    @Autowired
    private EmailService emailService;

    @CrossOrigin
    @GetMapping("/listar/{vagaId}")
    public ResponseEntity<List<Candidaturas>> listarCandidaturasPorVagaId(@PathVariable Long vagaId) {
        List<Candidaturas> candidaturas = candidaturasService.listarCandidaturasPorVagaId(vagaId);
        return ResponseEntity.ok(candidaturas);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Candidaturas> cadastrarCandidatura(@RequestBody Candidaturas candidatura) {
        Candidaturas novaCandidatura = candidaturasService.cadastrarCandidatura(candidatura);

        if (candidatura.getVagas() != null) {
            Map<String, Object> propriedades = new HashMap<>();
            propriedades.put("nome", candidatura.getCandidataNome());
            Vagas vagaAssociada = vagasService.pegarVagasPorId(candidatura.getVagas().getId());

            propriedades.put("funcaoCandidatura", vagaAssociada.getFuncao());

            emailService.enviarEmailCandididatura(candidatura.getCandidataEmail(),
                    "Sua candidatura foi recebida com sucesso!",
                    propriedades);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novaCandidatura);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Candidaturas> pegarCandidaturaPorId(@PathVariable Long id) {
        try {
            Candidaturas candidatura = candidaturasService.pegarCandidaturaPorId(id);
            return ResponseEntity.ok(candidatura);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCandidatura(@PathVariable Long id) {
        try {
            candidaturasService.deletarCandidatura(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/contar/{email}")
    public ResponseEntity<Integer> contarCandidaturasPorEmail(@PathVariable String email) {
        int totalCandidaturas = candidaturasService.contarCandidaturasPorEmail(email);
        return ResponseEntity.ok(totalCandidaturas);
    }
}
