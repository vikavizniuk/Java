package lab1.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.Appointment;
import lab1.AppointmentDAO;

public class AppointmentService {
    private static final Logger logger = Logger.getLogger(AppointmentService.class.getName());
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    /**
     * Створює запис про прийом у базі даних.
     *
     * @param appointment запис про прийом, який потрібно створити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void createAppointment(Appointment appointment) throws SQLException {
        logger.log(Level.INFO, "Створення запису для тварини {0} з ветеринаром {1}", 
                new Object[]{appointment.getPet().getName(), appointment.getVeterinarian().getName()});
        try {
            appointmentDAO.createAppointment(appointment);
            logger.log(Level.INFO, "Запис успішно створено");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при створенні запису: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує запис про прийом за його ID.
     *
     * @param id ID запису, який потрібно отримати
     * @return Optional з записом, якщо знайдено, або порожній Optional, якщо запис не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public Optional<Appointment> getAppointmentById(int id) throws SQLException {
        logger.log(Level.INFO, "Отримання запису з ID: {0}", id);
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            if (appointment == null) {
                logger.log(Level.WARNING, "Запис з ID {0} не знайдено", id);
                return Optional.empty();
            }
            return Optional.of(appointment);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні запису: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Отримує всі записи про прийом з бази даних.
     *
     * @return список усіх записів; повертає порожній список, якщо записів не знайдено
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public List<Appointment> getAllAppointments() throws SQLException {
        logger.log(Level.FINE, "Отримання всіх записів");
        try {
            return appointmentDAO.getAllAppointments();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при отриманні записів: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Оновлює наявний запис про прийом у базі даних.
     *
     * @param id ID запису, який потрібно оновити
     * @param appointment оновлені дані запису
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void updateAppointment(int id, Appointment appointment) throws SQLException {
        logger.log(Level.INFO, "Оновлення запису з ID: {0}", id);
        try {
            appointmentDAO.updateAppointment(id, appointment);
            logger.log(Level.INFO, "Запис з ID {0} успішно оновлено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при оновленні запису: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

    /**
     * Видаляє запис про прийом з бази даних.
     *
     * @param id ID запису, який потрібно видалити
     * @throws SQLException якщо виникає помилка доступу до бази даних
     */
    public void deleteAppointment(int id) throws SQLException {
        logger.log(Level.INFO, "Видалення запису з ID: {0}", id);
        try {
            appointmentDAO.deleteAppointment(id);
            logger.log(Level.INFO, "Запис з ID {0} успішно видалено", id);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Помилка при видаленні запису: {0}", e.getMessage());
            throw new SQLException(e);
        }
    }

}
