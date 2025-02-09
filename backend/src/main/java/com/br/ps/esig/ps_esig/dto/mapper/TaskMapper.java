package com.br.ps.esig.ps_esig.dto.mapper;

import com.br.ps.esig.ps_esig.dto.task.TaskCreateDTO;
import com.br.ps.esig.ps_esig.dto.task.TaskResponseDTO;
import com.br.ps.esig.ps_esig.entities.TaskEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskResponseDTO toResponseDTO(TaskEntity taskEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(taskEntity, TaskResponseDTO.class);
    }

    public static TaskEntity toTask(TaskCreateDTO taskCreateDTO) {
        PropertyMap<TaskCreateDTO, TaskEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(taskCreateDTO, TaskEntity.class);
    }

    public static Page<TaskResponseDTO> toPageResponseDTO(Page<TaskEntity> data) {
        List<TaskResponseDTO> dtos = data.stream()
                .map(TaskMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, data.getPageable(), data.getTotalElements());
    }
}
