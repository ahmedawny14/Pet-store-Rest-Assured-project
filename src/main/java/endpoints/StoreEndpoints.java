package endpoints;

import io.restassured.response.Response;
import payload.Order;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

public class StoreEndpoints {

    public static Response getInventory() {
        return given()
                .spec(Specifications.requestSpecification())
                .when()
                .get(Routes.GET_Inventory);
    }

    public static Response postOrder(Order order) {
        return given()
                .spec(Specifications.requestSpecification())
                .body(order)
                .when()
                .post(Routes.PLACE_ORDER);
    }

    public static Response getOrder(int orderId) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.GET_ORDER);
    }

    public static Response deleteOrder(int orderId) {
        return given()
                .spec(Specifications.requestSpecification())
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.DELETE_ORDER);
    }
}
