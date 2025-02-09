package com.br.ps.esig.ps_esig.services;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void create(TaskCreateDTO taskCreateDTO) {
        UserEntity user = userService.findById(taskCreateDTO.getUserId());
        TaskEntity task = TaskMapper.toTask(taskCreateDTO);
        task.setUser(user);

        taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public TaskEntity findById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new RuntimeException("Tarefa não encontrada com id=" + taskId)
        );
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> findByUser(Long userId) {
        return taskRepository.findTaskByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }
}
