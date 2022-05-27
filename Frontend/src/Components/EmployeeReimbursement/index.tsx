import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { IReimbursement } from "../../Interfaces/IReimbursement";
import { RootState } from "../../store";


export const EmployeeReimbursement: React.FC<IReimbursement> = (reimbursement) => {
    const user = useSelector((state:RootState) => state.user.user);
    return(
        <>
            <tr>
                <td>$ {reimbursement.amount}</td>
                <td>{reimbursement.description}</td>
                <td>{reimbursement.reimburseAuthor}</td>
                <td>{reimbursement.reimburseResolver}</td>
                <td>{reimbursement.submittedDate}</td>
                <td>{reimbursement.resolvedDate}</td>
                <td>{reimbursement.reimburseStatus}</td>
                <td>{reimbursement.reimburseType}</td>
                { user?.role === "Manager" && reimbursement.reimburseStatus === "Pending" ? 
                <td><Link to={`/manager/actionReimbursement/${reimbursement.reimburseId}`}>Acition</Link></td> : <></>
                }
            </tr>
        </>
    )
}