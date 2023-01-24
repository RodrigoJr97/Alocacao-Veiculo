package com.alugacarro.alugacarro.service.implementacao;

import com.alugacarro.alugacarro.domain.entity.Usuario;
import com.alugacarro.alugacarro.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails usuarioDetail = loadUserByUsername(usuario.getNomeUsuario());
        boolean senhaConfirmada = encoder.matches(usuario.getSenha(), usuarioDetail.getPassword());

        if (senhaConfirmada) {
            return usuarioDetail;
        }

        throw new RuntimeException("Usuario ou Senha não correspondem");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

}
