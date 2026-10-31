package test.petsTests;

import base.BaseTest;
import endpoints.PetsEndPoints;
import factory.PetDataFactory;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import payload.pet.Pet;
import utilities.dataReader.JsonReader;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Pet Management API")
public class UpdatePetTest extends BaseTest {
    JsonReader petData;
    Pet pet;
    Response response;
    PetDataFactory petDataFactory;
    @Test
    public void updatePetWIthValidData() throws IOException, ParseException {
        petDataFactory=new PetDataFactory();
        petData= new JsonReader("petData");
        pet= petDataFactory.updatePetFromJson();
        response= PetsEndPoints.updatePet(pet)
                .then()
                .log().all()
                .extract().response();
         assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("name"), equalTo(pet.getName()));
        assertThat(response.jsonPath().getString("status"), equalTo(pet.getStatus()));



    }
    @Test
    @Story("Update Pet Details")
    @Description("Verify that updating a pet that does not exist returns 404.")
    public void updateNonExistingPetTest() throws IOException, ParseException {
        petDataFactory = new PetDataFactory();
        pet = petDataFactory.updatePetFromJson();
        pet.setId(999999999);

        response = PetsEndPoints.updatePet(pet)
                .then().log().all().extract().response();

        assertThat(response.statusCode(), equalTo(404));
    }

    @Test
    @Story("Update Pet Details")
    @Description("Verify that an update request with an empty body is rejected.")
    public void updatePetWithEmptyBodyTest() {
        response = io.restassured.RestAssured.given()
                .spec(specifications.Specifications.requestSpecification())
                .body("{}")
                .when().put(endpoints.Routes.UPDATE_PET)
                .then().log().all().extract().response();

        assertThat(response.statusCode(), equalTo(400));

    }
    }
