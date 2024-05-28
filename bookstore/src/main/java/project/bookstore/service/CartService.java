package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Book;
import project.bookstore.entity.Cart;
import project.bookstore.entity.user.User;
import project.bookstore.repository.BookRepository;
import project.bookstore.repository.CartRepository;
import project.bookstore.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

//    public List<Cart> findByUser(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        return cartRepository.findByUser(user);
//    }

    public Integer addBookToCart(Integer bookId, Integer quantity, User user) {
        Integer updateQuantity = quantity;
//        Book book = new Book(bookId);
//
//        Cart cart = cartRepository.findByUserandBook(user, book);
//
//        if (cart != null) {
//            updateQuantity = cart.getQuantity() + quantity;
//        } else {
//            cart = new Cart();
//            cart.setUser(user);
//            cart.setBook(book);
//        }
//
//        cart.setQuantity(updateQuantity);
//
//        cartRepository.save(cart);

        return updateQuantity;

//        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//        Cart cart = new Cart();
//        cart.setBook(book);
//        cart.setUser(user);
//        cart.setQuantity(1); // Default quantity for simplicity

//        cartRepository.save(cart);
    }

    public void removeProductFromCart(int cartId, Long userId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        if (cart.getUser().getId().equals(userId)) {
            cartRepository.deleteById(cartId);
        }
    }

    //
    private HashMap<Integer, Integer> carts = new HashMap<>();

    public void addNewItems(int book_id) {
        carts.put(book_id, carts.getOrDefault(book_id, 0) + 1);
    }

    public Set<Integer> getAllCartItems() {
        return carts.keySet();
    }

    public int getNumberOfItems() {
        return carts.size();
    }

    public int getQuantityOfItem(int book_id) {
        return carts.get(book_id);
    }

    public void deleteItemsById(int book_id) {
        carts.remove(book_id);
    }

    public void increaseQuantity(int book_id) {
        carts.put(book_id, carts.get(book_id) + 1);
    }

    public void decreaseQuantity(int book_id) {
        carts.put(book_id, carts.get(book_id) - 1);
    }

}
