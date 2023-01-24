package com.alugacarro.alugacarro.jwt;

import com.alugacarro.alugacarro.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-de-assinatura}")
    private String chaveDeAssinatura;

    public String gerarToken(Usuario usuario) {
        long expiracaoString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getNomeUsuario())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveDeAssinatura)
                .compact();

    }

    private Claims getClaims(String token) throws ExpiredJwtException{
        return Jwts
                .parser()
                .setSigningKey(chaveDeAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = getClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String getLoginUsuario(String token) throws ExpiredJwtException{
        return (String) getClaims(token).getSubject();
    }

//    public static void main(String[] args) {
//        ConfigurableApplicationContext contexto = SpringApplication.run(AlugacarroApplication.class);
//        JwtService service = contexto.getBean(JwtService.class);
//        Usuario usuario = Usuario.builder().nomeUsuario("adm").build();
//        String token = service.gerarToken(usuario);
//        System.out.println(token);
//
//        boolean tokenValido = service.tokenValido(token);
//        System.out.println("Token valido? " + tokenValido);
//
//        System.out.println(service.getLoginUsuario(token));
//
//    }

}
