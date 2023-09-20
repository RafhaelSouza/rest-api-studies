package com.studies.foodorders.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.model.kitchen.KitchenModel;
import com.studies.foodorders.api.openapi.models.KitchensModelOpenApi;
import com.studies.foodorders.api.openapi.models.PageableModelOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.studies.foodorders.api"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.globalRequestParameters(Collections.singletonList(
						new RequestParameterBuilder()
								.name("fields")
								.description("Names of properties to filter on the response, separated by commas")
								.in(ParameterType.QUERY)
								.required(true)
								.query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
								.build()))
				.additionalModels(typeResolver.resolve(ApiError.class))
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, KitchenModel.class),
						KitchensModelOpenApi.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cities", "Manage Cities"),
						new Tag("Groups", "Manage User Groups"),
						new Tag("Kitchens", "Manage Kitchens"),
						new Tag("Payment Way", "Manage Payment Way"));
	}

	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}

	private List<Response> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("Resource does not have representation that can be accepted by the consumer")
						.build(),
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Internal Server Error")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build()
		);
	}

	private List<Response> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Invalid request (Client error)")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
						.description("Resource does not have representation that can be accepted by the consumer")
						.build(),
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
						.description("Request refused because the body is in an unsupported format")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Internal Server Error")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build()
		);
	}

	private List<Response> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
						.description("Invalid request (Client error)")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build(),
				new ResponseBuilder()
						.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
						.description("Internal Server Error")
						.representation(MediaType.APPLICATION_JSON )
						.apply(getApiErrorModelReference())
						.build()
		);
	}

	private Consumer<RepresentationBuilder> getApiErrorModelReference() {
		return r -> r.model(m -> m.name("ApiError")
				.referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
						q -> q.name("ApiError").namespace("com.studies.foodorders.api.exceptionhandler")))));
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Order Food API")
				.description("Open API")
				.version("1")
				.contact(new Contact("Order Food", "https://www.domain.com", "contact@domain.com"))
				.build();
	}
	
}
