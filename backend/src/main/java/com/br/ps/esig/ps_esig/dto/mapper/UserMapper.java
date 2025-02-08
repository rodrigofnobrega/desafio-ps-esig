package com.br.ps.esig.ps_esig.dto.mapper;

import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.dto.user.UserResponseDTO;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(UserEntity userEntity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    public static UserEntity toUser(UserCreateDTO userDTO) {
        PropertyMap<UserCreateDTO, UserEntity> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(null);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(userDTO, UserEntity.class);
    }
}
