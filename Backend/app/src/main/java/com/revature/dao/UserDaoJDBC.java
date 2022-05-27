package com.revature.dao;

import com.revature.exceptions.DuplicateUsernameException;
import com.revature.exceptions.UsernameOrEmailIncorrectException;
import com.revature.models.User;
import com.revature.utils.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements IUserDao{
    private ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();
    @Override
    public User getUser(String username) {
        Connection con = cs.getConnection();
        String sql = "select u.user_id, u.user_name, u.password, u.first_name, u.last_name, u.email, ur.role " +
                     "from users u, user_roles ur where u.role_id = ur.role_id and user_name = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUser(int userId) {
        Connection con = cs.getConnection();
        String sql = "select u.user_id, u.user_name, u.password, u.first_name, u.last_name, u.email, ur.role " +
                "from users u, user_roles ur where u.role_id = ur.role_id and user_id = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = cs.getConnection();
        String sql = "select u.user_id, u.user_name, u.password, u.first_name, u.last_name, u.email, ur.role " +
        "from users u, user_roles ur where u.role_id = ur.role_id order by u.user_id";
        try{
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<User> userList = new ArrayList<>();;
            while (rs.next()){
               User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
               userList.add(user);
            }
            return userList;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createUser(User u) throws DuplicateUsernameException {
        Connection con = cs.getConnection();
        String sql = "insert into users (user_name, password, first_name, last_name, email, role_id) values(?,?,?,?,?,?)";
        int roleId = u.getRole().equals("Manager") ? 1 : 2;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,u.getUsername());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getFirstName());
            ps.setString(4,u.getLastName());
            ps.setString(5,u.getEmail());
            ps.setInt(6,roleId);
            ps.execute();
        } catch (Exception e){
            throw new DuplicateUsernameException();
        }
    }

    @Override
    public void updateUser(User user) throws UsernameOrEmailIncorrectException {
        Connection con = cs.getConnection();
        String sql = "update users set user_name=?, password=?, first_name=?, last_name=?, email=? where user_id=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getUserId());
            ps.executeUpdate();
        } catch (Exception e){
            throw new UsernameOrEmailIncorrectException();
        }
    }
}
