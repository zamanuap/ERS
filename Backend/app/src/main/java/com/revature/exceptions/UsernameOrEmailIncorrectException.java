package com.revature.exceptions;

public class UsernameOrEmailIncorrectException extends Exception{
    public UsernameOrEmailIncorrectException(){
        super("Username or email already exists.");
    }

}
