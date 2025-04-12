package cerlace.limitservice.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"cerlace.limitservice.persistence"})
@EnableJpaRepositories(basePackages={"cerlace.limitservice.persistence.repository"})
@EntityScan(basePackages={"cerlace.limitservice.persistence.entity"})
public class ModulePersistenceConfig {
}
