package lab1;

import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

/**
    * Конструктор класу Pet.
    * 
    * @param name Ім'я домашнього улюбленця.
    * @param birthdate Вік домашнього улюбленця.
    * @param species Вид домашнього улюбленця.
    * @param owner Власник домашнього улюбленця.
    */

public class Pet implements Comparable<Pet>{
    private int id;
    private String name;
    private LocalDate birthDate;
    private String species;
    private Owner owner;

    private Pet(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.birthDate = builder.birthDate;
        this.species = builder.species;
        this.owner = builder.owner;
    }

    public int getId() {
        return id;
    }

    public Pet setId(int id){
        this.id = id;
        return this;
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

    public static class Builder {
        private int id;
        private String name;
        private LocalDate birthDate;
        private String species;
        private Owner owner;

        private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-яІіЇїЄєҐґ'\\-\\s]{2,30}$");
        private static final Pattern SPECIES_PATTERN = Pattern.compile("^[A-Za-zА-Яа-яІіЇїЄєҐґ\\s]{2,30}$");

    
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Pet build() {
            List<String> errors = new ArrayList<>();

            if (name == null || name.trim().isEmpty()) {
                errors.add("name: '" + name + "' (Ім'я не може бути пустим)");
            } else if (!NAME_PATTERN.matcher(name).matches()) {
                errors.add("name: '" + name + "' (Ім'я містить недопустимі символи або має некоректну довжину)");
            }

            if (birthDate == null) {
                errors.add("birthDate: null (Дата народження не може бути null)");
            } else if (birthDate.isAfter(LocalDate.now())) {
                errors.add("birthDate: " + birthDate + " (Дата народження не може бути в майбутньому)");
            }

            if (species == null || species.trim().isEmpty()) {
                errors.add("species: '" + species + "' (Вид не може бути пустим)");
            } else if (!SPECIES_PATTERN.matcher(species).matches()) {
                errors.add("species: '" + species + "' (Вид містить недопустимі символи або має некоректну довжину)");
            }

            if (owner == null) {
                errors.add("owner: null (Власник не може бути null)");
            }

            if (!errors.isEmpty()) {
                String errorMessage = "Помилки валідації при створенні Pet:\n" + String.join("\n", errors);
                throw new IllegalArgumentException(errorMessage);
            }

            return new Pet(this);
        }
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
    @Override
    public int compareTo(Pet other) {
        return Integer.compare(this.getAge(), other.getAge());
    }

}

