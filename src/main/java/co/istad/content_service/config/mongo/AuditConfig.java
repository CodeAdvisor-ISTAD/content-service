package co.istad.content_service.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableMongoAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }

}
