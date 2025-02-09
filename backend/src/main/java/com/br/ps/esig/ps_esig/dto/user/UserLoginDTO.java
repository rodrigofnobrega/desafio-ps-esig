package com.br.ps.esig.ps_esig.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "Formato do email inválido")
    private String email;
    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, message = "A senha tem que ter no mínimo 6 caracteres")
    private String password;
}
