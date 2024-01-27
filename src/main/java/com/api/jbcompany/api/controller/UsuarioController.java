package com.api.jbcompany.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jbcompany.api.dto.LoginResponseDTO;
import com.api.jbcompany.api.dto.RegistroUsuarioDTO;
import com.api.jbcompany.api.dto.UsuarioAtualizadoDTO;
import com.api.jbcompany.api.dto.UsuarioDTO;
import com.api.jbcompany.api.dto.UsuarioExibicaoDTO;
import com.api.jbcompany.api.model.Usuarios;
import com.api.jbcompany.api.repository.UsuariosRepository;
import com.api.jbcompany.api.service.TokenService;
import com.api.jbcompany.api.service.UsuariosService;

@RestController
@RequestMapping("auth")
public class UsuarioController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosService usuariosService;

    @CrossOrigin
    @PostMapping("/logar")
    public ResponseEntity<?> logar(@RequestBody UsuarioDTO data) {
        var usuario = new UsernamePasswordAuthenticationToken(data.email(),
                data.senha());
        try {
            var auth = this.authenticationManager.authenticate(usuario);
            var token = tokenService.gerarToken((Usuarios) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }

    }

    @CrossOrigin
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuarios> cadastrarUsuario(@RequestBody RegistroUsuarioDTO data) {
        Usuarios usuarioExistente = usuariosRepository.findByEmail(data.email());

        if (usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());
        Usuarios novoUsuario = new Usuarios(data.nome(), data.email(), senhaCriptografada, data.cargo());
        this.usuariosRepository.save(novoUsuario);
        return ResponseEntity.ok().body(novoUsuario);
    }

    @CrossOrigin
    @GetMapping("/detalhes")
    public ResponseEntity<UsuarioExibicaoDTO> exibirDetalhesUsuario() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuarios usuarioLogado = usuariosService.encontrarUsuarioPorEmail(auth.getName());

        UsuarioExibicaoDTO detalhesUsuario = new UsuarioExibicaoDTO(usuarioLogado.getId(), usuarioLogado.getNome(),
                usuarioLogado.getEmail(), usuarioLogado.getTelefone(), usuarioLogado.getArea_de_atuacao(),
                usuarioLogado.getDescricao());

        return ResponseEntity.ok().body(detalhesUsuario);

    }

    @CrossOrigin
    @PutMapping("/editar")
    public ResponseEntity<UsuarioAtualizadoDTO> editarUsuario(
            @RequestBody UsuarioAtualizadoDTO data) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Usuarios usuarioExistente = usuariosService.encontrarUsuarioPorEmail(auth.getName());

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();

        }

        Usuarios usuario = usuarioExistente;

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setArea_de_atuacao(data.area_de_atuacao());
        usuario.setDescricao(data.descricao());

        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());

        usuario.setSenha(senhaCriptografada);
        usuario.setTelefone(data.telefone());

        this.usuariosRepository.save(usuario);

        return ResponseEntity.ok().build();

    }
}
