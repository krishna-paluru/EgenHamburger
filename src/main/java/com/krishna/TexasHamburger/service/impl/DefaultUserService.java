package com.krishna.TexasHamburger.service.impl;
import com.krishna.TexasHamburger.Exception.addUserException;
import com.krishna.TexasHamburger.Exception.getUsersException;
import com.krishna.TexasHamburger.model.CustomUser;
import com.krishna.TexasHamburger.model.User;
import com.krishna.TexasHamburger.repository.UserRepository;
import com.krishna.TexasHamburger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DefaultUserService implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public void addUsers(User user) throws addUserException {
        try{
            passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        catch(Exception e)
        {
            throw new addUserException(e.getMessage());
        }
    }
    public List<User> getUsers() throws getUsersException
    {
        try{
            return userRepository.findAll();
        }
        catch(Exception e)
        {
            throw new getUsersException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");

        }
        return new CustomUser(user);


    }
}
