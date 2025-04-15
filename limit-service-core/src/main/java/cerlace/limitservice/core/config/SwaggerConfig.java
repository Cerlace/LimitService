package cerlace.limitservice.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spend Limit API",
                version = "1.0",
                description = """
                        API для управления лимитами на расходные операции.
                        
                        Имеет две точки доступа — первая для интеграции с банковским сервисом,
                        принимает транзакции и сохраняет их в базу данных, помечая превышение лимита.
                        
                        Вторая — для клиента, позволяет получить список всех лимитов,
                        установить новый лимит и получить все транзакции, превысившие лимит.
                        """,
                contact = @Contact(
                        name = "Support",
                        email = "nesteroffden@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
