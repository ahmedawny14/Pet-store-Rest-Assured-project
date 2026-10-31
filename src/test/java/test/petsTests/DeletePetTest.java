package test.petsTests;

import base.BaseTest;
import endpoints.PetsEndPoints;
import factory.PetDataFactory;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import payload.pet.Pet;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeletePetTest extends BaseTest {

    private Response response;

    @Test
    public void deletePetTest() throws IOException, ParseException {
        Pet pet = new PetDataFactory().createPetFromJson();

        Response createResponse = PetsEndPoints.addPet(pet);
        assertThat(createResponse.statusCode(), equalTo(200));

        response = PetsEndPoints.deletePet(pet.getId())
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode(), equalTo(200));

        response = PetsEndPoints.getPet(String.valueOf(pet.getId()))
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode(), equalTo(404));
    }
    @Test
    public void deleteNonExistingPetTest() {
        response = PetsEndPoints.deletePet(999999999)
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(404));
    }

    @Test
    public void deletePetTwiceTest() throws IOException, ParseException {
        Pet pet = new PetDataFactory().createPetFromJson();

        Response createResponse = PetsEndPoints.addPet(pet);
        assertThat(createResponse.statusCode(), equalTo(200));

        Response firstDelete = PetsEndPoints.deletePet(pet.getId());
        assertThat(firstDelete.statusCode(), equalTo(200));

        response = PetsEndPoints.deletePet(pet.getId())
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(404));
    }

}
