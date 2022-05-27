import React from "react";
import { IUser } from "../../Interfaces/IUser";
import { NavbarManager } from "../NavbarManager/NavbarManager";

export const EmployeesInfo: React.FC<IUser> = (user)=> {
    return(
        <>
            <tr>
                <td>{user.userId}</td>
                <td>{user.firstName}</td>
                <td>{user.lastName}</td>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>{user.role}</td>
            </tr>
        </>
    )
}