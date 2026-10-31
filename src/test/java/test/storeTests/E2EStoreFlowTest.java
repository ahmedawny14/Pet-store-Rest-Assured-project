package test.storeTests;

import base.BaseTest;
import endpoints.StoreEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.Order;
import testData.faker.StoreData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class E2EStoreFlowTest extends BaseTest {
    Order order;
    Response response;

    @Test
    public void placeOrder()
    {
       order= StoreData.getOrderData() ;
       response= StoreEndpoints.postOrder(order)
               .then()
               .log().all()
               .extract().response();
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.jsonPath().getInt("id"),equalTo(order.getId()));
        assertThat(response.jsonPath().getInt("petId"),equalTo(order.getPetId()));


    }

    @Test(dependsOnMethods = "placeOrder")
    public void getOrder()
    {
        int orderId=order.getId();
        response= StoreEndpoints.getOrder(orderId)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.jsonPath().getInt("id"),equalTo(order.getId()));
        assertThat(response.jsonPath().getInt("petId"),equalTo(order.getPetId()));

    }


    @Test(dependsOnMethods = "getOrder")
    public void deleteOrder()
    {

        int orderId=order.getId();
        response= StoreEndpoints.deleteOrder(orderId)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));
     }


    @Test(dependsOnMethods ="deleteOrder")
    public void getDeletedOrder()
    {
        int orderId=order.getId();
        response= StoreEndpoints.getOrder(orderId)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(404));


    }


}
