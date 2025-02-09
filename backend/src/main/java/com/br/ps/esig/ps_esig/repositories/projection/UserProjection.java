package com.br.ps.esig.ps_esig.repositories.projection;

import com.br.ps.esig.ps_esig.utils.RefactorDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();
    String getPassword();
    @JsonIgnore
    LocalDateTime getCreatedAt();

    @JsonProperty("createdAt")
    private String getCreateAtFormattedDate() {
        return RefactorDate.refactorLocalDateTime(getCreatedAt());
    }
}

