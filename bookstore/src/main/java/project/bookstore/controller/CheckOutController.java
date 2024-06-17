package project.bookstore.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.bookstore.entity.Cart;
import project.bookstore.entity.Order;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.User;
import project.bookstore.enums.PaymentMethod;
import project.bookstore.service.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class CheckOutController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/shop-checkout")
    public String getShopCheckout(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // View Cart
        User user = userDetails.getUser();
        List<Cart> carts = cartService.listCart(user);

        float estimatedTotal = 0.0F;

        for (Cart cart : carts) {
            estimatedTotal += cart.getSubtotal();
        }

        // Get Shopping Cart Total Number Of Items
        int numberOfCartItems = cartService.getTotalNumberOfItems(userDetails.getUser());
        model.addAttribute("numberOfCartItems", numberOfCartItems);

        model.addAttribute("carts", carts);
        model.addAttribute("estimatedTotal", estimatedTotal);

        model.addAttribute("cartItems", carts);
        model.addAttribute("order", new Order());
        model.addAttribute("user", user);

        return "Client/shop-checkout";
    }

    @PostMapping("/shop-checkout/processing")
    public String processingCheckout(@ModelAttribute("order") Order order, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        String email= user.getEmail();

        order.setUser(user);
        order.setPaymentMethod(PaymentMethod.COD);
        order.setDeliverDays(3);

        try {
            sendMail(email);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        orderService.save(order);
        cartService.deleteCartItemsByUserId(user.getId());

        return "Client/checkout-success";
    }

    public void sendMail(String receiver) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String subject = "[Bookland] Reset Your Password";
        String content = "<p>Hello,</p>"
                + "<br>"
                + "<p>Thank you so much for your order! We truly appreciate your support and are excited for you to receive your items. " +
                "Your purchase means a lot to us, and we hope you love everything you ordered. " +
                "If you have any questions or need anything at all, please don't hesitate to reach out. We're here to help!</p>"
                + "<br>";

        helper.setFrom("contact@bookland.com", "Bookland Support");
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
