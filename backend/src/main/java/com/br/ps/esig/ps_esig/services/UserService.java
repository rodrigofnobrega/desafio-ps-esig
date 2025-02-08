package com.br.ps.esig.ps_esig.services;

import com.br.ps.esig.ps_esig.dto.mapper.UserMapper;
import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(UserCreateDTO userCreateDTO) {
        userRepository.save(UserMapper.toUser(userCreateDTO));
    }

    @Transactional(readOnly = true)
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado com id=" + userId)
        );
    }
}
