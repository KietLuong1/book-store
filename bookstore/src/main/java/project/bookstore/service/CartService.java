package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.repository.CartRepository;

import java.util.HashMap;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

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
