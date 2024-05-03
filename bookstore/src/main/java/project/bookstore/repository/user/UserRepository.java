package project.bookstore.repository.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import project.bookstore.entity.user.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select  u from  User u where u.email =?1")
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set  u.password =?2 where u.email =?1")
    void updatePassword(String email, String password);
}
