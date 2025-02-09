package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.dto.user.UserLoginDTO;
import com.br.ps.esig.ps_esig.infra.jwt.JwtToken;
import com.br.ps.esig.ps_esig.infra.jwt.JwtUserDetailsService;
import com.br.ps.esig.ps_esig.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService detailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    private ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserLoginDTO login, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login: {}", login.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getEmail(), login.getPassword());

        authenticationManager.authenticate(authenticationToken);

        JwtToken token = detailsService.getTokenAuthenticated(login.getEmail());
        log.info("Token gerado com sucesso para o usuário: {} - Token JWT: {}", login.getEmail(), token);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
