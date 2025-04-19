/*
* References
*
* Medium (2023, April 1). Swagger-Open API to Spring Boot 3 Application(Web MVC). Retrieved April 17, 2025, from https://pranavkhodanpur.medium.com/swagger-open-api-to-spring-boot-3-application-d29d45d50fc2#:~:text=The%20springdoc%2Dopenapi%2Dstarter%2D,.info(
*
 */
package com.bookclub.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * OpenAPI Configuration, needed for spring security 6.4.4
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .description("My REST API")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}