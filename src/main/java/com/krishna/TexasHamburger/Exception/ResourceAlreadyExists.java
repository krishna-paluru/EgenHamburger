package com.krishna.TexasHamburger.Exception;

public class ResourceAlreadyExists extends Exception{
    public ResourceAlreadyExists()
    {
        super("Resource Already Exists");
    }
    public ResourceAlreadyExists(String message)
    {
        super(message);
    }
}
