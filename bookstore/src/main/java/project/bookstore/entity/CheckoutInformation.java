package project.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.bookstore.entity.user.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checkout")
public class CheckoutInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id")
    private Integer id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

//    @OneToOne(mappedBy = "checkoutInformation", cascade = CascadeType.ALL)
//    private Address checkoutAddress;

//    @ManyToOne
//    @JoinColumn(name = "user_checkout_id")
//    private User checkoutUser;

    private boolean isDefault;
}
