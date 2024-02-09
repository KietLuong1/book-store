package project.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//     @Column(nullable = false, unique = true, length = 45)
//     private String name;

//     @Column(nullable = false, length = 45)
//     private String category;

//     @Column(nullable = false, length = 45)
//     private String author;

//     @Column(nullable = false, length = 45)
//     private String description;

//     @Column(nullable = false, length = 45)
//     private String price;

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getCategory() {
//         return category;
//     }

//     public void setCategory(String category) {
//         this.category = category;
//     }

//     public String getAuthor() {
//         return author;
//     }

//     public void setAuthor(String author) {
//         this.author = author;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getPrice() {
//         return price;
//     }

//     public void setPrice(String price) {
//         this.price = price;
//     }
// }
    private String name;
    private String catagory;
    private String author;
    private String description;
    private String price;

    public Book(int id, String name, String catagory, String author, String description, String price) {
        this.id = id;
        this.name = name;
        this.catagory = catagory;
        this.author = author;
        this.description = description;
        this.price = price;
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

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
