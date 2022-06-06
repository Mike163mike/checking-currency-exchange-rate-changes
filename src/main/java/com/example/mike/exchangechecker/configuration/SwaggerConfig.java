package com.example.mike.exchangechecker.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("exchange-checker")
                        .description("RESTful App with Feign HTTP - client")
                        .contact(new Contact("Mike", "", "mick_mick_mick@icloud.com"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mike.exchangechecker.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
