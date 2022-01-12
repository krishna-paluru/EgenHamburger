package com.krishna.TexasHamburger.Exception;

public class addUserException extends Exception{
    public addUserException() {
        super("User not added check for the errors!!");
    }

    public addUserException(String message) {
        super(message);
    }
}
