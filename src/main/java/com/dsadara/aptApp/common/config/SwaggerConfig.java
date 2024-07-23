package com.dsadara.aptApp.common.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    TypeResolver typeResolver = new TypeResolver();
    public static final String REAL_ESTATE_TAG = "real_estate";
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Pageable.class),
                        typeResolver.resolve(MyPagable.class)))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dsadara.aptApp"))
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

    @Data
    @ApiModel
    static class MyPagable {
        @ApiModelProperty(value = "페이지 번호 (0 ~ N)")
        private Integer page;

        @ApiModelProperty(value = "페이지 크기", allowableValues = "range[0, 100]")
        private Integer size;

        @ApiModelProperty(value = "정렬 (사용법: 컬럼명,ASC|DESC)")
        private List<String> sort;
    }
}
