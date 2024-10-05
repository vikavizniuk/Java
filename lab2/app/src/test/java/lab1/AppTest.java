package lab1;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


class AppTest {
    
    @Test(dataProvider = "petProvider")
    public void testCompareTo(Pet pet1, Pet pet2, int expected) {
        assertEquals(pet1.compareTo(pet2), expected);
    }

    @DataProvider
    public Object[][] petProvider() {
        Owner owner = new Owner("Іван Іваненко", "ivan@example.com", "Київ, вул. Хрещатик, 1");
        return new Object[][]{
                {new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner),
                 new Pet("Рекс", LocalDate.of(2020, 5, 15), "Пес", owner), -1},
                {new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner),
                 new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner), 0},
                {new Pet("Рекс", LocalDate.of(2020, 5, 15), "Пес", owner),
                 new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner), 1}
        };
    }

    @Test(dataProvider = "petAgeProvider")
    public void testPetAgeComparator(Pet pet1, Pet pet2, int expected) {
        PetAgeComparator comparator = new PetAgeComparator();
        assertEquals(comparator.compare(pet1, pet2), expected);
    }

    @DataProvider
    public Object[][] petAgeProvider() {
        Owner owner = new Owner("Іван Іваненко", "ivan@example.com", "Київ, вул. Хрещатик, 1");
        return new Object[][]{
                {new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner),
                 new Pet("Рекс", LocalDate.of(2020, 5, 15), "Пес", owner), 1},
                {new Pet("Рекс", LocalDate.of(2020, 5, 15), "Пес", owner),
                 new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner), -1},
                {new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner),
                 new Pet("Барсик", LocalDate.of(2021, 9, 10), "Кіт", owner), 0}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "invalidPetProvider")
    public void testInvalidPetCreation(LocalDate birthDate) {
        Owner owner = new Owner("Іван Іваненко", "ivan@example.com", "Київ, вул. Хрещатик, 1");
        new Pet("Барсик", birthDate, "Кіт", owner);
    }

    @DataProvider
    public Object[][] invalidPetProvider() {
        return new Object[][]{
                {LocalDate.of(2025, 1, 1)}, 
                {LocalDate.of(3000, 12, 31)} 
        };
    }
}
