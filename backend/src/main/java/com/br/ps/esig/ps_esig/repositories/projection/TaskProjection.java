package com.br.ps.esig.ps_esig.repositories.projection;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.br.ps.esig.ps_esig.utils.RefactorDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TaskProjection {
    Long getId();
    String getTitle();
    String getDescription();
    PriorityEnum getPriority();
    TaskSituationEnum getTaskSituation();
    UserEntity getUser();
    @JsonIgnore
    LocalDate getDate();
    @JsonIgnore
    LocalDateTime getCreatedAt();
    @JsonIgnore
    LocalDateTime getUpdatedAt();

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

    interface UserEntity extends UserProjection {}
}
