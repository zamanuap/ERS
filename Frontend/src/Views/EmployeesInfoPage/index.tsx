import userEvent from "@testing-library/user-event";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { EmployeesInfo } from "../../Components/EmployeesInfo";
import { NavbarManager } from "../../Components/NavbarManager/NavbarManager";
import { IUser } from "../../Interfaces/IUser";
import { allUserInfo } from "../../Slices/userSlice";
import { AppDispatch, RootState } from "../../store";

import "./EmployeesInfoPage.css";

export const EmployeesInfoPage = () =>{

    const userList = useSelector((state:RootState)=> state.user.userList);
    const dispatch:AppDispatch = useDispatch();

    useEffect(()=>{
        dispatch(allUserInfo());
    },[]);
    
    return(
        <div>
            <NavbarManager />
            <div className="info-div">
                <h3 className="all-employees-heading-h3">All Employees Account Info</h3>
                <table className="table-display">
                <thead>
                    <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    { userList && userList.map(
                        (item:IUser) => 
                        <EmployeesInfo {...item} key={item.userId} />)
                        }
                </tbody>
                </table>
            </div>
        </div>
    )
}