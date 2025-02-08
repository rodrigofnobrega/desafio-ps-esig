package com.br.ps.esig.ps_esig.dto.task;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private PriorityEnum priority;
    private LocalDate date;
}
