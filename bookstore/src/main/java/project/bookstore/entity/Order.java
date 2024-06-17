package project.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.bookstore.entity.user.User;
import project.bookstore.enums.OrderStatus;
import project.bookstore.enums.PaymentMethod;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String address;

    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDate createdDate = LocalDate.now();

    private int deliverDays;

    private float shippingCost;

    private float total;

    private String note;

    @Column(name = "total_origin_price")
    private float totalOriginPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User user;
}
