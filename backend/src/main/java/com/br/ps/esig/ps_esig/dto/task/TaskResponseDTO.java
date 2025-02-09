package com.br.ps.esig.ps_esig.dto.task;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskSituationEnum taskSituation;
    private PriorityEnum priority;
    private LocalDate date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
