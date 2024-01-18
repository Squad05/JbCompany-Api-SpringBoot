package com.api.jbcompany.api.controller;

import com.api.jbcompany.api.dto.VagasDTOExibicao;
import com.api.jbcompany.api.dto.VagasDTOExibicaoComEmpresa;
import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.model.Vagas;
import com.api.jbcompany.api.service.AuthorizationService;
import com.api.jbcompany.api.service.UsuariosService;
import com.api.jbcompany.api.service.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vagas")
public class VagasController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private VagasService vagasService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<VagasDTOExibicaoComEmpresa>> listarVagas() {
        List<Vagas> vagas = vagasService.listarVagas();

        List<VagasDTOExibicaoComEmpresa> listaVagasExibicao = vagas.stream()
                .map(vaga -> new VagasDTOExibicaoComEmpresa(vaga.getId(), vaga.getEmpresas().getNome(),
                        vaga.getDescricao(), vaga.getLocalizacao(), vaga.getFuncao(), vaga.getSalario()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaVagasExibicao);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<VagasDTOExibicao> cadastrarVagas(@RequestBody Vagas vaga) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuarios usuario = usuariosService.encontrarUsuarioPorEmail(auth.getName());

        vaga.setEmpresas(usuario);

        Vagas vagaCadastrada = vagasService.cadastrarVagas(vaga);

        VagasDTOExibicao exibirVaga = new VagasDTOExibicao(vagaCadastrada.getId(), vagaCadastrada.getDescricao(),
                vagaCadastrada.getLocalizacao(), vagaCadastrada.getFuncao(), vagaCadastrada.getSalario());

        return ResponseEntity.ok(exibirVaga);

    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<VagasDTOExibicaoComEmpresa> pegarVagasPorId(@PathVariable Long id) {
        try {
            Vagas vaga = vagasService.pegarVagasPorId(id);
            VagasDTOExibicaoComEmpresa vagaExibir = new VagasDTOExibicaoComEmpresa(vaga.getId(),
                    vaga.getEmpresas().getNome(),
                    vaga.getDescricao(),
                    vaga.getLocalizacao(), vaga.getFuncao(), vaga.getSalario());

            return ResponseEntity.ok(vagaExibir);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<VagasDTOExibicao> atualizarVagas(@PathVariable Long id, @RequestBody Vagas vaga) {
        try {
            Vagas vagaAtualizada = vagasService.atualizarVagas(id, vaga);

            VagasDTOExibicao vagaExibicao = new VagasDTOExibicao(vagaAtualizada.getId(), vagaAtualizada.getDescricao(),
                    vagaAtualizada.getLocalizacao(), vagaAtualizada.getFuncao(), vagaAtualizada.getSalario());

            return ResponseEntity.ok(vagaExibicao);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVagas(@PathVariable Long id) {
        try {
            vagasService.deletarVagas(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<VagasDTOExibicao>> listarVagasPorEmpresa(Long empresas) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            Usuarios usuario = usuariosService.encontrarUsuarioPorEmail(auth.getName());

            List<Vagas> vagasPorEmpresa = vagasService.listarVagasPorEmpresa(usuario.getId());

            List<VagasDTOExibicao> listaVagas = vagasPorEmpresa.stream()
                    .map(vaga -> new VagasDTOExibicao(vaga.getId(), vaga.getDescricao(), vaga.getLocalizacao(),
                            vaga.getFuncao(),
                            vaga.getSalario()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(listaVagas);

        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
