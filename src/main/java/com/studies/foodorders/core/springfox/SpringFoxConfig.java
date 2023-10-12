package com.studies.foodorders.core.springfox;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.studies.foodorders.api.exceptionhandler.ApiError;
import com.studies.foodorders.api.v1.models.kitchen.KitchenModel;
import com.studies.foodorders.api.v1.models.localization.city.CityModel;
import com.studies.foodorders.api.v1.models.localization.state.StateModel;
import com.studies.foodorders.api.v1.models.order.OrderSummaryModel;
import com.studies.foodorders.api.v1.models.paymentway.PaymentWayModel;
import com.studies.foodorders.api.v1.models.product.ProductModel;
import com.studies.foodorders.api.v1.models.restaurant.RestaurantBasicModel;
import com.studies.foodorders.api.v1.models.security.group.GroupModel;
import com.studies.foodorders.api.v1.models.security.permission.PermissionModel;
import com.studies.foodorders.api.v1.models.security.user.UserModel;
import com.studies.foodorders.api.v1.openapi.models.*;
import com.studies.foodorders.api.v2.models.kitchen.KitchenModelV2;
import com.studies.foodorders.api.v2.models.localization.city.CityModelV2;
import com.studies.foodorders.api.v2.openapi.models.CitiesModelV2OpenApi;
import com.studies.foodorders.api.v2.openapi.models.KitchensModelV2OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	// http://localhost:8080/swagger-ui/index.html

	@Bean
	public Docket apiDocketV1() {
		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.OAS_30)
				.groupName("v1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.studies.foodorders.api"))
					.paths(PathSelectors.ant("/v1/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(ApiError.class))
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, OrderSummaryModel.class),
						OrdersSummaryModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, KitchenModel.class),
						KitchensModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CityModel.class),
						CitiesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, StateModel.class),
						StatesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, PaymentWayModel.class),
						PaymentWaysModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, GroupModel.class),
						GroupsModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, PermissionModel.class),
						PermissionsModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, ProductModel.class),
						ProductsModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, RestaurantBasicModel.class),
						RestaurantsBasicModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, UserModel.class),
						UsersModelOpenApi.class))
				.apiInfo(apiInfoV1())
				.tags(
						new Tag("Groups", "Manage User Groups"),
						//new Tag("Cities", "Manage Cities"),
						//new Tag("Kitchens", "Manage Kitchens"),
						new Tag("Payment Ways", "Manage Payment Ways"),
						new Tag("Orders", "Manage Orders"),
						new Tag("Restaurants", "Manage Restaurants"),
						new Tag("States", "Manage States"),
						new Tag("Products", "Manage Products"),
						new Tag("Users", "Manage Users"),
						new Tag("Statistics", "Sell Statistics"),
						new Tag("Permissions", "Manage Permissions"));
	}

	@Bean
	public Docket apiDocketV2() {
		var typeResolver = new TypeResolver();

		return new Docket(DocumentationType.OAS_30)
				.groupName("v2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.studies.foodorders.api"))
				.paths(PathSelectors.ant("/v2/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(ApiError.class))
				.ignoredParameterTypes(ServletWebRequest.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, KitchenModelV2.class),
						KitchensModelV2OpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CityModelV2.class),
						CitiesModelV2OpenApi.class))
				.apiInfo(apiInfoV2())
				.tags(new Tag("Cities", "Manage Cities"),
						new Tag("Kitchens", "Manage Kitchens"));
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

	public ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("Order Food API")
				.description("Open API")
				.description("<strong>This version of the cities and kitchens API is deprecated and will be available up to mm/dd/YYYY."
						+ "Use the most current version of the API.")
				.version("1")
				.contact(new Contact("Order Food", "https://www.domain.com", "contact@domain.com"))
				.build();
	}

	public ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.title("Order Food API")
				.description("Open API")
				.version("2")
				.contact(new Contact("Order Food", "https://www.domain.com", "contact@domain.com"))
				.build();
	}
	
}
