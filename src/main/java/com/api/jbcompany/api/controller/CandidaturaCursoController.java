package com.api.jbcompany.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jbcompany.api.model.CandidaturaCurso;
import com.api.jbcompany.api.service.CandidaturaCursoService;

@RestController
@RequestMapping("/candidaturas-cursos")
public class CandidaturaCursoController {

    @Autowired
    private CandidaturaCursoService candidaturaCursoService;

    @GetMapping("/{cursoId}")
    public ResponseEntity<List<CandidaturaCurso>> listarCandidaturasPorCursoId(@PathVariable Long cursoId) {
        List<CandidaturaCurso> candidaturasCursos = candidaturaCursoService.listarCandidaturaPorCursoId(cursoId);
        return ResponseEntity.ok(candidaturasCursos);
    }

    @PostMapping
    public ResponseEntity<CandidaturaCurso> cadastrarCandidaturaCurso(@RequestBody CandidaturaCurso candidaturaCurso) {
        CandidaturaCurso novaCandidaturaCurso = candidaturaCursoService.cadastrarCandidatura(candidaturaCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCandidaturaCurso);
    }
}
