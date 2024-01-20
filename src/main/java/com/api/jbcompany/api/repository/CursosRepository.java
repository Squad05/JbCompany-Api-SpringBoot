package com.api.jbcompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.jbcompany.api.model.Cursos;
import com.api.jbcompany.api.model.Usuarios;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

    List<Cursos> findByEmpresas(Usuarios empresas);
}
