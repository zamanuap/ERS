package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.IUserDao;
import com.revature.exceptions.DuplicateUsernameException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameOrEmailIncorrectException;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;

import java.util.List;

public class UserController {
    private UserService us;
    private ObjectMapper om;
    public UserController(UserService us){
        this.us = us;
        this.om = new ObjectMapper();
    }
    public Handler handleRegister = ctx -> {
        User user = om.readValue(ctx.body(), User.class);
        try{
            us.registerUser(user);
            ctx.result("User created with user name: " + user.getUsername());
        } catch (DuplicateUsernameException e){
            throw e;
        }
    };
    public Handler handleLogin = ctx -> {
        User u = om.readValue(ctx.body(), User.class);
        try {
            User user = us.loginUser(u);
            ctx.req.getSession().setAttribute("userId", user.getUserId());
            ctx.req.getSession().setAttribute("username", user.getUsername());
            ctx.req.getSession().setAttribute("role", user.getRole());
            System.out.println(ctx.req.getSession().getAttribute("userId"));
            ctx.result(om.writeValueAsString(user));
        } catch (UserNotFoundException e){
            throw e;
        } catch (PasswordIncorrectException e){
            throw e;
        }
    };
    public Handler handleLogout = ctx -> {
        if(ctx.req.getSession().getAttribute("username") == null){
            ctx.result("Nothing to log out. No user is logged in");
        } else {
            ctx.req.getSession().invalidate();
            ctx.result("Logout successful.");
        }
    };
    public Handler handleAccountInfo = ctx -> {
        String user_name = ctx.pathParam("username");
        String username = (String) ctx.req.getSession().getAttribute("username");
        if(!username.equals(user_name)){
            ctx.status(401);
        }else {
            User user = us.getAccountInfo(username);
            ctx.result(om.writeValueAsString(user));
        }
    };
    public Handler handleAllAccountInfo = ctx -> {
        String username = (String) ctx.req.getSession().getAttribute("username");
        String role = (String) ctx.req.getSession().getAttribute("role");
        if(username == null){
            ctx.result("You are not logged in");
        } else if(!role.equals("Manager")){
            ctx.result("Only manager can see all account info");
        } else {
            List<User> userList = us.getAllAccountInfo();
            ctx.result(om.writeValueAsString(userList));
        }
    };
    public Handler handleUpdateUserInfo = ctx -> {
        User user = om.readValue(ctx.body(), User.class);
        int userId = (int) ctx.req.getSession().getAttribute("userId");
        String role = (String) ctx.req.getSession().getAttribute("role");
        user.setUserId(userId);
        try {
            User updatedUser = us.updateUserInfo(user);
            if(!((String)ctx.req.getSession().getAttribute("username")).equals(updatedUser.getUsername())){
                ctx.req.getSession().setAttribute("username", updatedUser.getUsername());
            }
            ctx.result(om.writeValueAsString(updatedUser));
        } catch (UsernameOrEmailIncorrectException e){
            throw e;
        }
    };
}
