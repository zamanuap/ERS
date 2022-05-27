import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { NavbarManager } from "../../../Components/NavbarManager/NavbarManager";
import { showOtherReimbursement } from "../../../Slices/userSlice";
import { AppDispatch } from "../../../store";

import "./SearchReimbursementPage.css";

export const SearchReimbursementPage: React.FC = () => {
    const [username, setUsername] = useState("");

    const handleOnChange = (e: any) => {
        setUsername(e.target.value);
    }

    return(
        <>
            <NavbarManager />
            <div className="search-div">
                <input className="search-input" name="search" type="text" autoComplete="off"
                    placeholder="Enter username..." onChange={handleOnChange}
                /> 
                <button className="search-btn"><Link className="search-link" to={`/manager/${username}/Pending`}>Search</Link></button>
            </div>
        </>
    )
}