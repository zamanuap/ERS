import React from "react";
import { Link, useParams } from "react-router-dom";
import { NavbarManager } from "../../Components/NavbarManager/NavbarManager";

import "./ActionStatusShowPage.css";

export const ActionStatusShowPage: React.FC = () => {

    const { status } = useParams();

    return(
        <>
            <NavbarManager />
            <h2 style={{textAlign: "center", marginTop:"40px"}}>Reimbursement  {status}</h2>
            <div style={{display:"flex", flexDirection:"column", alignItems:"center"}}>
            <button className="action-status-btn" style={{width:"15%", marginTop:"30px", padding:"5px"}}>
                <Link style={{textDecoration:"none"}} to="/manager/allReimbursement/Pending">Back to Reimbursement Page</Link>
            </button>
            </div>
        </>
    )
}