package lab1;

import lab1.serializer.JSONSerializer;
import lab1.serializer.XMLSerializer;
import lab1.serializer.YMLSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws IOException {
        LocalDate birthDate1 = LocalDate.of(2021, 9, 10);
        
        Pet pet = new Pet("Барсик", birthDate1, "Кіт", new Owner("Іван Іваненко", "ivan@example.com", "Київ, вул. Хрещатик, 1"));

        // JSON
        JSONSerializer<Pet> jsonSerializer = new JSONSerializer<>();
        String json = jsonSerializer.serialize(pet);
        System.out.println("JSON: " + json);
        jsonSerializer.writeToFile(pet, new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.json"));
        Pet petFromJson = jsonSerializer.readFromFile(new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.json"), Pet.class);
        System.out.println("Зчитано з JSON: " + petFromJson);

        // XML
        XMLSerializer<Pet> xmlSerializer = new XMLSerializer<>();
        String xml = xmlSerializer.serialize(pet);
        System.out.println("XML: " + xml);
        xmlSerializer.writeToFile(pet, new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.xml"));
        Pet petFromXml = xmlSerializer.readFromFile(new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.xml"), Pet.class);
        System.out.println("Зчитано з XML: " + petFromXml);

        // YAML
        YMLSerializer<Pet> yamlSerializer = new YMLSerializer<>();
        String yaml = yamlSerializer.serialize(pet);
        System.out.println("YAML: " + yaml);
        yamlSerializer.writeToFile(pet, new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.yaml"));
        Pet petFromYaml = yamlSerializer.readFromFile(new File("/Users/vikaviznuk/Desktop/programming/java/lab3/app/src/main/java/lab1/data/pet.yaml"), Pet.class);
        System.out.println("Зчитано з YAML: " + petFromYaml);
    }
}