package com.br.ps.esig.ps_esig.services;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskUpdateDTO;
import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.br.ps.esig.ps_esig.repositories.TaskRepository;
import jakarta.validation.Valid;
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
    public TaskEntity findByid(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> searchTasks(Long id, String term, Long userId, TaskSituationEnum situation) {
        return taskRepository.findTasksByFilters(id, term, userId, situation);
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO taskUpdateDTO) {
        TaskEntity task = this.findByid(id);

        if (taskUpdateDTO.getTitle() != null) {
            task.setTitle(taskUpdateDTO.getTitle());
        }
        if (taskUpdateDTO.getDescription() != null) {
            task.setDescription(taskUpdateDTO.getDescription());
        }
        if (taskUpdateDTO.getPriority() != null) {
            task.setPriority(taskUpdateDTO.getPriority());
        }
        if (taskUpdateDTO.getDate() != null) {
            task.setDate(taskUpdateDTO.getDate());
        }
        if (taskUpdateDTO.getTaskSituation() != null) {
            task.setTaskSituation(taskUpdateDTO.getTaskSituation());
        }
        if (taskUpdateDTO.getUserId() != null) {
            task.setUser(userService.findById(taskUpdateDTO.getUserId()));
        }

        TaskEntity updatedTask = taskRepository.save(task);

        return TaskMapper.toResponseDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        this.findByid(id); // Verificar se a tarefa existe
        taskRepository.deleteById(id);
    }
}
