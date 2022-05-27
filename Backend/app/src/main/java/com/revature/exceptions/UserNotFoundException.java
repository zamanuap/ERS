package com.revature.exceptions;

public class UserNotFoundException extends UsernameOrPasswordIncorrectException{
    public UserNotFoundException(){
        super("User not found. No such user exits.");
    }
}
