package test.storeTests;

import base.BaseTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import payload.Order;
import testData.faker.StoreData;
import utilities.dataReader.JsonReader;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreTests extends BaseTest {
    Order order;
    JsonReader orderData;
    Response response;

    @Test
    public void getInventoryTest()
    {

       response= StoreEndpoints.getInventory().then().extract().response();
        assertThat(response.statusCode(),equalTo(200));


    }


    @Test
    public void placeOrderTest() throws IOException, ParseException {
        orderData=new JsonReader("storeData");

        order = StoreData.getOrderData();
   order.setId(Integer.parseInt(orderData.getJsonData("id")));
        response=StoreEndpoints.postOrder(order)
                .then().extract().response();
        System.out.println(response.prettyPrint());
        assertThat(response.statusCode(),equalTo(200));
    }


    @Test
    public void getOrderTest() throws IOException, ParseException {
        orderData=new JsonReader("storeData");
        response= StoreEndpoints.getOrder(Integer.parseInt(orderData.getJsonData("id")))
                .then().extract().response();
        assertThat(response.statusCode(),equalTo(200));


    }

    @Test
    public void deleteOrderTest() throws IOException, ParseException {
        orderData=new JsonReader("storeData");
       response= StoreEndpoints.deleteOrder(Integer.parseInt(orderData.getJsonData("id")))
                .then().extract().response();
        assertThat(response.statusCode(),equalTo(200));


    }
    @Test
    public void getOrderWithNonExistingIdTest() {
        response = StoreEndpoints.getOrder(999999999)
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(404));
    }

    @Test
    public void getOrderWithNegativeIdTest() {
        response = StoreEndpoints.getOrder(-1)
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(400));
    }

    @Test
    public void deleteOrderWithNonExistingIdTest() {
        response = StoreEndpoints.deleteOrder(999999999)
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(404));
    }

    @Test
    public void placeOrderWithEmptyBodyTest() {
        response = io.restassured.RestAssured.given()
                .spec(specifications.Specifications.requestSpecification())
                .body("{}")
                .when().post(endpoints.Routes.PLACE_ORDER)
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(400));
    }

}
