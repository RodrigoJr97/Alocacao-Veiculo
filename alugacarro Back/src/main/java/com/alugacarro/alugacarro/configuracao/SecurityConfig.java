package com.alugacarro.alugacarro.configuracao;

import com.alugacarro.alugacarro.jwt.JwtAuthFilter;
import com.alugacarro.alugacarro.jwt.JwtService;
import com.alugacarro.alugacarro.service.implementacao.UsuarioDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UsuarioDetailServiceImpl usuarioDetailService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioDetailService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/aluga/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST, "/aluga/cliente/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/aluga/cliente/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/aluga/cliente/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/aluga/cliente/**").hasRole("ADMIN")
                .antMatchers("/aluga/endereco/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/aluga/carro/disponivel", "/aluga/carro/tipo").permitAll()
                .antMatchers("/aluga/carro/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/aluga/contrato/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/aluga/contrato/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
