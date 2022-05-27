import React, { useEffect } from 'react';

import {useSelector} from 'react-redux';
import {RootState} from '../../store';

import { useNavigate } from 'react-router-dom';

import { Login } from '../../Components/LoginForm/LoginForm';

import "./LoginPage.css";

export const LoginPage: React.FC = () => {

    const userState = useSelector((state:RootState)=> state.user);

    const navigator = useNavigate();

    useEffect(()=>{
        if(!userState.error && userState.user){
            if(userState.user?.role === "Employee"){
                navigator('/employee/home');
            } else{
                navigator('/manager/home');
            }
        }  
    }, [userState, navigator]);

    return(
        <div className="login-page">
            {userState.error ? <h2 className="login-error">Username or password incorrect</h2> : <></>}
            <Login />
        </div>
    )

}