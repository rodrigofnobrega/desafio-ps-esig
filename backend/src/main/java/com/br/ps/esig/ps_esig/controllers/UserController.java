package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.dto.user.UserLoginDTO;
import com.br.ps.esig.ps_esig.infra.RestErrorMessage;
import com.br.ps.esig.ps_esig.infra.jwt.JwtToken;
import com.br.ps.esig.ps_esig.infra.jwt.JwtUserDetailsService;
import com.br.ps.esig.ps_esig.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Usuários", description = "Contém todas as operações relativas a autenticação e login de usuário")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService detailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Criar um usuário",
            description = "Criará um novo usuário no sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Realizar login",
            description = "Irá logar e retornará um token para usar nas rotas bloqueadas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtToken.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciais Inválidas",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class)))
            })
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
