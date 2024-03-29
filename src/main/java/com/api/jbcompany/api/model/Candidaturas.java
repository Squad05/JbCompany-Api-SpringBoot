package com.api.jbcompany.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Candidaturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidataEmail;

    private String candidataNome;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vagas vagas;

    public Candidaturas(Long id, String candidataEmail, String candidataNome, Vagas vagas) {
        this.id = id;
        this.candidataEmail = candidataEmail;
        this.candidataNome = candidataNome;
        this.vagas = vagas;
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

    public Vagas getVagas() {
        return vagas;
    }

    public void setVagas(Vagas vagas) {
        this.vagas = vagas;
    }

    public Candidaturas() {

    }

}
