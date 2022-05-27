import React from 'react';

import {Link} from 'react-router-dom';

import { useDispatch, useSelector } from 'react-redux';

import defaultImage from '../../deafultpic.jpg';

import './Navbar.css';
import { AppDispatch, RootState } from '../../store';
import { logoutUser } from '../../Slices/userSlice';

export const Navbar: React.FC = () => {

    const dispatch:AppDispatch = useDispatch();

    const handleLogout = () => {
        dispatch(logoutUser());
    }

    const user = useSelector((state:RootState) => state.user.user);

    return(
        <nav className="navbar">
            <ul className='nav-menu'>
                <li className="nav-item">
                    <Link to={"/employee/home"} className="nav-link">Home</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/employee/reimbursement"} className="nav-link">Reimbursement</Link>
                </li>
                <li className="nav-item">
                    <Link to={`/employee/${user?.username}`} className="nav-link">Account Info</Link>
                </li>
                <li className="logout nav-item">
                    <Link to={"/login"} className="nav-link">
                        <button className="logout-btn" onClick={handleLogout}>Logout</button>
                    </Link>
                </li>
            </ul>
        </nav>
    )
}