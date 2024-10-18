package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
    * Конструктор класу Owner.
    * 
    * @param name Ім'я власника.
    * @param contactInfo Контактна інформація власника.
    * @param address Адреса власника.
    */

public class Owner {
    private String name;
    private String contactInfo;
    private String address;
    private List<Pet> pets;

    private Owner(Builder builder) {
        this.name = builder.name;
        this.contactInfo = builder.contactInfo;
        this.address = builder.address;
        this.pets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public List<Pet> getPets() {
        return new ArrayList<>(pets);
    }

    public static class Builder {
        private String name;
        private String contactInfo;
        private String address;

        private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Owner build() {
            List<String> errors = new ArrayList<>();

            if (name == null || name.trim().isEmpty()) {
                errors.add("name: '" + name + "' (Ім'я не може бути пустим)");
            } else if (name.length() < 2 || name.length() > 50) {
                errors.add("name: '" + name + "' (Довжина імені повинна бути від 2 до 50 символів)");
            }

            if (contactInfo == null || contactInfo.trim().isEmpty()) {
                errors.add("contactInfo: '" + contactInfo + "' (Контактна інформація не може бути пустою)");
            } else if (!EMAIL_PATTERN.matcher(contactInfo).matches()) {
                errors.add("contactInfo: '" + contactInfo + "' (Некоректний формат email)");
            }

            if (address == null || address.trim().isEmpty()) {
                errors.add("address: '" + address + "' (Адреса не може бути пустою)");
            } else if (address.length() < 5 || address.length() > 100) {
                errors.add("address: '" + address + "' (Довжина адреси повинна бути від 5 до 100 символів)");
            }

            if (!errors.isEmpty()) {
                String errorMessage = "Помилки валідації при створенні Owner:\n" + String.join("\n", errors);
                throw new IllegalArgumentException(errorMessage);
            }

            return new Owner(this);
        }
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }
    
    @Override
    public String toString() {
        return "Owner{name='" + name + "', contactInfo='" + contactInfo + "', address='" + address + "'}";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Owner owner = (Owner) obj;
        return name.equals(owner.name) &&
               contactInfo.equals(owner.contactInfo) &&
               address.equals(owner.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contactInfo, address);
    }
}
