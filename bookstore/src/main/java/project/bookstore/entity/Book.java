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

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String category;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String salePrice;

    @Column(nullable = false, length = 255)
    private String price;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false, length = 255)
    private String publication_date;

    private int num_pages;

    private String book_image;

}
