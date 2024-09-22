package lab1;

import java.util.Objects;

/**
    * Конструктор класу Veterinarian.
    * 
    * @param name Ім'я ветеринара.
    * @param speciality Спеціалізація ветеринара.
    */

public class Veterinarian {
    private String name;
    private Speciality speciality;

    public Veterinarian(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpecialty() {
        return speciality;
    }

    @Override
    public String toString() {
        return "Veterinarian{name='" + name + "', specialty='" + speciality + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veterinarian that = (Veterinarian) obj;
        return name.equals(that.name) && speciality.equals(that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speciality);
    }
}

