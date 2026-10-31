package factory;

import org.json.simple.parser.ParseException;
import payload.User;
import utilities.dataReader.JsonReader;

import java.io.IOException;

public class UserFactory {

    public User createUser() throws IOException, ParseException {
        JsonReader userData = new JsonReader("userData");
        User user = new User();
        user.setId(Integer.parseInt(userData.getJsonData("id")));
        user.setUsername(userData.getJsonData("username") + System.nanoTime());
        user.setFirstName(userData.getJsonData("firstName"));
        user.setLastName(userData.getJsonData("lastName"));
        user.setEmail(userData.getJsonData("email"));
        user.setPassword(userData.getJsonData("password"));
        user.setPhone(userData.getJsonData("phoneNumber"));
        user.setUserStatus(Integer.parseInt(userData.getJsonData("status")));
        return user;
    }

    public User updateUser() throws IOException, ParseException {
        JsonReader userData = new JsonReader("userData");
        User user = new User();
        user.setFirstName(userData.getJsonData("updatedFirstName"));
        user.setLastName(userData.getJsonData("updatedLastName"));
        return user;
    }
}
