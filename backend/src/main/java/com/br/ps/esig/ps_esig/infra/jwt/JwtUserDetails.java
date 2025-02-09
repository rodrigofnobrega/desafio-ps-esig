package com.br.ps.esig.ps_esig.infra.jwt;

import com.br.ps.esig.ps_esig.entities.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class JwtUserDetails extends User {
    private UserEntity user;

    public JwtUserDetails(UserEntity user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public Long getId() {
        return this.user.getId();
    }
}
