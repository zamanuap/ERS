import React from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import './App.css';
import { SingleEmployeeInfoPage } from './Views/AccountInfoPage/SingleEmployeeInfoPage/SingleEmployeeInfoPage';
import { AccountUpdatePage } from './Views/AccountUpdatePage/AccountUpdatePage';
import { ActionReimbursementPage } from './Views/ActionReimbursementPage';
import { ActionStatusShowPage } from './Views/ActionReimbursementPage/ActionStatusShowPage';
import { EmployeesInfoPage } from './Views/EmployeesInfoPage';
import { EmployeeHomePage } from './Views/HomePage/EmployeeHomePage';
import { ManagerHomePage } from './Views/HomePage/ManagerHomePage';
import { LoginPage } from './Views/LoginPage';
import { AllEmployeesReimbursementPage } from './Views/ReimbursementPage/AllEmployeesReimbursementPage/AllEmployeesReimbursementPage';
import { EmployeeReimbursementPage } from './Views/ReimbursementPage/EmployeeReimbursementPage';
import { OtherEmployeeReimbursementPage } from './Views/ReimbursementPage/OtherEmployeeReimbursementPage/OtherEmployeeReimbursementPage';

import { SearchReimbursementPage } from './Views/ReimbursementPage/OtherEmployeeReimbursementPage/SearchReimbursementPage';
import { SubmitReimbursementPage } from './Views/ReimbursementPage/SubmitReimbursementPage';
import { SubmitSuccessPage } from './Views/ReimbursementPage/SubmitReimbursementPage/SubmitSuccessPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
          <Route path="/login" element={<LoginPage />}/>
          <Route path="/manager/home" element={<ManagerHomePage />}/>
          <Route path="/employee/home" element={<EmployeeHomePage />}/>
          <Route path="/employee/reimbursement" element={<SubmitReimbursementPage />}/>
          <Route path="/employee/reimbursement/submitted" element={<SubmitSuccessPage />}/>
          <Route path="/manager/reimbursement" element={<SubmitReimbursementPage />}/>
          <Route path="/employee/:username" element={<SingleEmployeeInfoPage />}/>
          <Route path="/manager/employeesinfo" element={<EmployeesInfoPage />}/>
          <Route path="/employee/:username/edit" element={<AccountUpdatePage />}/>
          <Route path="/employee/reimbursement/:status" element={<EmployeeReimbursementPage />}/>
          <Route path="/manager/allReimbursement/:status" element={<AllEmployeesReimbursementPage />}/>
          <Route path="/manager/searchReimbursement" element={ <SearchReimbursementPage />}/>
          <Route path="/manager/:username/:status" element={<OtherEmployeeReimbursementPage />}/>
          <Route path="/manager/actionReimbursement/:id" element={<ActionReimbursementPage />}/>
          <Route path="/manager/actionStatus/:status" element={<ActionStatusShowPage />}/>
          <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
