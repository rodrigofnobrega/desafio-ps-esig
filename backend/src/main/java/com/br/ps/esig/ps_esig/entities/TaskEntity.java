package com.br.ps.esig.ps_esig.entities;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
import com.br.ps.esig.ps_esig.enums.TaskSituationEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarefas")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String title;

    @Column(name = "descricao", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade", nullable = false)
    private PriorityEnum priority;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private TaskSituationEnum taskSituation;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "atualizada_em", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @PrePersist
    protected void onCreate() {
        this.taskSituation = TaskSituationEnum.EM_ANDAMENTO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
