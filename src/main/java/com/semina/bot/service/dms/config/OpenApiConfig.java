package com.semina.bot.service.dms.config;

import io.swagger.v3.oas.models.Components;
        import io.swagger.v3.oas.models.OpenAPI;
        import io.swagger.v3.oas.models.info.Contact;
        import io.swagger.v3.oas.models.info.Info;
        import io.swagger.v3.oas.models.info.License;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final Info info = new Info()
                .title("DMS 3rd Party Integration Service")
                .description("3rd Party Service Simulating a DMS for Bot")
                .version("0.0.1")
                .license(new License().url("https://opensource.org/licenses/Apache-2.0")
                        .name("Apache Licence Version 2.0"))
                .contact(new Contact()
                        .email("princekudzaimaposa94@gmail.com")
                        .name("Developer Prince")
                        .url("http://developerprince.co.zw/")
                );

        return new OpenAPI().components(new Components())
                .info(info);
    }

}