package com.revature;

import com.revature.exceptions.DuplicateUsernameException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameOrEmailIncorrectException;
import com.revature.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.models.User;
import org.junit.Before;
import org.junit.Test;
import com.revature.dao.*;
import com.revature.services.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Before
    public void setupBeforeMethods(){
        MockitoAnnotations.openMocks(this);
    }


    @Mock
    static IUserDao ud;

    @InjectMocks
    static UserService us;

    // Register

    @Test
    public void registerCreateNewEmptyUserTest() throws DuplicateUsernameException {
        // Since the registerUser is void method, it doesn't return anything
        User user1= new User();
        us.registerUser(user1);

        verify(ud).createUser((any()));
    }

    @Test
    public void registerCreateNewUserTest() throws DuplicateUsernameException {
        // Since the registerUser is void method, it doesn't return anything
        User user1= new User(3, "username1", "password", "firstName", "lastName", "email", "Manager");
        us.registerUser(user1);

        verify(ud).createUser((any()));
    }


    @Test
    public void validLoginCredentialsTest() throws UsernameOrPasswordIncorrectException {
        User user1 = new User(1,"username", "password", "firstName", "lastName", "test@email.com", "Employee");
        when(ud.getUser("username")).thenReturn(user1);

        User loggedInUser = us.loginUser(user1);
        verify(ud).getUser("username");

        assertEquals(user1, loggedInUser);

    }

    @Test(expected=UsernameOrPasswordIncorrectException.class)
    public void wrongUsernameOrIncorrectPasswordTest() throws UsernameOrPasswordIncorrectException {
        User user1 = new User(1,"Username", "password", "firstName", "lastName", "test@email.com", "Employee");
        User wrongUser1 = new User(1,"Username", "password123", "firstName", "lastName", "test1@email.com", "Manager");

        when(ud.getUser(wrongUser1.getUsername())).thenReturn(user1);
        us.loginUser(wrongUser1);
    }

    @Test(expected=UserNotFoundException.class)
    public void noUsernameFoundTest() throws UsernameOrPasswordIncorrectException {
        User wrongUser1 = new User(1,"Username123", "password123", "firstName", "lastName", "test1@email.com", "Manager");

        when(ud.getUser(wrongUser1.getUsername())).thenReturn(null);
        us.loginUser(wrongUser1);
    }

    //
    @Test
    public void getUserInfoTest(){
        User expectedUser = new User(3,"Username", "password", "firstName", "lastName", "test@email.com", "Employee");

        when(ud.getUser("Username")).thenReturn(expectedUser);

        User getUserInfo = us.getAccountInfo("Username");
        verify(ud).getUser(any());
        assertEquals(expectedUser, getUserInfo);
    }

    // Method to update account information
    @Test
    public void updateUserInfoTest() throws UsernameOrEmailIncorrectException {
        User oddUserInfo = new User(121,"Username", "password", "firstName", "lastName", "test@email.com", "Employee");
        User updateUserInfo = new User(121, "Username", "passwordNew", "firstNameNew", "lastNameNew", "test@email.com", "Employee");

        when(ud.getUser(oddUserInfo.getUserId())).thenReturn(updateUserInfo);
        User actualNewUser = us.updateUserInfo(updateUserInfo);
        verify(ud).updateUser(updateUserInfo);

        assertEquals(updateUserInfo, actualNewUser);
    }



    // Method to view all employees
    @Test
    public void viewAllEmployeesTest(){
        List<User> employeeList = new ArrayList<>();
        User user1 = new User(1,"Username", "password", "firstName", "lastName", "test@email.com", "Manager");
        User user2 = new User(2,"Username2", "password2", "firstName2", "lastName2", "test2@email.com", "Employee");
        User user3 = new User(3,"Username3", "password3", "firstName3", "lastName3", "test3@email.com", "Employee");
        employeeList.add(user1);
        employeeList.add(user2);
        employeeList.add(user3);
        when(ud.getAllUsers()).thenReturn(employeeList);
        List<User> actualList = us.getAllAccountInfo();
        verify(ud).getAllUsers();
        assertEquals(employeeList, actualList);

    }

    @Test
    public void viewAllEmployeesEmptyListTest(){
        List<User> employeeList = new ArrayList<>();

        when(ud.getAllUsers()).thenReturn(employeeList);
        List<User> actualList = us.getAllAccountInfo();
        verify(ud).getAllUsers();
        assertEquals(employeeList, actualList);

    }



}