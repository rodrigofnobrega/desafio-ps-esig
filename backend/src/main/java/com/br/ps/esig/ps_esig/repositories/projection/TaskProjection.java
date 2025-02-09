package com.br.ps.esig.ps_esig.repositories.projection;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TaskProjection {
    Long getId();
    String getTitle();
    String getDescription();
    PriorityEnum getPriority();
    TaskSituationEnum getTaskSituation();
    UserEntity getUser();
    LocalDate getDate();
    Long getUserId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    interface UserEntity extends UserProjection {}


}
