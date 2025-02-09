package com.br.ps.esig.ps_esig.repositories.projection;

import java.time.LocalDateTime;

public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();
    String getPassword();
    LocalDateTime getCreatedAt();
}

