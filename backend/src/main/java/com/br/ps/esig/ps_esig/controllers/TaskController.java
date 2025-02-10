package com.br.ps.esig.ps_esig.controllers;

import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskUpdateDTO;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import com.br.ps.esig.ps_esig.infra.RestErrorMessage;
import com.br.ps.esig.ps_esig.repositories.projection.TaskProjection;
import com.br.ps.esig.ps_esig.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas", description = "Contém todas as operações relativas a gerenciamento de tarefas")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Operation(summary = "Criar uma tarefa",
            description = "Criará uma nova tarefa no sistema.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarefa cadastrada com sucesso.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Sem autorização para acessar o endpoint.",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s)",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
            })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> create(@RequestBody @Valid TaskCreateDTO taskCreateDTO) {
        taskService.create(taskCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Listar tarefas",
            description = "Listará as tarefas de acordo com os query parameters.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa listada com sucesso. Retorna uma página",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskProjection.class))),
                    @ApiResponse(responseCode = "401", description = "Sem autorização para acessar o endpoint.",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Erro ao processar requisição",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),

            })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<TaskProjection>> searchTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) TaskSituationEnum situation,
            Pageable pageable
    ) {
        Page<TaskProjection> tasks =   taskService.searchTasks(id, term, userId, situation, pageable);

        return ResponseEntity.ok(tasks);
    }


    @Operation(summary = "Atualizar tarefa pelo ID",
            description = "Atualizará os dados da tarefa no sistema.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Tarefa atualizada com sucesso. Retorna a tarefa atualizada",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição Inválida",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Sem autorização para acessar o endpoint.",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Erro ao processar requisição",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id,
                                                      @RequestBody @Valid TaskUpdateDTO taskUpdateDTO) {

        TaskResponseDTO updatedTask = taskService.updateTask(id, taskUpdateDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Deletar tarefa pelo ID",
            description = "Deletará uma tarefa no sistema.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Tarefa Deletada com sucesso.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "Sem autorização para acessar o endpoint.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RestErrorMessage.class))),
                    @ApiResponse(responseCode = "500", description = "Erro ao processar requisição",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RestErrorMessage.class)))
            })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
