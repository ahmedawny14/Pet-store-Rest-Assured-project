
package test.petsTests;

import base.BaseTest;
import endpoints.PetsEndPoints;
import factory.PetDataFactory;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
 import payload.pet.Pet;
 import java.io.IOException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("Pet Management API")
public class AddPetTest extends BaseTest {
    Pet pet;
    Response response;
    PetDataFactory petFactory;

    @Test
    @Story("Add a New Pet")
    @Description("Verify that a new pet can be added successfully and the API returns status code 200.")
    public void addPetTest() throws IOException, ParseException {
      petFactory=new PetDataFactory();
        pet = petFactory.createPetFromJson();
        response = PetsEndPoints.addPet(pet)
                .then()
                .log().all()
                .extract()
                .response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getInt("id"), equalTo(pet.getId()));
        assertThat(response.jsonPath().getString("name"), equalTo(pet.getName()));
        assertThat(response.jsonPath().getString("status"), equalTo(pet.getStatus()));
        assertThat(response.jsonPath().getList("photoUrls"), equalTo(pet.getPhotoUrls()));
     }


    @Test
    @Story("Add a New Pet")
    @Description("Verify the API behavior when the pet name is null.")
    public void addPetWithoutName() throws IOException, ParseException {
        petFactory = new PetDataFactory();
        pet = petFactory.createPetFromJson();
        pet.setName(null);

        response = PetsEndPoints.addPet(pet)
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode(), equalTo(400));
    }

    @Test
    @Story("Add a New Pet")
    @Description("Verify the API behavior when the pet status is null.")
    public void addPetWithoutStatus() throws IOException, ParseException {
        petFactory = new PetDataFactory();
        pet = petFactory.createPetFromJson();
        pet.setStatus(null);

        response = PetsEndPoints.addPet(pet)
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode(), equalTo(400));
    }

    @Test
    @Story("Add a New Pet")
    @Description("Verify the API behavior when the pet category is null.")
    public void addPetWithoutCategory() throws IOException, ParseException {
        petFactory = new PetDataFactory();
        pet = petFactory.createPetFromJson();
        pet.setCategory(null);

        response = PetsEndPoints.addPet(pet)
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode(), equalTo(400));
    }

    @Test
    @Story("Add a New Pet")
    @Description("Verify the API behavior when the pet tags are null.")
    public void addPetWithoutTags() throws IOException, ParseException {
        petFactory = new PetDataFactory();
        pet = petFactory.createPetFromJson();
        pet.setTags(null);

        response = PetsEndPoints.addPet(pet)
                .then()
                .log().all()
                .extract()
                .response();

        assertThat(response.statusCode(), equalTo(400));
    }




    @Test
    @Story("Add a New Pet")
    @Description("Verify that the API rejects a pet with an unsupported status value.")
    public void addPetWithInvalidStatusTest() throws IOException, ParseException {
        pet = new PetDataFactory().createPetFromJson();
        pet.setStatus("not-a-valid-status");

        response = PetsEndPoints.addPet(pet)
                .then().log().all().extract().response();

        assertThat(response.statusCode(), (equalTo(400)));

    }

    @Test
    @Story("Add a New Pet")
    @Description("Verify that the API rejects an empty request body.")
    public void addPetWithEmptyBodyTest() {
        response = io.restassured.RestAssured.given()
                .spec(specifications.Specifications.requestSpecification())
                .body("{}")
                .when().post(endpoints.Routes.ADD_PET)
                .then().log().all().extract().response();

        assertThat(response.statusCode(), (equalTo(400)));
    }

}
