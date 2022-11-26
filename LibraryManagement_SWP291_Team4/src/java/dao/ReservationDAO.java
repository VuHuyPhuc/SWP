/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Reservation;
import java.util.List;

/**
 *
 * @author s
 */
public interface ReservationDAO extends BaseDAO<Reservation>{
    List<Reservation> getAllReservationByUserIdAndStatus(int userId, String status);
    public Reservation getReservaionDetailByReservationIdAndUserIdAndStatus(int reservationId, int userId, String status);
}
