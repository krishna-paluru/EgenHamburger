package com.krishna.TexasHamburger.Exception;

public class getUsersException extends Exception{
    public getUsersException() {
        super("Error in getting the Users List");
    }

    public getUsersException(String message) {
        super(message);
    }
}
