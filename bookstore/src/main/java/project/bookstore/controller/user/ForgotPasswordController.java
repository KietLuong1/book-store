package project.bookstore.controller.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.bookstore.dto.MailBody;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.entity.user.ForgotPassword;
import project.bookstore.entity.user.User;
import project.bookstore.exception.UserNotFoundException;
import project.bookstore.repository.user.ForgotRepository;
import project.bookstore.service.EmailService;
import project.bookstore.service.UserService;
import project.bookstore.utils.ChangePassword;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Controller
public class ForgotPasswordController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    ForgotRepository forgotRepository;


    //    Send email for verification
    @PostMapping("/forget-password")
    public String verifyEmail(@RequestParam("forgotEmail") String email, HttpSession session
                              ) throws UserNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(email);

        if (userDetails == null){
            throw new UserNotFoundException("Invalid email!");
        }
        User user = userDetails.getUser();
        int otp = generateOTP();
        session.setAttribute("forgotEmail", email);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your Forgot Password: " + otp)
                .subject("[Bookland] OTP for Forgot Password")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotRepository.save(forgotPassword);

        return "Client/submit-otp";
    }

//    verify OTP code which send to user's email
    @PostMapping("forget-password/{email}")
    public String verifyOTP(RedirectAttributes ra, @RequestParam("otp") Integer otp, @PathVariable String email) throws UserNotFoundException {
        CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(email);

        if (userDetails == null){
            throw new UserNotFoundException("Invalid email!");
        }
        User user = userDetails.getUser();

        ForgotPassword forgotPassword = forgotRepository.findByOTPAndUser(otp, user)
                .orElseThrow(() -> new RuntimeException("Invalid OTP for email: "+ email));

        if(forgotPassword.getExpirationTime().before(Date.from(Instant.now()))){
            forgotRepository.deleteById(forgotPassword.getId());
            ra.addFlashAttribute("verifiedMessage","OTP has expired!");

            return "Client/change-password";
        }
        ra.addFlashAttribute("verifiedMessage","OTP verified!");

        return "Client/reset-password";
    }

//    change password
    @PostMapping("/change-password/{email}")
    public String changePassword(@RequestBody ChangePassword changePassword,
                                 @PathVariable String email,
                                 RedirectAttributes ra){
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
            ra.addFlashAttribute("passwordMessage", "Please enter password again!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(changePassword.password());

        userService.updatePassword(encodedPassword, email);

        return "redirect:/login";
    }

    private Integer generateOTP() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}