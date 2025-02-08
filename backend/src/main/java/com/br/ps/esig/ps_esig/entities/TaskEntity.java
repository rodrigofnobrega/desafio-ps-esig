package com.br.ps.esig.ps_esig.entities;

import com.br.ps.esig.ps_esig.enums.PriorityEnum;
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

    @Column(name = "data")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
