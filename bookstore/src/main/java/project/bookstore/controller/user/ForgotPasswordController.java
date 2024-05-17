package project.bookstore.controller.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.bookstore.entity.user.User;
import project.bookstore.service.UserService;
import project.bookstore.utils.Utility;

import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @PostMapping("/forget-password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("forgotEmail");
        String token = RandomStringUtils.randomAlphanumeric(5, 20);

        try {
            userService.updateResetPasswordToken(token, email);

            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;
            sendMail(email, resetPasswordLink);
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "redirect:/client-login";
    }

    @GetMapping("/reset-password")
    public String showForgetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null){
            model.addAttribute("message", "Invalid Token");
            return "redirect:/client-login";
        }

        return "Client/reset-password";
    }

    @PostMapping("/reset-password")
    public String processForgetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("title", "Reset Your Password");

        if (user == null){
            model.addAttribute("message", "Invalid Token");
        }else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newPassword = encoder.encode(password);

            userService.updatePassword(newPassword, user.getEmail());

            model.addAttribute("message","Change password successfully");
        }

        return "/Client/shop-login";
    }

    public void sendMail(String receiver, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        String subject = "[Bookland] Reset Your Password";
        String content = "<p>Hello,</p>"
                + "<br>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\" target=\""+"_blank"+"\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setFrom("contact@bookland.com", "Bookland Support");
        helper.setTo(receiver);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
