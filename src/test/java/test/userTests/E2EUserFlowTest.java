package test.userTests;

import base.BaseTest;
import endpoints.UsersEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
 import payload.User;
import testData.faker.UserData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class E2EUserFlowTest extends BaseTest {

    Response response;
    User user;

    @Test
    public void createUser()
    {
        user= UserData.getUserData();
        response= UsersEndpoints.createUser(user)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));

    }

    @Test(dependsOnMethods = "createUser")
    public void getUser()
    {
        String username= user.getUsername();
        response= UsersEndpoints.getUser(username)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.jsonPath().getString("username"),equalTo(user.getUsername()));
        assertThat((response.jsonPath().getInt("id")),equalTo(user.getId()));

    }

    @Test(dependsOnMethods = "getUser")
    public void updateUser()
    {
        String username= user.getUsername();
        user.setFirstName("ahmed");
        user.setLastName("Awny");
        response= UsersEndpoints.updateUser(username,user)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));


    }


    @Test(dependsOnMethods = "updateUser")
    public void deleteUser()
    {
        String username= user.getUsername();
        response= UsersEndpoints.deleteUser(username)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(200));

    }

    @Test(dependsOnMethods = "deleteUser")
    public void getDeletedUser()
    {
        String username= user.getUsername();
        response= UsersEndpoints.getUser(username)
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(),equalTo(404));

    }





}
