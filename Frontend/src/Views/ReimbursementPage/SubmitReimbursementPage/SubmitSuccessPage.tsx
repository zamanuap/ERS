import React from "react";
import { Link } from "react-router-dom";
import { Navbar } from "../../../Components/Navbar/Navbar";

import "./SubmitSuccessPage.css";

export const SubmitSuccessPage: React.FC = () => {
    return (
        <>
            <Navbar />
            <div className="success-div">
            <h2 className="success-h2">Reimbursement submitted successfully</h2>
            <button className="success-btn">
                <Link className="success-link" to="/employee/reimbursement">Back to Reimbursement Page</Link>
            </button>
            </div>
        </>
    )
}