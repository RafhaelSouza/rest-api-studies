package com.studies.foodorders.kitchen;

import com.studies.foodorders.domain.models.kitchen.Kitchen;
import com.studies.foodorders.domain.repositories.kitchen.KitchenRepository;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class KitchenServiceIT {

    private static final int INEXISTENT_ID_KITCHEN = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    private Kitchen britishKitchen;
    private int amountSavedKitchens;
    private String jsonChineseKitchen;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";
        jsonChineseKitchen = ResourceUtils.getContentFromResource(
                "/json-files/kitchen/chinese-kitchen.json");
        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    public void mustReturnStatus200WhenSearchKitchens() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void mustHave2KitchensWhenSearchKitchens() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", hasSize(amountSavedKitchens));
    }

    @Test
    public void mustReturnStatus201WhenSaveKitchen() {
        given()
                .body(jsonChineseKitchen)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void mustReturnResponseAndCorrectStatusWhenSearchExistentKitchen() {
        given()
                .pathParam("kitchenId", britishKitchen.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo(britishKitchen.getName()));
    }

    @Test
    public void mustReturnStatus404WhenSearchInexistentKitchen() {
        given()
                .pathParam("kitchenId", INEXISTENT_ID_KITCHEN)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareData() {
        britishKitchen = new Kitchen();
        britishKitchen.setName("British");
        kitchenRepository.save(britishKitchen);

        Kitchen brazilianKitchen = new Kitchen();
        brazilianKitchen.setName("Brazilian");
        kitchenRepository.save(brazilianKitchen);

        amountSavedKitchens = (int) kitchenRepository.count();
    }

}
