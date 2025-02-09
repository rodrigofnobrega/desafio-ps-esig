package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskUpdateDTO;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.br.ps.esig.ps_esig.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> searchTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) TaskSituationEnum situation,
            Pageable pageable
    ) {
        Page<TaskResponseDTO> tasks = TaskMapper.toPageResponseDTO(
                taskService.searchTasks(id, term, userId, situation, pageable));

        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
                                                      @RequestBody @Valid TaskUpdateDTO taskUpdateDTO) {

        TaskResponseDTO updatedTask = taskService.updateTask(id, taskUpdateDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
