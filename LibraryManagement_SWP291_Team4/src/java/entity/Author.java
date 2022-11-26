// This is a personal academic project. Dear PVS-Studio, please check it.

// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package entity;

/**
 *
 * @author s
 */
public class Author {
    int id;
    String fullname;
    String image;
    String information;
    String breif_information;

    public Author(int id, String fullname, String image, String information, String breif_information) {
        this.id = id;
        this.fullname = fullname;
        this.image = image;
        this.information = information;
        this.breif_information = breif_information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getBreif_information() {
        return breif_information;
    }

    public void setBreif_information(String breif_information) {
        this.breif_information = breif_information;
    }
    
}
