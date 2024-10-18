package lab1;

import java.util.List;
import java.util.ArrayList;

import java.util.Objects;
import java.util.regex.Pattern;

/**
    * Конструктор класу Veterinarian.
    * 
    * @param name Ім'я ветеринара.
    * @param speciality Спеціалізація ветеринара.
    */

public class Veterinarian {
    private String name;
    private Speciality speciality;

    private Veterinarian(Builder builder) {
        this.name = builder.name;
        this.speciality = builder.speciality;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpecialty() {
        return speciality;
    }

    public static class Builder {
        private String name;
        private Speciality speciality;

        private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-яІіЇїЄєҐґ'\\-\\s]{2,50}$");

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSpeciality(Speciality speciality) {
            this.speciality = speciality;
            return this;
        }

        public Veterinarian build() {
            List<String> errors = new ArrayList<>();

            if (name == null || name.trim().isEmpty()) {
                errors.add("name: '" + name + "' (Ім'я не може бути пустим)");
            } else if (!NAME_PATTERN.matcher(name).matches()) {
                errors.add("name: '" + name + "' (Ім'я містить недопустимі символи або має некоректну довжину)");
            }

            if (speciality == null) {
                errors.add("speciality: null (Спеціальність не може бути null)");
            }

            if (!errors.isEmpty()) {
                String errorMessage = "Помилки валідації при створенні Veterinarian:\n" + String.join("\n", errors);
                throw new IllegalArgumentException(errorMessage);
            }

            return new Veterinarian(this);
        }
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

