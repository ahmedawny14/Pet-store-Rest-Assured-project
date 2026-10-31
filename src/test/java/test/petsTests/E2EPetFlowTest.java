package test.petsTests;

import base.BaseTest;
import endpoints.PetsEndPoints;
import io.restassured.response.Response;
 import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import payload.pet.Pet;
import testData.faker.PetData;

 import static org.junit.Assert.assertThat;

public class E2EPetFlowTest extends BaseTest {
    Pet pet;
    PetData petData;
    Response response;
    @Test
    public void createPet()
    {
        pet= PetData.getPetData();
        response= PetsEndPoints.addPet(pet)
                .then()
                .log()
                .all()
                .extract().response();
          assertThat(response.statusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("name"), equalTo(pet.getName()));
    }


    @Test(dependsOnMethods = "createPet")
    public void getPet()
    {
       int petId= pet.getId();
        response= PetsEndPoints.getPet(String.valueOf(petId))
                .then()
                .log()
                .all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("name"), equalTo(pet.getName()));
    }

    @Test(dependsOnMethods = "getPet")
    public void updatePet()
    {
        pet.setName("cat");
         response= PetsEndPoints.updatePet(pet)
                .then()
                .log()
                .all()
                .extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getInt("id"),
                equalTo(pet.getId()));
        assertThat(response.jsonPath().getString("name"),
                equalTo("cat"));
    }

    @Test(dependsOnMethods = "updatePet")
    public void deletePet()
    {
        int petId= pet.getId();
        response= PetsEndPoints.deletePet(petId)
                .then()
                .log()
                .all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));
     }
    @Test(dependsOnMethods = "deletePet")
    public void verifyDeletedPet() {
        int petId = pet.getId();

        response = PetsEndPoints.getPet(String.valueOf(petId))
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode(), equalTo(404));
    }


    
}
