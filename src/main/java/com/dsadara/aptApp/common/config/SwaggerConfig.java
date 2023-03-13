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
    public static final String APARTMENT_TAG = "apartment";
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
                .tags(new Tag(APARTMENT_TAG, "apartment api"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("호갱예스 : ")
                .description("아파트 정보, 아파트 거래를 할 수 있는 서비스 백엔드 API입니다.\n"
                        + "아파트 실거래가 서비스인 '호갱노노'를 참고해서 만들었습니다.")
                .version("1.0")
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
