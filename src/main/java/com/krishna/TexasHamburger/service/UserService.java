package com.krishna.TexasHamburger.service;

import com.krishna.TexasHamburger.Exception.addUserException;
import com.krishna.TexasHamburger.Exception.getUsersException;
import com.krishna.TexasHamburger.model.User;

import java.util.List;

public interface UserService {

    public void addUsers(User user) throws addUserException;
    public List<User> getUsers() throws getUsersException;
//    User getUserById(Long userId);
//    User getUserByUserName(String userName);
}
