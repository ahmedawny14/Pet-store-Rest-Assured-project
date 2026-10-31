package endpoints;

import io.restassured.response.Response;
import payload.User;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

public class UsersEndpoints {

    public static Response createUser(User payload) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(payload)
                .when()
                .post(Routes.CREATE_USER);
    }

    public static Response getUser(String username) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("username", username)
                .when()
                .get(Routes.GET_USER);
    }

    public static Response updateUser(String username, User payload) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(Routes.UPDATE_USER);
    }

    public static Response deleteUser(String username) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("username", username)
                .when()
                .delete(Routes.DELETE_USER);
    }
}
