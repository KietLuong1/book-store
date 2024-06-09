package project.bookstore.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.bookstore.entity.user.User;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void delete(long userId){
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));

        return new CustomUserDetails(user);
    }

    public User getUserById(Long id){
       Optional<User> user = userRepository.findById(id);
       return user.orElse(null);
    }

    public User getUserByEmail(String email){
       Optional<User> user = userRepository.findByEmail(email);
       return user.orElse(null);
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }

    public void updatePassword(String newPassword, String email){
        userRepository.updatePassword(newPassword, email);
    }

    public  void updateResetPasswordToken(String token, String email)  {
        User foundUser = userRepository.findByEmail(email).orElseThrow();

        foundUser.setResetPasswordToken(token);
        userRepository.save(foundUser);
    }

    public User getUserByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token).orElse(null);
    }


}
