package com.br.ps.esig.ps_esig.repositories;

import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findTaskByUserId(Long userId);

    List<TaskEntity> findTaskByTaskSituation(TaskSituationEnum situation);
}
