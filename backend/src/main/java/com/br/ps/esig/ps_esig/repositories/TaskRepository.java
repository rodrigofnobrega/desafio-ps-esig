package com.br.ps.esig.ps_esig.repositories;

import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT t FROM TaskEntity t " +
            "WHERE (:id IS NULL OR t.id = :id) " +
            "AND (:term IS NULL OR " +
            "     LOWER(t.title) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%')) OR " +
            "     LOWER(t.description) LIKE LOWER(CONCAT('%', CAST(:term AS string), '%'))) " +
            "AND (:userId IS NULL OR t.user.id = :userId) " +
            "AND (:situation IS NULL OR t.taskSituation = :situation)")
    List<TaskEntity> findTasksByFilters(
            @Param("id") Long id,
            @Param("term") String term,
            @Param("userId") Long responsible,
            @Param("situation") TaskSituationEnum situation
    );
}

