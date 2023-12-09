package kitri.dev6.memore.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Memo.re",
                version = "v1.0",
                description = "도서기록 사이트 API 문서"
        )
)
@Configuration
public class SwaggerConfig {
}
