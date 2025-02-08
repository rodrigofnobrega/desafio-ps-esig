package com.br.ps.esig.ps_esig.dto.task;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {
    @NotBlank(message = "O título não pode estar vazio")
    @Size(max = 50, message = "O título não pode ter mais de 50 caracteres")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia")
    @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres")
    private String description;

    @NotNull(message = "A prioridade não pode estar vazia")
    private PriorityEnum priority;

    @NotNull(message = "A data não pode estar vazia")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Positive(message = "Valores iguais ou menores que zero não são aceitos")
    private Long userId;
}
