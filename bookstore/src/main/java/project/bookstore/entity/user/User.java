package project.bookstore.entity.user;

import jakarta.persistence.*;
import lombok.*;
import project.bookstore.entity.Address;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 50)
    private String phone;

    @Column(length = 50)
    private String occupation;

    private int age;

    private String description;

    private boolean status = true;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
}