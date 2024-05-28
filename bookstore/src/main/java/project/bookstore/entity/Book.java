package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

//    public Book(int book_id) {
//        this.book_id = book_id;
//    }

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String category;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 255)
    private float salePrice;

    @Column(nullable = false, length = 255)
    private float price;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 255)
    private String publication_date;

    @Column(nullable = false)
    private int num_pages;

    @Column(nullable = false)
    private String book_image;

}
