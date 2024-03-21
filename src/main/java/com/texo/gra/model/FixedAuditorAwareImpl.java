package com.texo.gra.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class FixedAuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Texo_user");
    }
}
