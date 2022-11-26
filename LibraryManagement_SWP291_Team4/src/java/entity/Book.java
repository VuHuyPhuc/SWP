// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

import java.sql.Date;

/**
 *
 * @author s
 */
public class Book {
    private int id;
    private String title;
    private Category category_id;
    private String publisher;
    private Date publication_year;
    private int quantity;
    private String image;
    private int rate;
    private boolean status;
    private String introduction;
    private String description;

    public Book() {
    }

    public Book(int id, String title, Category category_id, String publisher, Date publication_year, int quantity, String image, int rate, boolean status, String introduction, String description) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.quantity = quantity;
        this.image = image;
        this.rate = rate;
        this.status = status;
        this.introduction = introduction;
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(Date publication_year) {
        this.publication_year = publication_year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
