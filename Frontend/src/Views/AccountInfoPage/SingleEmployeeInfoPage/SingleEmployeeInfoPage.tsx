import React, { useEffect } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { Navbar } from "../../../Components/Navbar/Navbar";
import { SingleEmployeeInfo } from "../../../Components/SingleEmployeeInfo/SingleEmployeeInfo";
import { IUser } from "../../../Interfaces/IUser";
import { RootState } from "../../../store";

import "./SingleEmployeeInfoPage.css";

export const SingleEmployeeInfoPage : React.FC = () => {
    
    const userState = useSelector((state:RootState) => state.user.user);
    const isLogged = useSelector((state:RootState) => state.user.isLogged);
    
    const navigator = useNavigate();
    useEffect(() =>{
        if(!isLogged){
            navigator("/login");
        }
    },[])
    return(
        <>
            <Navbar />
            <div className="info-container">
            <h3 className="account-info-h3">Employee Account Info</h3>
            { userState !== undefined ? <SingleEmployeeInfo {...userState} /> : <></> }
            <button className="edit-btn">            
                <Link to ={`/employee/${userState?.username}/edit`} 
                className="edit-link">Edit</Link>             
            </button>
            </div>
        </>
    )
}