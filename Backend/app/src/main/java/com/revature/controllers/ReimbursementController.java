package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReimbursementController {

    private ReimbursementService rs;
    private ObjectMapper om;
    public ReimbursementController(ReimbursementService rs){
        this.rs = rs;
        this.om = new ObjectMapper();
    }
    public Handler handleSubmit = ctx -> {

        Reimbursement r = om.readValue(ctx.body(), Reimbursement.class);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        om.setDateFormat(df);

        if(ctx.req.getSession().getAttribute("userId") == null){
            ctx.result("You must login first.");
        } else if(r.getAmount()<0) {
            ctx.result("Enter positive value");
        }
        else {
            int userId = (int)ctx.req.getSession().getAttribute("userId");
            Reimbursement reimburse = rs.submitReimburse(r,userId);
            ctx.result(om.writeValueAsString(reimburse));
        }
    };
    public Handler handleSingleUserReimbursement = ctx -> {
        Reimbursement reimburse = om.readValue(ctx.body(), Reimbursement.class);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        om.setDateFormat(df);
        if(ctx.req.getSession().getAttribute("userId") == null){
            ctx.result("User must login.");
        } else {
            String username = (String) ctx.req.getSession().getAttribute("username");
            reimburse.setReimburseAuthor(username);
            List<Reimbursement> rList = rs.getSingleUserReimbursement(reimburse);
            ctx.result(om.writeValueAsString(rList));
        }
    };

    public Handler handleManagerSingleUserReimbursement = ctx -> {
        Reimbursement reimburse = om.readValue(ctx.body(), Reimbursement.class);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        om.setDateFormat(df);
        String role = (String) ctx.req.getSession().getAttribute("role");

        if(!role.equals("Manager")){
            ctx.result("Only Manager can see others request.");
            System.out.println("Error");
        } else {
            List<Reimbursement> rList = rs.getSingleUserReimbursement(reimburse);
            ctx.result(om.writeValueAsString(rList));
        }
    };
    public Handler handleAllUsersReimbursement = ctx -> {
        Map<String,String> map = om.readValue(ctx.body(), Map.class);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        om.setDateFormat(df);
        String role = (String) ctx.req.getSession().getAttribute("role");
        if(ctx.req.getSession().getAttribute("userId") == null){
            ctx.result("User must login.");
        } else if(!role.equals("Manager")){
            ctx.result("Only manager can see different users reimbursement request.");
        } else {
            List<Reimbursement> rList = rs.getAllUsersReimbursement(map.get("reimburseStatus"));
            ctx.result(om.writeValueAsString(rList));
        }
    };
    public Handler handleUpdateRequest = ctx -> {
        Reimbursement r = om.readValue(ctx.body(), Reimbursement.class);
        String role = (String) ctx.req.getSession().getAttribute("role");
        if(ctx.req.getSession().getAttribute("userId") == null){
            ctx.result("User must login.");
        } else if(!role.equals("Manager")){
            ctx.result("Only manager can see different users reimbursement request.");
        } else {
            int resolverId = (int)ctx.req.getSession().getAttribute("userId");
            rs.updateRequest(resolverId, r.getReimburseId(), r.getReimburseStatus());
            ctx.result("Updated successfully.");
        }
    };
}
