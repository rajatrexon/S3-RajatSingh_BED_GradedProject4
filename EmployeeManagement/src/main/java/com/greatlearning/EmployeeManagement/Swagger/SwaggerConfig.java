package com.greatlearning.EmployeeManagement.Swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket employeeApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("Employee-API").select()
				.apis(RequestHandlerSelectors.basePackage("com.greatlearning.EmployeeManagement.Controller")).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee API").description("Employee API for management")
				.termsOfServiceUrl("http://fakeEmployee.com")
				.contact(new Contact("Employee API", "http://fakeEmployee.com", "fakeEmployee@gmail.com"))
				.license("Management License").licenseUrl("http://fakeEmployee.com").version("1.0").build();
	}

}
