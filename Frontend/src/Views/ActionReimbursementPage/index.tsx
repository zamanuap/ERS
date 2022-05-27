import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { NavbarManager } from "../../Components/NavbarManager/NavbarManager";
import { actionReimbursement } from "../../Slices/userSlice";
import { AppDispatch, RootState } from "../../store";

import "./ActionReimbursementPage.css";

export const ActionReimbursementPage: React.FC = () =>{
    const reimbursementList = useSelector((state:RootState) => state.user.reimbursementList);
    const dispatch:AppDispatch = useDispatch();
    const { id } = useParams();
    const navigator = useNavigate();
    const reimburse = reimbursementList?.filter(item => id ? item.reimburseId == parseInt(id) : false)

    const handleClickApproved = () => {
        let data = {};
        if(reimburse){
            data = {
                reimburseId: reimburse[0].reimburseId,
                reimburseStatus : "Approved"
            }
        }
        dispatch(actionReimbursement(data));
        navigator("/manager/actionStatus/Approved");
    }

    const handleClickDenied = () => {
        let data = {};
        if(reimburse){
            data = {
                reimburseId: reimburse[0].reimburseId,
                reimburseStatus : "Denied"
            }
        }
        dispatch(actionReimbursement(data));
        navigator("/manager/actionStatus/Denied");
    }

    return(
        <>
            <NavbarManager />
            <div className="action-div-container">
                <div className="show-info-div">
                    { reimburse &&
                    <>
                        <h4 className="h4-info">Employee username</h4>
                        <input className="input-info" readOnly value={reimburse[0].reimburseAuthor} />
                        <h4 className="h4-info">Amount</h4>
                        <input className="input-info" readOnly value={reimburse[0].amount} />
                        <h4 className="h4-info">Description</h4>
                        <input className="input-info" readOnly value={reimburse[0].description} />
                        <h4 className="h4-info">Submitted date</h4>
                        <input className="input-info" readOnly value={reimburse[0].submittedDate} />
                        <h4 className="h4-info">Status</h4>
                        <input className="input-info" readOnly value={reimburse[0].reimburseStatus} />
                        <h4 className="h4-info">Type</h4>
                        <input className="input-info" readOnly value={reimburse[0].reimburseType} />  
                        <div className="link-div">
                            <input type="button" className="reimburse-btn" value="Approved" onClick={handleClickApproved} />
                            <input type="button" className="reimburse-btn" value="Denied" onClick={handleClickDenied} />
                        </div> 
                    </>
                    } 
                </div>
            </div>
        </>
    )
}