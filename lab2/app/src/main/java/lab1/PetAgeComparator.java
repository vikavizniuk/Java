package lab1;

import java.util.Comparator;

public class PetAgeComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getBirthDate().compareTo(p2.getBirthDate());
    }
}