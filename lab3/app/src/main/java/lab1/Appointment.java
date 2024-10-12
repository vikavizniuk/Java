package lab1;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Appointment {
    private final Pet pet;
    private final Veterinarian veterinarian;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final LocalDateTime dateTime;

    private Appointment(Builder builder) {
        this.pet = builder.pet;
        this.veterinarian = builder.veterinarian;
        this.dateTime = builder.dateTime;
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
            return new Appointment(this);
        }
    }
}

