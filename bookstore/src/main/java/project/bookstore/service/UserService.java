package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.bookstore.entity.user.User;
import project.bookstore.entity.user.CustomUserDetails;
import project.bookstore.repository.user.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

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

    public void save(User user){
        userRepository.save(user);
    }

    public void updatePassword(String password, String email){
        userRepository.updatePassword(password, email);
    }
}