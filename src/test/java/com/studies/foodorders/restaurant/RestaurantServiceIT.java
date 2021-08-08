package com.studies.foodorders.restaurant;

import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import com.studies.foodorders.domain.repositories.kitchen.KitchenRepository;
import com.studies.foodorders.domain.repositories.restaurant.RestaurantRepository;
import com.studies.foodorders.utils.DatabaseCleaner;
import com.studies.foodorders.utils.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantServiceIT {

    private static final String BUSINESS_RULE_VIOLATION = "Business Rule Violation";

    private static final String INVALID_DATA = "Invalid Data";

    private static final int INEXISTENT_RESTAURANT = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private String jsonValidRestaurant;
    private String jsonRestaurantWithoutTaxDelivery;
    private String jsonRestauranteWithoutKitchen;
    private String jsonRestaurantWithInexistentKitchen;

    private Restaurant topBurguerRestaurant;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        jsonValidRestaurant = ResourceUtils.getContentFromResource(
                "/json-files/restaurant/restaurant-new-york-barbecue.json");

        jsonRestaurantWithoutTaxDelivery = ResourceUtils.getContentFromResource(
                "/json-files/restaurant/restaurant-new-york-barbecue-without-tax-delivery.json");

        jsonRestauranteWithoutKitchen = ResourceUtils.getContentFromResource(
                "/json-files/restaurant/restaurantenew-york-barbecue-without-kitchen.json");

        jsonRestaurantWithInexistentKitchen = ResourceUtils.getContentFromResource(
                "/json-files/restaurant/restaurant-new-york-barbecue-with-inexistent-kitchen.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void mustReturnStatus200WhenSearchRestaurants() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void mustReturnStatus201WhenSaveRestaurant() {
        given()
                .body(jsonValidRestaurant)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnStatus400WhenSaveRestaurantWithoutDeliveryTax() {
        given()
                .body(jsonRestaurantWithoutTaxDelivery)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(INVALID_DATA));
    }

    @Test
    public void mustReturnStatus400WhenSaveRestaurantWithoutKitchen() {
        given()
                .body(jsonRestauranteWithoutKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(INVALID_DATA));
    }

    @Test
    public void mustReturnStatus400WhenSaveRestaurantWithInexistentKitchen() {
        given()
                .body(jsonRestaurantWithInexistentKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(BUSINESS_RULE_VIOLATION));
    }

    @Test
    public void mustReturnResponseAndCorrectStatusWhenSearchExistentRestaurant() {
        given()
                .pathParam("restauranteId", topBurguerRestaurant.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(topBurguerRestaurant.getName()));
    }

    @Test
    public void mustReturnStatus404WhenSearchInexistentRestaurant() {
        given()
                .pathParam("restauranteId", INEXISTENT_RESTAURANT)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        Kitchen brazilianKitchen = new Kitchen();
        brazilianKitchen.setName("Brazilian");
        kitchenRepository.save(brazilianKitchen);

        Kitchen americanKitchen = new Kitchen();
        americanKitchen.setName("American");
        kitchenRepository.save(americanKitchen);

        topBurguerRestaurant = new Restaurant();
        topBurguerRestaurant.setName("Burger Top");
        topBurguerRestaurant.setShippingCosts(new BigDecimal(10));
        topBurguerRestaurant.setKitchen(americanKitchen);
        restaurantRepository.save(topBurguerRestaurant);

        Restaurant mineiraFoodRestaurant = new Restaurant();
        mineiraFoodRestaurant.setName("Mineira Food");
        mineiraFoodRestaurant.setShippingCosts(new BigDecimal(10));
        mineiraFoodRestaurant.setKitchen(brazilianKitchen);
        restaurantRepository.save(mineiraFoodRestaurant);
    }

}
