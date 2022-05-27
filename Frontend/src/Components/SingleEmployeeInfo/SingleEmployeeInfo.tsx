import React from "react";
import { IUser } from "../../Interfaces/IUser";

import "./SingleEmployeeInfo.css";

export const SingleEmployeeInfo: React.FC<IUser> = (user:IUser) => {
    return(
        <div className="show-info-div">
            <h4 className="h4-info">User Id</h4>
            <input className="input-info" readOnly value={user.userId} />
            <h4 className="h4-info">First name</h4>
            <input className="input-info" readOnly value={user.firstName} />
            <h4 className="h4-info">Last name</h4>
            <input className="input-info" readOnly value={user.lastName} />
            <h4 className="h4-info">User name</h4>
            <input className="input-info" readOnly value={user.username} />
            <h4 className="h4-info">Email</h4>
            <input className="input-info" readOnly value={user.email} />
            <h4 className="h4-info">Role</h4>
            <input className="input-info" readOnly value={user.role} />    
        </div>
    )
}