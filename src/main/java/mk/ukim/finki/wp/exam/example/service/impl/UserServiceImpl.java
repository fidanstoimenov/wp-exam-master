package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.wp.exam.example.repository.UserRepository;
import mk.ukim.finki.wp.exam.example.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//cekor 9 anotirame deka se raboti za service i implements od userservice
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    //cekor 9 - potrebno e repository za da go snimime korisnikot
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //cekor 10 - vo UserRepository
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //cekor 11 da se vkluci password encoder - od tuka se odi vo aplikacijata (ExampleApplication.java u web)
    //cekor 13 se vrakjame serviceimpl i se implementira slednoto
    // so klik na klasata User moze da se vidat konstrukotirte username pass i role
    @Override
    public User create(String username, String password, Role role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        User user = new User(username,encryptedPassword,role);
        return this.userRepository.save(user);
    }
    //cekor 14 - odime vo sledno serviceimpl categoryserviceimpl

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(InvalidUsernameException::new);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList())

        );

        return userDetails;
    }
}
