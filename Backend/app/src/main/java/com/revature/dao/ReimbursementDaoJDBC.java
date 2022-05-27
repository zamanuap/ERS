package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReimbursementDaoJDBC implements IReimbursementDao{
    private ConnectionSingleton cs = ConnectionSingleton.getConnectionSingleton();

    public Reimbursement get(int userId) {
        Connection con = cs.getConnection();
        String sql = "select r.reimburse_id, r.amount, r.submitted_date, r.resolved_date, r.description, u1.user_name, u2.user_name, rs.status, rt.type" +
                " from users u1, users u2, reimbursement r, reimburse_status rs, reimburse_type rt " +
                "where r.reimburse_status = rs.status_id and r.reimburse_type = rt.type_id and r.reimburse_author = u1.user_id " +
                "and r.reimburse_resolver = u2.user_id and r.reimburse_author = ? order by r.reimburse_id desc limit 1";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();

            Reimbursement reimburse = null;
            while (rs.next()){
                reimburse = new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
            }
            return reimburse;
        } catch (Exception e){
            return null;
        }
    }
    @Override
    public List<Reimbursement> get(Reimbursement r) {
        Connection con = cs.getConnection();
        String sql = "select r.reimburse_id, r.amount, r.submitted_date, r.resolved_date, r.description, u1.user_name, u2.user_name, rs.status, rt.type" +
                " from users u1, users u2, reimbursement r, reimburse_status rs, reimburse_type rt " +
                "where r.reimburse_status = rs.status_id and r.reimburse_type = rt.type_id and r.reimburse_author = u1.user_id " +
                "and r.reimburse_resolver = u2.user_id and u1.user_name = ? and rs.status = ?";


        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,r.getReimburseAuthor());
            ps.setString(2, r.getReimburseStatus());
            ResultSet rs = ps.executeQuery();
            List<Reimbursement> reimburseList = new ArrayList<>();

            while (rs.next()){
                Reimbursement reimburse = new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                reimburseList.add(reimburse);
            }
            return reimburseList;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Reimbursement> getAll(int userId, String status) {
        Connection con = cs.getConnection();
        String sql = "select * from reimbursement where reimburse_author = ? and reimburse_status = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            return null;
        } catch( Exception e){
            return null;
        }
    }

    @Override
    public List<Reimbursement> getAll(String status) {
        Connection con = cs.getConnection();

        String sql = "select r.reimburse_id, r.amount, r.submitted_date, r.resolved_date, r.description, u1.user_name, u2.user_name, rs.status, rt.type" +
                " from users u1, users u2, reimbursement r, reimburse_status rs, reimburse_type rt " +
                "where r.reimburse_status = rs.status_id and r.reimburse_type = rt.type_id and r.reimburse_author = u1.user_id " +
                "and r.reimburse_resolver = u2.user_id and rs.status = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,status);
            ResultSet rs = ps.executeQuery();
            List<Reimbursement> reimburseList = new ArrayList<>();
            while (rs.next()){
                Reimbursement reimburse = new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                reimburseList.add(reimburse);
            }
            return reimburseList;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void create(Reimbursement r, int userId) {
        Connection con = cs.getConnection();
        int type = 0;
        switch (r.getReimburseType()){
            case "LODGING": type = 1;
                            break;
            case "TRAVEL": type = 2;
                            break;
            case "FOOD": type = 3;
                            break;
            case "OTHER": type = 4;
                            break;
        }

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "insert into reimbursement (amount, submitted_date, resolved_date, description, reimburse_author, reimburse_resolver, reimburse_status, reimburse_type) values (?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,r.getAmount());
            ps.setDate(2,sqlDate);
            ps.setDate(3,null);
            ps.setString(4,r.getDescription());
            ps.setInt(5,userId);
            ps.setInt(6,8);
            ps.setInt(7,1);
            ps.setInt(8,type);
            ps.execute();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(int resolverId, int reimburseId, String status) {
        Connection con = cs.getConnection();

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());


        int rStatus = 0;
        switch (status){
            case "Pending": rStatus = 1;
                break;
            case "Approved": rStatus = 2;
                break;
            case "Denied": rStatus = 3;
                break;
            }
        String sql = "update reimbursement set reimburse_resolver = ?, reimburse_status = ?, resolved_date = ? where reimburse_id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,resolverId);
            ps.setInt(2,rStatus);
            ps.setDate(3,sqlDate);
            ps.setInt(4,reimburseId);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
