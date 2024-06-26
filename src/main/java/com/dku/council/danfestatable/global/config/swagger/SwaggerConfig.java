package com.dku.council.danfestatable.global.config.swagger;

import com.dku.council.danfestatable.global.auth.jwt.JwtProvider;
import com.dku.council.danfestatable.global.config.jackson.JacksonDateTimeFormatter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Map;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Danfesta Table API",
                version = SwaggerConfig.API_VERSION,
                description = "단페스타 테이블 매칭을 위한 RESTFUL API 제공"
        ),
        servers = {
                @Server(url = "https://ese.danvery.com/api", description = "개발 서버"),
                @Server(url = "/", description = "로컬 서버")
        }
)
@SecurityScheme(
        name = JwtProvider.AUTHORIZATION,
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
    public static final String API_VERSION = "v1.0.0";

    @Bean
    public OpenApiCustomiser openAPICustomiser() {
        return openApi -> {
            openApi.getComponents().getSchemas().forEach((s, schema) -> {
                Map<String, Schema> properties = schema.getProperties();
                if (properties == null) {
                    properties = Map.of();
                }
                for (String propertyName : properties.keySet()) {
                    Schema propertySchema = properties.get(propertyName);
                    if (propertySchema instanceof DateTimeSchema) {
                        String example = JacksonDateTimeFormatter.DATE_TIME_FORMAT.format(
                                LocalDateTime.of(2022, 1, 1, 10, 35, 17));
                        properties.replace(propertyName, new StringSchema()
                                .example(example)
                                .description(propertySchema.getDescription()));
                    }
                }
            });
        };
    }
}
