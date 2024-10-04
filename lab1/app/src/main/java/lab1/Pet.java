package lab1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

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
    private LocalDate birthDate;
    private String species;
    private Owner owner;

    public Pet(String name, LocalDate birthDate, String species, Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.species = species;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getSpecies() {
        return species;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Pet{name='" + name + "', age=" + getAge() + ", species='" + species + "', owner=" + owner + "}";
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

