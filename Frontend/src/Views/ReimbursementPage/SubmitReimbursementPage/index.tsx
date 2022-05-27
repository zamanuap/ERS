import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Navbar } from "../../../Components/Navbar/Navbar";
import { SubmitReimbursement } from "../../../Components/SubmitReimbursement";
import { submitReimbursement } from "../../../Slices/userSlice";
import { AppDispatch, RootState } from "../../../store";



export const SubmitReimbursementPage: React.FC = () => {
    const userState = useSelector((state:RootState)=> state.user);
    const navigator = useNavigate();
    const dispatch:AppDispatch = useDispatch();
    
    useEffect(()=>{
        if(!userState.isLogged){
                navigator('/login'); 
        }  
        
    }, [navigator]);
    
    const handleOnSubmit =(reimburseData: any) =>{
        dispatch(submitReimbursement(reimburseData));
        navigator("/employee/reimbursement/submitted");
    }

    return(
        <>
            <Navbar />
            <SubmitReimbursement onSubmitProp = {handleOnSubmit}/>
        </>
    )
}