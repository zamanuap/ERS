package com.revature;

import com.revature.dao.*;
import com.revature.services.*;
import com.revature.models.*;
import static org.junit.Assert.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {

    // Need to add this setup before testing
    @Before
    public void setupBeforeMethods(){
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    static IReimbursementDao rd;

    @InjectMocks
    static ReimbursementService rs;

    @Test
    public void submitReimbursementRequestTest(){

        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement reimbursement1 = new Reimbursement(3, 1122.4, today, null, "Travelling to Japan", "Eric", "", "Pending", "TRAVEL");
        int userId1 = 41;

        when(rd.get(userId1)).thenReturn(reimbursement1);
        Reimbursement actualList = rs.submitReimburse(reimbursement1, userId1);
        verify(rd).create(reimbursement1, userId1);
        Reimbursement expectedList = reimbursement1;
        assertEquals(actualList, expectedList);

    }

    @Test
    public void viewPendingReimbursementTest(){
        List<Reimbursement> viewPendingList = new ArrayList<>();
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement pending1 = new Reimbursement(3, 1122.4, today, null, "Travelling to Japan", "Eric", "", "Pending", "TRAVEL");
        Reimbursement pending2 = new Reimbursement(41, 132.4, today, null, "Other thing during travelling", "Eric", "", "Pending", "OTHER");

        viewPendingList.add(pending1);
        viewPendingList.add(pending2);
        when(rd.get(pending1)).thenReturn(viewPendingList);
        List<Reimbursement> expectedList =  viewPendingList;
        List<Reimbursement> actualList = rs.getSingleUserReimbursement(pending1);
        verify(rd).get(pending1);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void viewPendingReimbursementEmptyTest(){
        List<Reimbursement> viewPendingList = new ArrayList<>();
        Reimbursement empty = new Reimbursement();
        when(rd.get(empty)).thenReturn(viewPendingList);
        List<Reimbursement> expectedList =  viewPendingList;
        List<Reimbursement> actualList = rs.getSingleUserReimbursement(empty);
        verify(rd).get(empty);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void viewResolvedReimbursementTest(){

        List<Reimbursement> viewResolvedList = new ArrayList<>();
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement resolved1 = new Reimbursement(1, 300, today, today, "travelling", "Victor", "Manger",  "Resolved", "TRAVEL");
        Reimbursement resolved2 = new Reimbursement(2, 4130, today, today, "Other thing during travelling", "Victor", "Manager", "Resolved", "OTHER");
        viewResolvedList.add(resolved1);
        viewResolvedList.add(resolved2);
        when(rd.get(resolved1)).thenReturn(viewResolvedList);
        List<Reimbursement> expectedList =  viewResolvedList;
        List<Reimbursement> actualList = rs.getSingleUserReimbursement(resolved1);
        verify(rd).get(resolved1);
        assertEquals(actualList, expectedList);



    }
    @Test
    public void viewResolvedReimbursementEmptyTest(){

        List<Reimbursement> viewResolvedList = new ArrayList<>();
        Reimbursement empty = new Reimbursement();
        when(rd.get(empty)).thenReturn(viewResolvedList);
        List<Reimbursement> expectedList =  viewResolvedList;
        List<Reimbursement> actualList = rs.getSingleUserReimbursement(empty);
        verify(rd).get(empty);
        assertEquals(actualList, expectedList);
    }
    @Test
    public void approveReimbursementTest(){
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement pending1 = new Reimbursement(3, 1122.4, today, null, "Travelling to Japan", "Eric", "", "Pending", "TRAVEL");
        User user1 = new User(1,"Username", "password", "firstName", "lastName", "test@email.com", "Manager");
        rs.updateRequest(user1.getUserId(), pending1.getReimburseId(), "Accept");
        verify(rd).update(user1.getUserId(), pending1.getReimburseId(), "Accept");

    }

    @Test
    public void denyReimbursementTest(){
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement pending1 = new Reimbursement(24, 1122.4, today, null, "Travelling to Japan", "Eric", "", "Pending", "TRAVEL");
        User user1 = new User(1,"Username", "password", "firstName", "lastName", "test@email.com", "Manager");
        rs.updateRequest(user1.getUserId(), pending1.getReimburseId(), "Deny");
        verify(rd).update(user1.getUserId(), pending1.getReimburseId(), "Deny");
    }


    @Test
    public void viewAllPendingReimbursementByManagerTest(){
        List<Reimbursement> viewPendingList = new ArrayList<>();
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement pending1 = new Reimbursement(3, 1122.4, today, null, "Travelling to Japan", "Eric", "", "Pending", "TRAVEL");
        Reimbursement pending2 = new Reimbursement(4, 132.4, today, null, "Other thing during travelling", "Eric", "", "Pending", "OTHER");
        Reimbursement pending3 = new Reimbursement(7, 122.4, today, null, "Travelling to U.S.", "Tony", "", "Pending", "TRAVEL");
        Reimbursement pending4 = new Reimbursement(9, 512.4, today, null, "Food", "May", "", "Pending", "FOOD");


        viewPendingList.add(pending1);
        viewPendingList.add(pending2);
        viewPendingList.add(pending3);
        viewPendingList.add(pending4);

        when(rd.getAll("Pending")).thenReturn(viewPendingList);
        List<Reimbursement> expectedList =  viewPendingList;
        List<Reimbursement> actualList = rs.getAllUsersReimbursement("Pending");
        verify(rd).getAll("Pending");
        assertEquals(actualList, expectedList);
    }

    @Test
    public void viewAllResolvedReimbursementByManagerTest(){

        List<Reimbursement> viewResolvedList = new ArrayList<>();
        Date today = new Date(Instant.now().toEpochMilli());
        Reimbursement resolved1 = new Reimbursement(1, 300, today, today, "travelling", "Victor", "Manger",  "Accept", "TRAVEL");
        Reimbursement resolved2 = new Reimbursement(2, 4130, today, today, "Other thing during travelling", "Victor", "Manager", "Accept", "OTHER");

        viewResolvedList.add(resolved1);
        viewResolvedList.add(resolved2);

        when(rd.getAll("Accept")).thenReturn(viewResolvedList);
        List<Reimbursement> expectedList =  viewResolvedList;
        List<Reimbursement> actualList = rs.getAllUsersReimbursement("Accept");
        verify(rd).getAll("Accept");
        assertEquals(actualList, expectedList);
    }
}