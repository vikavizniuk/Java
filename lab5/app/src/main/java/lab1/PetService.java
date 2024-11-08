package lab1;

import java.util.List;
import java.util.stream.Collectors;

public class PetService {

    private List<Pet> pets;
    public PetService(List<Pet> pets){
        this.pets = pets;
    }
    public List<Pet> sortPetsByAge() {
        return pets.stream()
                   .sorted()
                   .collect(Collectors.toList());
    }

    public List<Pet> sortPetsByName() {
        return pets.stream()
                   .sorted(new PetNameComparator())
                   .collect(Collectors.toList());
    }

    public List<Pet> findPetByName(String name) {
        return pets.stream()
                   .filter(pet -> pet.getName().equalsIgnoreCase(name))
                   .toList();
    }
}
