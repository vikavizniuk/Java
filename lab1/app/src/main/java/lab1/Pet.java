package lab1;

import java.util.Calendar;
import java.util.Objects;
import java.util.Date;

/**
    * Конструктор класу Pet.
    * 
    * @param name Ім'я домашнього улюбленця.
    * @param birthdate Вік домашнього улюбленця.
    * @param species Вид домашнього улюбленця.
    * @param owner Власник домашнього улюбленця.
    */
public class Pet {
    private String name;
    private Date birthDate;
    private String species;
    private Owner owner;

    public Pet(String name, Date birthDate, String species, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.species = species;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', age=" + birthDate + ", species='" + species + "', owner=" + owner + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        return Objects.equals(birthDate, pet.birthDate) &&
               Objects.equals(name, pet.name) &&
               Objects.equals(species, pet.species) &&
               Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, species, owner);
    }
}
