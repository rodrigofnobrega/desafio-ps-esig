package com.br.ps.esig.ps_esig.services;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
