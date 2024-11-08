package lab1.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.Veterinarian;
import lab1.VeterinarianDAO;

public class VeterinarianService {
    private static final Logger logger = Logger.getLogger(VeterinarianService.class.getName());
    private VeterinarianDAO veterinarianDAO = new VeterinarianDAO();

    /**
     * Створює запис про ветеринара в базі даних.
     *
     * @param veterinarian ветеринар, якого потрібно створити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void createVeterinarian(Veterinarian veterinarian) throws SQLException {
        logger.log(Level.INFO, "Створення ветеринара {0}", veterinarian.getName());
        try {
            veterinarianDAO.createVeterinarian(veterinarian);
            logger.log(Level.INFO, "Ветеринара успішно створено");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при створенні ветеринара: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує ветеринара за його ID.
     *
     * @param id ID ветеринара, якого потрібно отримати
     * @return Optional з ветеринаром, якщо знайдено, або порожній Optional, якщо ветеринара не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public Optional<Veterinarian> getVeterinarianById(int id) throws SQLException {
        logger.log(Level.INFO, "Отримання ветеринара з ID: {0}", id);
        try {
            Veterinarian vet = veterinarianDAO.getVeterinarianById(id);
            if (vet == null) {
                logger.log(Level.WARNING, "Ветеринара з ID {0} не знайдено", id);
                return Optional.empty();
            }
            return Optional.of(vet);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні ветеринара: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує всіх ветеринарів з бази даних.
     *
     * @return список усіх ветеринарів; повертає порожній список, якщо ветеринарів не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public List<Veterinarian> getAllVeterinarians() throws SQLException {
        logger.log(Level.FINE, "Отримання всіх ветеринарів");
        try {
            return veterinarianDAO.getAllVeterinarians();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні ветеринарів: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Оновлює наявний запис про ветеринара у базі даних.
     *
     * @param id ID ветеринара, якого потрібно оновити
     * @param veterinarian оновлені дані ветеринара
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void updateVeterinarian(int id, Veterinarian veterinarian) throws SQLException {
        logger.log(Level.INFO, "Оновлення ветеринара з ID: {0}", id);
        try {
            veterinarianDAO.updateVeterinarian(id, veterinarian);
            logger.log(Level.INFO, "Ветеринара з ID {0} успішно оновлено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при оновленні ветеринара: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Видаляє запис про ветеринара з бази даних.
     *
     * @param id ID ветеринара, якого потрібно видалити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void deleteVeterinarian(int id) throws SQLException {
        logger.log(Level.INFO, "Видалення ветеринара з ID: {0}", id);
        try {
            veterinarianDAO.deleteVeterinarian(id);
            logger.log(Level.INFO, "Ветеринара з ID {0} успішно видалено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при видаленні ветеринара: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }
}
