package com.revature.models;

import java.util.Date;

public class Reimbursement {
    private int reimburseId;
    private double amount;
    private Date submittedDate;
    private Date resolvedDate;
    private String description;
    private String reimburseAuthor;
    private String reimburseResolver;
    private String reimburseStatus;
    private String reimburseType;

    public Reimbursement(){

    }
    public Reimbursement(int reimburseId, double amount, Date submittedDate, Date resolvedDate, String description, String reimburseAuthor, String reimburseResolver, String reimburseStatus, String reimburseType){
        this.reimburseId = reimburseId;
        this.amount = amount;
        this.submittedDate = submittedDate;
        this.resolvedDate = resolvedDate;
        this.description = description;
        this.reimburseAuthor = reimburseAuthor;
        this.reimburseResolver = reimburseResolver;
        this.reimburseStatus = reimburseStatus;
        this.reimburseType = reimburseType;
    }
    public int getReimburseId() {
        return reimburseId;
    }

    public void setReimburseId(int reimburseId) {
        this.reimburseId = reimburseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReimburseAuthor() {
        return reimburseAuthor;
    }

    public void setReimburseAuthor(String reimburseAuthor) {
        this.reimburseAuthor = reimburseAuthor;
    }

    public String getReimburseResolver() {
        return reimburseResolver;
    }

    public void setReimburseResolver(String reimburseResolver) {
        this.reimburseResolver = reimburseResolver;
    }

    public String getReimburseStatus() {
        return reimburseStatus;
    }

    public void setReimburseStatus(String reimburseStatus) {
        this.reimburseStatus = reimburseStatus;
    }

    public String getReimburseType() {
        return reimburseType;
    }

    public void setReimburseType(String reimburseType) {
        this.reimburseType = reimburseType;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimburseId=" + reimburseId +
                ", amount=" + amount +
                ", submittedDate=" + submittedDate +
                ", resolvedDate=" + resolvedDate +
                ", description='" + description + '\'' +
                ", reimburseAuthor='" + reimburseAuthor + '\'' +
                ", reimburseResolver='" + reimburseResolver + '\'' +
                ", reimburseStatus='" + reimburseStatus + '\'' +
                ", reimburseType='" + reimburseType + '\'' +
                '}';
    }
}
