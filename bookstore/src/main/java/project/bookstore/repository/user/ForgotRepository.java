package project.bookstore.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.bookstore.entity.user.ForgotPassword;
import project.bookstore.entity.user.User;

import java.util.Optional;

public interface ForgotRepository extends JpaRepository<ForgotPassword, Integer> {

    @Query("select fp from ForgotPassword fp where fp.otp =?1 and fp.user =?2")
    Optional<ForgotPassword> findByOTPAndUser(Integer otp, User user);
}
