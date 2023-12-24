package com.msd.product.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Product Microservice REST API Documentation",
                description = "Online-Retail-Store Product microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "SouravJune",
                        email = "souravpaul1715@gmail.com",
                        url = "https://www.linkedin.com/in/paulsourav/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.dummy.com"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8183"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        },
        externalDocs = @ExternalDocumentation(
                description =  "Online-Retail-Store Product microservice REST API Documentation",
                url = "https://www.dummy.com/swagger-ui. html"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class OpenApiConfiguration {
}
