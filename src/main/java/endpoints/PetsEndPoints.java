package endpoints;

import io.restassured.response.Response;
import payload.pet.Pet;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

public class PetsEndPoints {

    public static Response addPet(Pet pet) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(pet)
                .when()
                .post(Routes.ADD_PET);
    }

    public static Response getPet(String petId) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("petId", petId)
                .when()
                .get(Routes.GET_PET);
    }

    public static Response updatePet(Pet pet) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(pet)
                .when()
                .put(Routes.UPDATE_PET);
    }

    public static Response deletePet(int petId) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("petId", petId)
                .when()
                .delete(Routes.DELETE_PET);
    }
}
