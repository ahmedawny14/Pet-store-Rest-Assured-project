
package testData.faker;

import com.github.javafaker.Faker;

public class FakerUtility {

    private static final Faker faker = new Faker();

    public static Faker getFaker() {
        return faker;
    }
}