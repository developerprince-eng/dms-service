package com.semina.bot.service.dms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Profile({"development", "staging"})
@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket userEndpoint(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.semina.bot.service.dms"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        return new ApiInfoBuilder()
                .title("DMS 3rd Party Integration Service")
                .description( "3rd Party Service Simulating a DMS for Bot")
                .version("0.0.1")
                .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                .license("Apache Licence Version 2.0")
                .build();
    }
}