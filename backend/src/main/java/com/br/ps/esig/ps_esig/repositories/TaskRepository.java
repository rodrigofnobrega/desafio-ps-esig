package com.br.ps.esig.ps_esig.repositories;

import com.br.ps.esig.ps_esig.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
