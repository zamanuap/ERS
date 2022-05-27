import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Navbar } from "../../Components/Navbar/Navbar";
import { AccountUpdate } from "../../Components/AccountUpdate/AccountUpdate";
import { AppDispatch, RootState } from "../../store";

import "./AccountUpdatePage.css";
import { updateStatus } from "../../Slices/userSlice";

export const AccountUpdatePage: React.FC = () =>{

    const userState = useSelector((state:RootState) => state.user.user);
    const isUpdatefailed = useSelector((state:RootState) => state.user.isUpdatefailed);
    const isUpdated = useSelector((state:RootState) => state.user.isUpdated);
    const navigator = useNavigate();
    const dispatch:AppDispatch = useDispatch();
    console.log(isUpdatefailed);
    useEffect(() => {
               
        if(isUpdated){
            dispatch(updateStatus());
            navigator(`/employee/${userState?.username}`);    
        }       
    },[userState, isUpdatefailed]);

    console.log(isUpdatefailed);
    
    return(
        <>
            <Navbar />
            <AccountUpdate />
        </>
    )
}