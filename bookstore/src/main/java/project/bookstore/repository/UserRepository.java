package project.bookstore.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.bookstore.entity.user.User;

import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select  u from  User u where u.email =?1")
    Optional<User> findByEmail(String email);

    @Query("select  u from  User u where u.resetPasswordToken = ?1")
    Optional<User> findByResetPasswordToken(String token);

    @Transactional
    @Modifying
    @Query("update User u set  u.password =?1, u.resetPasswordToken = null where u.email =?2")
    void updatePassword(String password, String email);

    @Query("select u from User u join fetch u.orders where u.id=(:id)")
    void fetchLazy();
}
