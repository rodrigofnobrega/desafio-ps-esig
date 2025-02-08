package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<Void> create(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
