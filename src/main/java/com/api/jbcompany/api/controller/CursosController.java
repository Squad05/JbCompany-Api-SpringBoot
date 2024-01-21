package com.api.jbcompany.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.jbcompany.api.dto.CursosDTOExibicao;
import com.api.jbcompany.api.model.Cursos;
import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.service.CursosService;
import com.api.jbcompany.api.service.UsuariosService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/cursos")

public class CursosController {

    @Autowired
    private CursosService cursosService;

    @Autowired
    private UsuariosService usuariosService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Cursos>> listarCursos() {
        List<Cursos> cursos = cursosService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Cursos> cadastrarCurso(@RequestBody Cursos curso) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuarios usuario = usuariosService.encontrarUsuarioPorEmail(auth.getName());

        curso.setEmpresas(usuario);

        Cursos novoCurso = cursosService.cadastrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Cursos> pegarCursoPorId(@PathVariable Long id) {
        try {
            Cursos curso = cursosService.pegarCursoPorId(id);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Cursos> atualizarCurso(@PathVariable Long id, @RequestBody Cursos curso) {
        try {
            Cursos cursoAtualizado = cursosService.atualizarCurso(id, curso);
            return ResponseEntity.ok(cursoAtualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        try {
            cursosService.deletarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<CursosDTOExibicao>> listarCursosPorEmpresa(Long empresas) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            Usuarios usuario = usuariosService.encontrarUsuarioPorEmail(auth.getName());

            List<Cursos> cursosPorEmpresa = cursosService.listarCursosPorEmpresa(usuario.getId());

            List<CursosDTOExibicao> listaCursos = cursosPorEmpresa.stream()
                    .map(curso -> new CursosDTOExibicao(curso.getId(), curso.getMateria(), curso.getDescricao(),
                            curso.getDuracao()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(listaCursos);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
