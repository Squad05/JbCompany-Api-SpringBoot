package com.api.jbcompany.api.controller;

import java.util.List;

import com.api.jbcompany.api.dto.AulaDTO;
import com.api.jbcompany.api.model.Aulas;
import com.api.jbcompany.api.model.Cursos;
import com.api.jbcompany.api.service.AulasService;
import com.api.jbcompany.api.service.CursosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aulas")
public class AulasController {

    @Autowired
    private AulasService aulasService;

    @Autowired
    private CursosService cursosService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Aulas>> listarAulas() {
        List<Aulas> aulas = aulasService.listarAulas();
        return ResponseEntity.ok(aulas);
    }

    @CrossOrigin
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Aulas>> listarAulasPorCursoId(@PathVariable Long cursoId) {
        List<Aulas> aulas = aulasService.listarAulasPorCursoId(cursoId);
        return ResponseEntity.ok(aulas);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Aulas> cadastrarAula(@RequestBody AulaDTO aula) {
        Cursos buscarCurso = cursosService.pegarCursoPorId(aula.curso());

        Aulas novaAula = new Aulas();
        novaAula.setCurso(buscarCurso);
        novaAula.setDescricao(aula.descricao());
        novaAula.setLink(aula.link());
        novaAula.setTitulo(aula.titulo());

        Aulas aulaAdicionada = aulasService.cadastrarAula(novaAula);

        return ResponseEntity.status(HttpStatus.CREATED).body(aulaAdicionada);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Aulas> pegarAulaPorId(@PathVariable Long id) {
        try {
            Aulas aula = aulasService.pegarAulaPorId(id);
            return ResponseEntity.ok(aula);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Aulas> atualizarAula(@PathVariable Long id, @RequestBody Aulas aula) {
        try {
            Aulas aulaAtualizada = aulasService.atualizarAula(id, aula);
            return ResponseEntity.ok(aulaAtualizada);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAula(@PathVariable Long id) {
        try {
            aulasService.deletarAula(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
