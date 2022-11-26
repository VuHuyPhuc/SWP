// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Appointment implements Comparable<Appointment> {

    private int appointment_id;
    private int user_id;
    private String user_fullname;
    private Date date;
    private String status;

    private Book book;

    public Appointment() {
    }

    public Appointment(int appointment_id, int user_id, Date date, String status, Book book) {
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.date = date;
        this.status = status;
        this.book = book;
    }

    public Appointment(int appointment_id, int user_id, String user_fullname, Date date, String status, Book book) {
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.user_fullname = user_fullname;
        this.date = date;
        this.status = status;
        this.book = book;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int compareTo(Appointment appointment) {
        return getDate().compareTo(appointment.getDate());
    }

}
