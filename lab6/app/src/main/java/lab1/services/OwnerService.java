package lab1.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.Owner;
import lab1.OwnerDAO;

public class OwnerService {
    private static final Logger logger = Logger.getLogger(OwnerService.class.getName());
    private OwnerDAO ownerDAO = new OwnerDAO();

    /**
     * Створює запис про власника в базі даних.
     *
     * @param owner власник, якого потрібно створити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void createOwner(Owner owner) throws SQLException {
        logger.log(Level.INFO, "Створення власника {0}", owner.getName());
        try {
            logger.log(Level.INFO, "Власника успішно створено");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при створенні власника: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує власника за його ID.
     *
     * @param id ID власника, якого потрібно отримати
     * @return Optional з власником, якщо знайдено, або порожній Optional, якщо власника не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public Optional<Owner> getOwnerById(int id) throws SQLException {
        logger.log(Level.INFO, "Отримання власника з ID: {0}", id);
        try {
            Owner owner = ownerDAO.getOwnerById(id);
            if (owner == null) {
                logger.log(Level.WARNING, "Власника з ID {0} не знайдено", id);
                return Optional.empty();
            }
            return Optional.of(owner);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні власника: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує всіх власників з бази даних.
     *
     * @return список усіх власників; повертає порожній список, якщо власників не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public List<Owner> getAllOwners() throws SQLException {
        logger.log(Level.FINE, "Отримання всіх власників");
        try {
            return ownerDAO.getAllOwners();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні власників: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Оновлює наявний запис про власника у базі даних.
     *
     * @param id ID власника, якого потрібно оновити
     * @param owner оновлені дані власника
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void updateOwner(int id, Owner owner) throws SQLException {
        logger.log(Level.INFO, "Оновлення власника з ID: {0}", id);
        try {
            ownerDAO.updateOwner(id, owner);
            logger.log(Level.INFO, "Власника з ID {0} успішно оновлено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при оновленні власника: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Видаляє запис про власника з бази даних.
     *
     * @param id ID власника, якого потрібно видалити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void deleteOwner(int id) throws SQLException {
        logger.log(Level.INFO, "Видалення власника з ID: {0}", id);
        try {
            ownerDAO.deleteOwner(id);
            logger.log(Level.INFO, "Власника з ID {0} успішно видалено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при видаленні власника: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }
}
