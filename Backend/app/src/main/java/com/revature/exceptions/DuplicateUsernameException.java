package com.revature.exceptions;

public class DuplicateUsernameException extends Exception{
    public DuplicateUsernameException(){
        super("Duplicate user name. Try another.");
    }
}

