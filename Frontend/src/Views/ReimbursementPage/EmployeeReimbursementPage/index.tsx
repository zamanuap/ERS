import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";

import { EmployeeReimbursement } from "../../../Components/EmployeeReimbursement";
import { Navbar } from "../../../Components/Navbar/Navbar";
import { IReimbursement } from "../../../Interfaces/IReimbursement";
import { pendingReimbursement } from "../../../Slices/userSlice";
import { AppDispatch, RootState } from "../../../store";

import "./EmployeeReimbursementPage.css";

export const EmployeeReimbursementPage: React.FC = () => {
    const reimbursementList = useSelector((state:RootState) => state.user.reimbursementList );
    const dispatch:AppDispatch = useDispatch();
    const { status } = useParams();
    const reimburseData = {
        reimburseStatus: status
    }
    
    useEffect(()=>{
        
            dispatch(pendingReimbursement(reimburseData)); 
          
    },[]);

    return (
        <>
          <Navbar />
          <div className="reimburse-div">
            <h3 className="status-h3">{ status } Reimbursement</h3>
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
                    </tr>
                </thead>
                <tbody>
                    { reimbursementList && reimbursementList.map(
                        (item:IReimbursement) => 
                        <EmployeeReimbursement {...item} key={item.reimburseId} />)
                        }
                    
                </tbody>
            </table>  
          </div>
        </>)
}