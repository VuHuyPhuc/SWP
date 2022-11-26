// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

/**
 *
 * @author s
 */
public class Blog {
    int id;
    String title;
    String detail;
    String author;
    String image;
    int user_id;
    boolean status;

    public Blog(int id, String title, String detail, String author, String image, int user_id, boolean status) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.author = author;
        this.image = image;
        this.user_id = user_id;
        this.status = status;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", detail=" + detail + ", author=" + author + ", image=" + image + ", user_id=" + user_id + ", status=" + status + '}';
    }
    
    
}
