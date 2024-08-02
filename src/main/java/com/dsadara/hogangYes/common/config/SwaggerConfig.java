package com.dsadara.hogangYes.common.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    TypeResolver typeResolver = new TypeResolver();
    public static final String REAL_ESTATE_TAG = "real_estate";
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dsadara.hogangYes"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(REAL_ESTATE_TAG, "realestate api"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("호갱예스 : ")
                .description("부동산의 실거래 내역을 종류별로 조회할 수 있는 API입니다.\n")
                .version("1.1")
                .build();
    }
}
