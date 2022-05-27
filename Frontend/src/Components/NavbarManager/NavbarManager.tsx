import React from 'react';

import {Link} from 'react-router-dom';

import { useDispatch, useSelector } from 'react-redux';

import defaultImage from '../../deafultpic.jpg';

import './NavbarManager.css';
import { AppDispatch, RootState } from '../../store';
import { logoutUser } from '../../Slices/userSlice';

export const NavbarManager: React.FC = () => {

    const userState = useSelector((state:RootState) => state.user.user);
    const dispatch:AppDispatch = useDispatch();

    const handleLogout = () => {
        dispatch(logoutUser());
    }

    const user = useSelector((state:RootState) => state.user.user);

    return(
        <nav className="navbar">
            <ul className='nav-menu'>
                <li className="nav-item">
                    <Link to={"/home"} className="nav-link">Home</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/manager/allReimbursement/Pending"} className="nav-link">Reimbursement</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/manager/employeesinfo"} className="nav-link">Employees Ac Info</Link>
                </li>
                <li className="nav-item logout">
                    <Link to={"/login"} className="nav-link">
                        <button className="logout-btn" onClick={handleLogout}>Logout</button>
                    </Link>
                </li>
            </ul>
        </nav>
    )
}