package com.api.jbcompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.model.Vagas;

public interface VagasRepository extends JpaRepository<Vagas, Long> {

    List<Vagas> findByEmpresas(Usuarios empresas);
}
