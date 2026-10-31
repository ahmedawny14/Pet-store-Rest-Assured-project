package test.petsTests;

import base.BaseTest;
import endpoints.PetsEndPoints;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import payload.pet.petApiError;
import utilities.dataReader.JsonReader;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPetTest extends BaseTest {
    Response response;
    JsonReader petData;


    @Test
    public void getPetTest() throws IOException, ParseException {
        petData=new JsonReader("petData");
        response= PetsEndPoints.getPet(petData.getJsonData("id"))
                .then()
                .log()
                .all()
                .extract().response();;
                assertThat(response.statusCode(),equalTo(200));

    }

    @Test
    public void getPetWithNotFoundId() throws IOException, ParseException {
        petData=new JsonReader("petData");
        response= PetsEndPoints.getPet(petData.getJsonData("invalidPetId"))
                .then()
                .log()
                .all()
                .extract().response();
        petApiError error = response.as(petApiError.class);

        assertThat(response.statusCode(),equalTo(404));
        assertThat(error.getMessage(),equalTo(petData.getJsonData("errorMessages.message")));
    }






    @Test
    public void getPetWithNegativeIdTest() {
        response = PetsEndPoints.getPet("-1")
                .then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(404));
    }

    @Test
    public void getPetWithNonNumericIdTest() {
        response = PetsEndPoints.getPet("not-a-number")
                .then().log().all().extract().response();
        assertThat(response.statusCode(), org.hamcrest.Matchers.anyOf(
                equalTo(400), equalTo(404)));
    }

}
