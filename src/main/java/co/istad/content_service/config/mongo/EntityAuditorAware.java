package co.istad.content_service.config.mongo;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Primary
//@Component
public class EntityAuditorAware implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of("Admin");
//    }
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !(auth.getPrincipal() instanceof Jwt)) {
            return Optional.of("Something went wrong");
        }

        Jwt jwt = (Jwt) auth.getPrincipal();
        String uuid = jwt.getClaim("userUuid");
        System.out.println("UUID USER"+uuid);
//        return Optional.of(jwt.getId());
        return Optional.of(uuid);
    }

}
