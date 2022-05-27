package com.revature.exceptions;

public class PasswordIncorrectException extends UsernameOrPasswordIncorrectException{
    public PasswordIncorrectException(){
        super("Password incorrect.");
    }
}
