
package factory;

import org.json.simple.parser.ParseException;
import payload.pet.Category;
import payload.pet.Pet;
import payload.pet.Tag;
import utilities.dataReader.JsonReader;

import java.io.IOException;
import java.util.List;

public class PetDataFactory {
    Pet pet = new Pet();

    public Pet createPetFromJson() throws IOException, ParseException {

        JsonReader petData = new JsonReader("PetData");

        Category category = new Category();
        category.setId(Integer.parseInt(
                petData.getJsonData("category.id")));
        category.setName(
                petData.getJsonData("category.name"));

        Tag tag = new Tag();
        tag.setId(Integer.parseInt(
                petData.getJsonData("tags[0].id")));
        tag.setName(
                petData.getJsonData("tags[0].name"));

        pet.setId(Integer.parseInt(
                petData.getJsonData("id")));
        pet.setCategory(category);
        pet.setName(petData.getJsonData("name"));
        pet.setPhotoUrls(
                petData.getJsonArrayData("photoUrls"));
        pet.setTags(List.of(tag));
        pet.setStatus(petData.getJsonData("status"));

        return pet;
    }

    public Pet updatePetFromJson() throws IOException, ParseException {
        JsonReader petData = new JsonReader("PetData");
        createPetFromJson();
           pet.setName(petData.getJsonData( "updatePet.name"));
         pet.setStatus(petData.getJsonData( "updatePet.status"));
         return pet;

    }
    }

