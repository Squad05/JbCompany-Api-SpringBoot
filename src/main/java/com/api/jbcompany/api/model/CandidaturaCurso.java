package com.api.jbcompany.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class CandidaturaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidataEmail;

    private String candidataNome;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Cursos cursos;

    public CandidaturaCurso() {

    }

    public CandidaturaCurso(Long id, String candidataEmail, String candidataNome, Cursos cursos) {
        this.id = id;
        this.candidataEmail = candidataEmail;
        this.candidataNome = candidataNome;
        this.cursos = cursos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidataEmail() {
        return candidataEmail;
    }

    public void setCandidataEmail(String candidataEmail) {
        this.candidataEmail = candidataEmail;
    }

    public String getCandidataNome() {
        return candidataNome;
    }

    public void setCandidataNome(String candidataNome) {
        this.candidataNome = candidataNome;
    }

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

}
