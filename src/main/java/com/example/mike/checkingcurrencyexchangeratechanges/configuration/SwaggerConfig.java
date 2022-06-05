package com.example.mike.checkingcurrencyexchangeratechanges.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("checking-currency-exchange-rate-changes")
                        .description("RESTful App with Feign HTTP - client")
                        .contact(new Contact("Mike", "", "mick_mick_mick@icloud.com"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("checkingcurrencyexchangeratechanges.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
