package comfort.soft.test;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().contact(new Contact().name("Igor Shmidt").email("shmidtigor0704@gmail.com"))
                        .description("Documentation for ComfortSoft test task.").version("1.0"))
                .servers(List.of(new Server().url("http://localhost:8080").description("Search N service.")));
    }

}
