import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { IUser } from "../../Interfaces/IUser";
import { updateUser } from "../../Slices/userSlice";
import { AppDispatch, RootState } from "../../store";

import "./AccountUpdate.css";


export const AccountUpdate: React.FC = () => {

    const userState = useSelector((state:RootState) => state.user.user);
    const [firstName,setFirstName] = useState(userState?.firstName);
    const [lastName,setLastName] = useState(userState?.lastName);
    const [userName,setUserName] = useState(userState?.username);
    const [password,setPassword] = useState(userState?.password);
    const [email,setEmail] = useState(userState?.email);
    const dispatch:AppDispatch = useDispatch();


    const handleChange = (e:React.ChangeEvent<HTMLInputElement>) =>{
        if(e.target.name === "firstName"){
            setFirstName(e.target.value);
        }else if(e.target.name === "lastName"){
            setLastName(e.target.value);
        }else if(e.target.name === "userName"){
            setUserName(e.target.value);
        }else if(e.target.name === "password"){
            setPassword(e.target.value);
        }else {
            setEmail(e.target.value);
        }
    }

    const handleClick = () =>{
        let user:IUser = {
            firstName: firstName ? firstName : "",
            lastName: lastName ? lastName : "",
            username: userName ? userName : "",
            password: password ? password : "",
            email: email ? email : ""
        }
        dispatch(updateUser(user));            
    }

    return(
        <div className="info-container">
                <div className="show-info-div">
                    <h3 className="h3-update-heading">Update Account Info</h3>
                    <h4 className="h4-info">First name</h4>
                    <input className="input-info" name="firstName" value={firstName} onChange={handleChange}/>
                    <h4 className="h4-info">Last name</h4>
                    <input className="input-info" name="lastName" value={lastName} onChange={handleChange}/>
                    <h4 className="h4-info">User name</h4>
                    <input className="input-info" name="userName" value={userName} onChange={handleChange}/>
                    <h4 className="h4-info">Password</h4>
                    <input className="input-info" type="password" name="password" value={password} onChange={handleChange}/>
                    <h4 className="h4-info">Email</h4>
                    <input className="input-info" name="email" value={email} onChange={handleChange}/>  
                </div>
                <button className="update-btn" onClick={handleClick}>Update</button>
            </div>
    )
}