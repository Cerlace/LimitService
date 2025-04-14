package cerlace.limitservice.core.config;

import cerlace.limitservice.persistence.config.ModulePersistenceConfig;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"cerlace.limitservice.core"})
@EnableFeignClients(basePackages = {"cerlace.limitservice.core.external"})
@Import(ModulePersistenceConfig.class)
public class ModuleCoreConfig {
}
