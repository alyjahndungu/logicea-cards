package com.logicea.cards.config;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Elijah Ndung'u",
                        email = "dev.elijah.ndungu@gmail.com",
                        url = "https://github.com/alyjahndungu"
                ),
                description = "Simple card application to manage tasks",
                title = "Cards API",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://springdoc.org"
                ),
                termsOfService = "https://github.com/alyjahndungu"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerDocConfig {
}
