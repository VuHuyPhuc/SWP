// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

import java.sql.Date;

/**
 *
 * @author tuan
 */
public class Loan {
    int id;
    Book book;
    User user;
    Date dateActual;
    Date dateFrom;
    Date dateTo;
    String status;

    public Loan() {
    }

    public Loan(int id, Book book, User user, Date dateActual, Date dateFrom, Date dateTo, String status) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.dateActual = dateActual;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateActual() {
        return dateActual;
    }

    public void setDateActual(Date dateActual) {
        this.dateActual = dateActual;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
  
    
}
