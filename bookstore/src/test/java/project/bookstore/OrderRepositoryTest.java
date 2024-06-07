package project.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import project.bookstore.entity.*;
import project.bookstore.entity.user.User;
import project.bookstore.repository.OrderRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTest {
    @Autowired private OrderRepository order;
    @Autowired private TestEntityManager entityManager;

    @Test
    public void testCreateNewOrderWithSingleProduct() {
        User user = entityManager.find(User.class, 15);
        Book book = entityManager.find(Book.class, 7);

        Order mainOrder = new Order();
        mainOrder.setUser(user);
        mainOrder.setFirstName(user.getFirstName());
        mainOrder.setLastName(user.getLastName());
        mainOrder.setAddress(String.valueOf(user.getAddress()));
        mainOrder.setShippingCost(15);
        mainOrder.setProductCost(book.getSalePrice());
        mainOrder.setTotal(book.getPrice());
        mainOrder.setSubtotal(book.getPrice() + 10);
        mainOrder.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        mainOrder.setOrderStatus(OrderStatus.CANCELLED);
        mainOrder.setOrder_date(LocalDate.now());

        OrderItems orderItems = new OrderItems();
        orderItems.setBook(book);
        orderItems.setOrder(mainOrder);
        orderItems.setProductCost(book.getSalePrice());
        orderItems.setShippingCost(15);
        orderItems.setQuantity(1);
        orderItems.setSubtotal(book.getPrice());
        orderItems.setUnitPrice(book.getPrice());

        mainOrder.getOrderItems().add(orderItems);

        Order savedOrder = order.save(mainOrder);

        assertThat(savedOrder.getId()).isGreaterThan(0);
    }
}
