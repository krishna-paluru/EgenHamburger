package com.krishna.TexasHamburger.service.impl;

import com.krishna.TexasHamburger.Exception.ResourceNotFoundException;
import com.krishna.TexasHamburger.model.User;
import com.krishna.TexasHamburger.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user == null)
        {
            throw new UsernameNotFoundException("No userName with the provided username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword()
                ,getGrantedAuthority(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthority(User user)
    {
        Collection<GrantedAuthority> authorises = new ArrayList<>();
        if(user.getRole().equalsIgnoreCase("user"))
        {
            authorises.add(new SimpleGrantedAuthority("User"));
        }
        return authorises;
    }
}
