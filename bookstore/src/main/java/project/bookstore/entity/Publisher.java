package project.bookstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisher_id;

    @Column(nullable = false, length = 255)
    private String publisher_name;

    public int getPublisherId() {
        return publisher_id;
    }

    public String getPublisherName() {
        return publisher_name;
    }

    public void setPublisherName(String publisher_name) {
        this.publisher_name = publisher_name;
    }
}
