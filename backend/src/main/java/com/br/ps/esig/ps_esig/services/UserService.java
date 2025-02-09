package com.br.ps.esig.ps_esig.services;

import com.br.ps.esig.ps_esig.dto.mapper.UserMapper;
import com.br.ps.esig.ps_esig.dto.user.UserCreateDTO;
import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void create(UserCreateDTO userCreateDTO) {
        UserEntity user = UserMapper.toUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado com id=" + userId)
        );
    }
}
