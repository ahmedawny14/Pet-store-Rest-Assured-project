package test.userTests;

import endpoints.UsersEndpoints;
import factory.UserFactory;
import org.json.simple.parser.ParseException;
import payload.User;
 import base.BaseTest;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import utilities.dataReader.JsonReader;


import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UserTests extends BaseTest {
        User user;
        Response response;
        JsonReader userData;
UserFactory userFactory;


    @Test(priority = 1)
    public void createUserTest() throws IOException, ParseException {
        userFactory =new UserFactory();
        user= userFactory.createUser();
         response = UsersEndpoints.createUser(user)
                .then().log().all().extract().response();
          assertThat(response.statusCode(), equalTo(200));
       }

      @Test
      public void getUserTest() throws IOException, ParseException {
          userData =new JsonReader("userData");
            response=UsersEndpoints.getUser(userData.getJsonData("username"))
                   .then().extract().response();
            assertThat(response.statusCode(), equalTo(200));



      }

      @Test
    public void updateUserTest() throws IOException, ParseException {
          userData =new JsonReader("userData");

          userFactory=new UserFactory();
           user=userFactory.updateUser();
           response= UsersEndpoints.updateUser(userData.getJsonData("username"),user)
                   .then().log().body().extract().response();
          assertThat(response.statusCode(),equalTo(200));


      }

      @Test
    public void deleteUserTest() throws IOException, ParseException {
          userData= new JsonReader("userData");
          response=UsersEndpoints.deleteUser(userData.getJsonData("username"))
                  .then().extract().response();
          assertThat(response.statusCode(),equalTo(200));

      }


      @Test
      public void getUserWithUnknownUsernameTest() {
          response = UsersEndpoints.getUser("user_does_not_exist_" + System.nanoTime())
                  .then().log().all().extract().response();
          assertThat(response.statusCode(), equalTo(404));
      }

      @Test
      public void updateUserWithUnknownUsernameTest() throws IOException, ParseException {
          userFactory = new UserFactory();
          user = userFactory.updateUser();
          response = UsersEndpoints.updateUser("missing_user_" + System.nanoTime(), user)
                  .then().log().all().extract().response();
          assertThat(response.statusCode(), equalTo(404));
      }

      @Test
      public void deleteUserWithUnknownUsernameTest() {
          response = UsersEndpoints.deleteUser("missing_user_" + System.nanoTime())
                  .then().log().all().extract().response();
          assertThat(response.statusCode(), equalTo(404));
      }

      @Test
      public void createUserWithoutUsernameTest() {
          User invalidUser = testData.faker.UserData.getUserData();
          invalidUser.setUsername(null);
          response = UsersEndpoints.createUser(invalidUser)
                  .then().log().all().extract().response();
          assertThat(response.statusCode(), org.hamcrest.Matchers.anyOf(
                  equalTo(400), equalTo(422)));
      }

      @Test
      public void createUserWithEmptyBodyTest() {
          response = io.restassured.RestAssured.given()
                  .spec(specifications.Specifications.requestSpecification())
                  .body("{}")
                  .when().post(endpoints.Routes.CREATE_USER)
                  .then().log().all().extract().response();
          assertThat(response.statusCode(), org.hamcrest.Matchers.anyOf(
                  equalTo(400), equalTo(422)));
      }

}
