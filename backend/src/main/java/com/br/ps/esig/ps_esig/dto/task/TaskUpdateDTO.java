package com.br.ps.esig.ps_esig.dto.task;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDTO {
    @Size(max = 50, message = "O título não pode ter mais de 50 caracteres")
    private String title;

    @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres")
    private String description;

    private PriorityEnum priority;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Positive(message = "Valores iguais ou menores que zero não são aceitos")
    private Long userId;

    private TaskSituationEnum taskSituation;
}
