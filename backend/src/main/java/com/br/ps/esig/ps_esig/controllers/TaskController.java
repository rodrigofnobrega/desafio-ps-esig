package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid TaskCreateDTO taskCreateDTO) {
        taskService.create(taskCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable(value = "id") Long id) {
        TaskResponseDTO task = TaskMapper.toResponseDTO(taskService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TaskResponseDTO>> findByUser(@PathVariable(value = "id") Long id) {
        List<TaskResponseDTO> tasks = TaskMapper.toListResponseDTO(taskService.findByUser(id));

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
}
