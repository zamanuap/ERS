import React, { useState } from "react";
import { Link } from 'react-router-dom';

import "./SubmitReimbursement.css"

interface IOnSubmit {
    onSubmitProp : (data:any) => void;
}

export const SubmitReimbursement: React.FC<IOnSubmit> = ({onSubmitProp}) =>{
    const [amount, setAmount] = useState(0);
    const [description, setDescription] = useState("");
    const [reimburseType, setReimburseType] = useState("");
    
    
    const handleOnChange = (e:any) => {
        if(e.target.name === "amount"){
            setAmount(parseFloat(e.target.value));
        } else if(e.target.name === "description"){
            setDescription(e.target.value);
        } else{
            setReimburseType(e.target.value);
            console.log(e.target.value)
        }
    }

    const handleOnSubmit = (e:any) => {
        e.preventDefault();
        onSubmitProp({amount, description, reimburseType});
    }

    return(
        <div className="container">
            <div className="link-div">
                <button className="reimburse-btn"><Link to={"/employee/reimbursement/Pending"} className="link">Pending Reimbursement</Link></button>
                <button className="reimburse-btn"><Link to={"/employee/reimbursement/Approved"} className="link">Approved Reimbursement</Link></button>
                <button className="reimburse-btn"><Link to={"/employee/reimbursement/Denied"} className="link">Denied Reimbursement</Link></button>
            </div>
            <form className="form-submit" onSubmit={handleOnSubmit}>
                <h3 className="h3-reimbusement">Submit Reimbursement</h3>
                <h4 className="h4-reimburse-submit">Amount</h4>
                <input className="reimburse-input" type="text" id="amount" 
                    name="amount" autoComplete="off" 
                    onChange={handleOnChange}/>
                <h4 className="h4-reimburse-submit">Description</h4>
                <input className="reimburse-input" type="text" id="description" name="description" autoComplete="off" onChange={handleOnChange}/>
                <h4 className="h4-reimburse-submit">Type</h4>
                
                <div className="radio-container-div">
                    <div>
                        <input type="radio" className="radio-input" name="type" value="LODGING" onClick={handleOnChange} />
                        <label className="radio-label" htmlFor="type">Lodging</label>
                    </div>
                    <div>
                        <input type="radio" className="radio-input" name="type" value="TRAVEL" onClick={handleOnChange} />
                        <label className="radio-label" htmlFor="type">Travel</label>
                    </div>
                    <div>
                        <input type="radio" className="radio-input" name="type" value="FOOD" onClick={handleOnChange} />
                        <label className="radio-label" htmlFor="type">Food</label>
                    </div>
                    <div>
                        <input type="radio" className="radio-input" name="type" value="OTHER" onClick={handleOnChange} />
                        <label className="radio-label" htmlFor="type">Other</label>
                    </div>
                </div>
                <input className="submit-input" type="submit" value="Submit" />
            </form>
        </div>
    )
}