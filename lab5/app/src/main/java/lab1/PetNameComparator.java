package lab1;

import java.util.Comparator;

public class PetNameComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet pet1, Pet pet2) {
        return pet1.getName().compareToIgnoreCase(pet2.getName());
    }
}
