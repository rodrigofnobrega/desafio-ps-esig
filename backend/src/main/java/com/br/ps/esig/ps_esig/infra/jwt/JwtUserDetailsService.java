package com.br.ps.esig.ps_esig.infra.jwt;

import com.br.ps.esig.ps_esig.entities.UserEntity;
import com.br.ps.esig.ps_esig.repositories.UserRepository;
import com.br.ps.esig.ps_esig.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado.")
        );


        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String email) {
        return JwtUtils.createToken(email, "ROLE_USER");
    }
}