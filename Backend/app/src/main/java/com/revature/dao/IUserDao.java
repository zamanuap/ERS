package com.revature.dao;

import com.revature.exceptions.DuplicateUsernameException;
import com.revature.exceptions.UsernameOrEmailIncorrectException;
import com.revature.models.User;

import java.util.List;

public interface IUserDao {
    User getUser(String username);
    User getUser(int userId);
    List<User> getAllUsers();
    void createUser(User user) throws DuplicateUsernameException;
    void updateUser(User user) throws UsernameOrEmailIncorrectException;
}
