package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Owner(String name, String contactInfo, String address) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
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
