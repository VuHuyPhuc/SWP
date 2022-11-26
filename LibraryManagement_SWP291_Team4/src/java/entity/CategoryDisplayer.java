// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

/**
 *
 * @author s
 */
public class CategoryDisplayer {
    private int id;
    private String name;
    private String image;
    private int bookTotal;

    public CategoryDisplayer() {
    }

    public CategoryDisplayer(int id, String name, String image, int bookTotal) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.bookTotal = bookTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBookTotal() {
        return bookTotal;
    }

    public void setBookTotal(int bookTotal) {
        this.bookTotal = bookTotal;
    }

   
    
    
}
