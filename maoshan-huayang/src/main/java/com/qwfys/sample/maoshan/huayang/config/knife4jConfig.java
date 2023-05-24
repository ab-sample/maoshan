package com.qwfys.sample.maoshan.huayang.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Configuration
public class knife4jConfig {

    @Bean
    public GroupedOpenApi baseRestApi() {
        return GroupedOpenApi.builder()
                .group("接口文档")
                .packagesToScan("com.qwfys.sample.maoshan")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Service Provider接口文档")
                                .description("Service Provider接口文档")
                                .version("0.0.1-SNAPSHOT")
                                .license(
                                        new License()
                                                .name("使用请遵守MIT License授权协议")
                                                .url("https://github.com/ab-sample/maoshan")
                                )
                );
    }
}