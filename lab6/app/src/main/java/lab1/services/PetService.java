package lab1.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.Pet;
import lab1.PetDAO;

public class PetService {
    private static final Logger logger = Logger.getLogger(PetService.class.getName());
    private PetDAO petDAO = new PetDAO();

    /**
     * Створює запис про тварину в базі даних.
     *
     * @param pet тварина, яку потрібно створити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void createPet(Pet pet) throws SQLException {
        logger.log(Level.INFO, "Створення тварини {0}", pet.getName());
        try {
            petDAO.createPet(pet);
            logger.log(Level.INFO, "Тварину успішно створено");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при створенні тварини: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує тварину за її ID.
     *
     * @param id ID тварини, яку потрібно отримати
     * @return Optional з твариною, якщо знайдено, або порожній Optional, якщо тварину не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public Optional<Pet> getPetById(int id) throws SQLException {
        logger.log(Level.INFO, "Отримання тварини з ID: {0}", id);
        try {
            Pet pet = petDAO.getPetById(id);
            if (pet == null) {
                logger.log(Level.WARNING, "Тварину з ID {0} не знайдено", id);
                return Optional.empty();
            }
            return Optional.of(pet);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні тварини: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує всіх тварин з бази даних.
     *
     * @return список усіх тварин; повертає порожній список, якщо тварин не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public List<Pet> getAllPets() throws SQLException {
        logger.log(Level.FINE, "Отримання всіх тварин");
        try {
            return petDAO.getAllPets();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні тварин: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Оновлює наявний запис про тварину у базі даних.
     *
     * @param id ID тварини, яку потрібно оновити
     * @param pet оновлені дані тварини
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void updatePet(int id, Pet pet) throws SQLException {
        logger.log(Level.INFO, "Оновлення тварини з ID: {0}", id);
        try {
            petDAO.updatePet(id, pet);
            logger.log(Level.INFO, "Тварину з ID {0} успішно оновлено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при оновленні тварини: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Видаляє запис про тварину з бази даних.
     *
     * @param id ID тварини, яку потрібно видалити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void deletePet(int id) throws SQLException {
        logger.log(Level.INFO, "Видалення тварини з ID: {0}", id);
        try {
            petDAO.deletePet(id);
            logger.log(Level.INFO, "Тварину з ID {0} успішно видалено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при видаленні тварини: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }
}
