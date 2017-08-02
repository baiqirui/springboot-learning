package com.mealkey.core.config;

/**
 * Created by lvjj on 2016/11/17.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    /**
     * SpringBoot默认已经将classpath:/META-INF/resources/和classpath:/META-INF/resources/webjars/映射
     * 所以该方法不需要重写，如果在SpringMVC中，可能需要重写定义
     * 重写该方法需要 extends WebMvcConfigurerAdapter
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 定义order组
     *
     * @return
     */
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("mealtime")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true).enableUrlTemplating(false)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
 .apis(RequestHandlerSelectors.basePackage("com.mealkey.mealtime"))
//                .paths(Predicates.or(PathSelectors.regex("/api/*")))//过滤的接口
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("餐时间相关接口")
                        .version("1.0")
                        .build()
                );

        return docket;
    }


}