// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import entity.Appointment;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author s
 */
public interface AppointmentDAO extends BaseDAO<Appointment>{
    List<Appointment> getAllAppointmentByUserIdAndStatus(int userId, String status);
    Appointment getAppointmentDetailByAppointmentIdAndUserIdAndStatus(int appointmentId, int userId, String status);
}
