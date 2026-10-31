
package testData.faker;

import com.github.javafaker.Faker;
import payload.User;

public class UserData {

    private static final Faker faker = FakerUtility.getFaker();

    public static User getUserData() {

        User user = new User();

        user.setId(1);
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setUserStatus(1);

        return user;
    }

    public static User getUserWithoutEmail() {

        User user = getUserData();
        user.setEmail(null);

        return user;
    }

    public static User getUserWithoutPassword() {

        User user = getUserData();
        user.setPassword(null);

        return user;
    }
}