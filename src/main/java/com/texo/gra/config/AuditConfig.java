package com.texo.gra.config;

import com.texo.gra.model.FixedAuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "fixedAuditorAwareImpl")
public class AuditConfig {

    @Bean
    public AuditorAware<String> fixedAuditorAwareImpl() {
        return new FixedAuditorAwareImpl();
    }
}
