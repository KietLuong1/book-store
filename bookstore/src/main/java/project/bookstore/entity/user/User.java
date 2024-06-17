package project.bookstore.entity.user;

import jakarta.persistence.*;
import lombok.*;
import project.bookstore.entity.Address;
import project.bookstore.entity.Order;
import project.bookstore.enums.UserStatus;

import java.util.Set;

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

//    private String status;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "image_URL")
    private String avatarURL;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

}