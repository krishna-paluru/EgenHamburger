package com.krishna.TexasHamburger.controller;
import com.krishna.TexasHamburger.Exception.addUserException;
import com.krishna.TexasHamburger.Exception.getUsersException;
import com.krishna.TexasHamburger.model.User;
import com.krishna.TexasHamburger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/TexasHamburger")
public class  UserController {
    @Autowired
    private UserService userservice;
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user) throws addUserException
    {
        logger.trace("addUser method accessed");
        try{
            userservice.addUsers(user);
        }
        catch(Exception e)
        {
            throw new addUserException(e.getMessage());
        }
        return true;
    }

    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers() throws getUsersException
    {
        logger.trace("getUsers method accessed");
        try{
            return userservice.getUsers();
        }
        catch(Exception e)
        {
            throw new getUsersException(e.getMessage());
        }
    }
}
