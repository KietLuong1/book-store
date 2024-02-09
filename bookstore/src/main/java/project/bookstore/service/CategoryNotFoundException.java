package project.bookstore.service;

public class CategoryNotFoundException extends Throwable {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
