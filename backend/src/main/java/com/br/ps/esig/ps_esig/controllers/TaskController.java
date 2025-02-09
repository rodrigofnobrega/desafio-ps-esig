package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.mapper.TaskMapper;
import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.entities.TaskEntity;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
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

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponseDTO>> searchTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) TaskSituationEnum situation
    ) {
        List<TaskResponseDTO> tasks = TaskMapper.toListResponseDTO(taskService.searchTasks(id, term, userId, situation));

        return ResponseEntity.ok(tasks);
    }
}
