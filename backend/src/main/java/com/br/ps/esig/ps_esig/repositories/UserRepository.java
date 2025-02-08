package com.br.ps.esig.ps_esig.repositories;

import com.br.ps.esig.ps_esig.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
