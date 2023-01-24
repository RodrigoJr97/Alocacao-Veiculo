package com.alugacarro.alugacarro.controller;

import com.alugacarro.alugacarro.domain.entity.Usuario;
import com.alugacarro.alugacarro.domain.repository.UsuarioRepository;
import com.alugacarro.alugacarro.dto.CredenciaisDTO;
import com.alugacarro.alugacarro.dto.TokenDTO;
import com.alugacarro.alugacarro.jwt.JwtService;
import com.alugacarro.alugacarro.service.implementacao.UsuarioDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/aluga/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDetailServiceImpl usuarioDetailService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<?> salvaCliente(@RequestBody @Valid Usuario usuario) {
        String senhaCriptograda = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptograda);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO dto) {
        try{

            Usuario usuario =
                    Usuario.builder()
                            .id(usuarioRepository.findByNomeUsuario(dto.getNomeUsuario()).get().getId())
                            .nomeUsuario(dto.getNomeUsuario())
                            .senha(dto.getSenha())
                            .build();


            usuarioDetailService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getId(), usuario.getNomeUsuario(), token);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
