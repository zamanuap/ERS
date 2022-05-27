import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { EmployeeReimbursement } from "../../../Components/EmployeeReimbursement";
import { NavbarManager } from "../../../Components/NavbarManager/NavbarManager";
import { IReimbursement } from "../../../Interfaces/IReimbursement";
import { showOtherReimbursement } from "../../../Slices/userSlice";

import { AppDispatch, RootState } from "../../../store";

export const OtherEmployeeReimbursementPage: React.FC = () => {
    const userState = useSelector((state:RootState) => state.user);
    const { username, status } = useParams();
    const dispatch:AppDispatch = useDispatch();
    const searchData = {
        reimburseAuthor: username,
        reimburseStatus: status
    }
    
    useEffect(()=>{
        dispatch(showOtherReimbursement(searchData));
    },[status])

    return (
        <>
          <NavbarManager />
          <div className="reimburse-div">
            <div className="link-div">
                <button className="reimburse-btn"><Link to={`/manager/${username}/Pending`} className="link">Pending Reimbursement</Link></button>
                <button className="reimburse-btn"><Link to={`/manager/${username}/Approved`} className="link">Approved Reimbursement</Link></button>
                <button className="reimburse-btn"><Link to={`/manager/${username}/Denied`} className="link">Denied Reimbursement</Link></button>
                <button className="reimburse-btn"><Link to={"/manager/searchReimbursement"} className="link">Search Reimbursement</Link></button>
            </div>
            <h3 className="status-h3">{ status } Reimbursement for {username}</h3>
            <table className="reimburse-table">
                <thead>
                    <tr>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Author</th>
                    <th>Resolver</th>
                    <th>Submitted Date</th>
                    <th>Resolved Date</th>
                    <th>Status</th>
                    <th>Type</th>
                    {userState.user?.role === "Manager" && status === "Pending" ? <th>Approve/Deny</th> : <></>}
                    </tr>
                </thead>
                <tbody>
                    { userState.reimbursementList && userState.reimbursementList.map(
                        (item:IReimbursement) => 
                        <EmployeeReimbursement {...item} key={item.reimburseId} />)
                        }
                </tbody>
            </table>  
          </div>
        </>)
}