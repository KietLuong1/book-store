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

    @Column(nullable = false, length = 255)
    private String description;

    public int getPublisherId() {
        return publisher_id;
    }

    public String getPublisherName() {
        return publisher_name;
    }

    public void setPublisherName(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
