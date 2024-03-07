package project.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String category;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 255)
    private String price;

    @Column(nullable = false, length = 255)
    private String quantity;

    @Column(nullable = false, length = 255)
    private String language;

    @Column(nullable = false, length = 255)
    private String publication_date;

    @Column(nullable = false, length = 255)
    private String num_pages;
    @Column(length = 45, nullable = true)
    private String book_imgae;

    public String getBook_imgae() {
        return book_imgae;
    }
    public void setBook_imgae(String book_imgae) {
        this.book_imgae = book_imgae;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationDate() {
        return publication_date;
    }

    public void setPublicationDate(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getNumPages() {
        return num_pages;
    }

    public void setNumPages(String num_pages) {
        this.num_pages = num_pages;
    }

}
