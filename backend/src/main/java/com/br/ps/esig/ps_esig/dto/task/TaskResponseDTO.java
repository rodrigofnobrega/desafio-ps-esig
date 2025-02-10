package com.br.ps.esig.ps_esig.dto.task;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.br.ps.esig.ps_esig.utils.RefactorDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private LocalDate date;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonProperty("createdAt")
    private String getCreateAtFormatted() {
        return RefactorDate.refactorLocalDateTime(getCreatedAt());
    }

    @JsonProperty("updatedAt")
    private String getUpdatedAtFormatted() {
        return RefactorDate.refactorLocalDateTime(getUpdatedAt());
    }

    @JsonProperty("date")
    private String getDateFormatted() {
        return RefactorDate.refactorLocalDate(getDate());
    }
}
