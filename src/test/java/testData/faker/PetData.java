
package testData.faker;

import com.github.javafaker.Faker;
import payload.pet.Category;
import payload.pet.Pet;
import payload.pet.Tag;

import java.util.List;

public class PetData {

    private static final Faker faker = FakerUtility.getFaker();

    public static Pet getPetData() {

        Pet pet = new Pet();

        Category category = new Category();
        category.setId(1);
        category.setName(faker.animal().name());

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName(faker.animal().name());

        pet.setId(faker.number().numberBetween(1000, 999999));
        pet.setCategory(category);
        pet.setName(faker.animal().name());
        pet.setPhotoUrls(
                List.of("https://example.com/" + faker.lorem().word() + ".jpg")
        );
        pet.setTags(List.of(tag));
        pet.setStatus("available");

        return pet;
    }
}