package com.api.jbcompany.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jbcompany.api.model.CandidaturaCurso;
import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.service.CandidaturaCursoService;
import com.api.jbcompany.api.service.UsuariosService;

@RestController
@RequestMapping("/candidaturas-cursos")
public class CandidaturaCursoController {

    @Autowired
    private CandidaturaCursoService candidaturaCursoService;

    @Autowired
    private UsuariosService usuariosService;

    @CrossOrigin
    @GetMapping("/{cursoId}")
    public ResponseEntity<List<CandidaturaCurso>> listarCandidaturasPorCursoId(@PathVariable Long cursoId) {
        List<CandidaturaCurso> candidaturasCursos = candidaturaCursoService.listarCandidaturaPorCursoId(cursoId);
        return ResponseEntity.ok(candidaturasCursos);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<CandidaturaCurso> cadastrarCandidaturaCurso(@RequestBody CandidaturaCurso candidaturaCurso) {
        CandidaturaCurso novaCandidaturaCurso = candidaturaCursoService.cadastrarCandidatura(candidaturaCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCandidaturaCurso);
    }

    @CrossOrigin
    @GetMapping("/contar/{email}")
    public ResponseEntity<Integer> contarCandidaturasPorEmail(@PathVariable String email) {
        int totalCandidaturas = candidaturaCursoService.contarCandidaturasCursoPorEmail(email);
        return ResponseEntity.ok(totalCandidaturas);
    }

    @CrossOrigin
    @GetMapping("/contarPorEmpresa")
    public ResponseEntity<?> contarCandidaturasCursosPorEmpresa() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Usuarios usuario = usuariosService.encontrarUsuarioPorEmail(auth.getName());

            if (usuario != null && usuario.getId() != null) {
                Long empresaId = usuario.getId();
                int totalCandidaturas = candidaturaCursoService.contarCandidaturasCursoPorEmpresa(empresaId);
                return ResponseEntity.ok().body(totalCandidaturas);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou empresaId ausente.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }
    }
}
