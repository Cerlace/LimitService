package cerlace.limitservice.web.config;

import cerlace.limitservice.core.config.ModuleCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"cerlace.limitservice.web"})
@Import(ModuleCoreConfig.class)
public class ModuleWebConfig {
}
