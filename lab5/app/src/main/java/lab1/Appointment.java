package lab1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Appointment {
    private int id;
    private final Pet pet;
    private final Veterinarian veterinarian;
    private final LocalDateTime dateTime;

    private Appointment(Builder builder) {
        this.id = builder.id;
        this.pet = builder.pet;
        this.veterinarian = builder.veterinarian;
        this.dateTime = builder.dateTime;
    }

    public int getId() {
        return id;
    }

    public Appointment setId(int id) {
        this.id = id;
        return this;
    }

    public Pet getPet() {
        return pet;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Appointment{pet=" + pet + ", veterinarian=" + veterinarian + ", dateTime=" + dateTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return pet.equals(that.pet) &&
               veterinarian.equals(that.veterinarian) &&
               Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pet, veterinarian, dateTime);
    }

    public static class Builder {
        private int id;
        private Pet pet;
        private Veterinarian veterinarian;
        private LocalDateTime dateTime;

        public Builder setPet(Pet pet) {
            this.pet = pet;
            return this;
        }

        public Builder setVeterinarian(Veterinarian veterinarian) {
            this.veterinarian = veterinarian;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Appointment build() {
            List<String> errors = new ArrayList<>();
            if (pet == null) {
                errors.add("pet: null (Pet не може бути null)");
            }
            if (veterinarian == null) {
                errors.add("veterinarian: null (Veterinarian не може бути null)");
            }
            if (dateTime == null) {
                errors.add("dateTime: null (Дата та час не можуть бути null)");
            } 

            if (!errors.isEmpty()) {
                String errorMessage = "Помилки валідації при створенні Appointment:\n" + String.join("\n", errors);
                throw new IllegalArgumentException(errorMessage);
            }

            return new Appointment(this);
        }
    }
}
