import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import { IReimbursement } from "../Interfaces/IReimbursement";
import {IUser} from "../Interfaces/IUser";

//Figure out our default state for this slice

interface UserSliceState {
    loading: boolean,
    error: boolean,
    isLogged: boolean,
    isUpdatefailed: boolean,
    isUpdated: boolean,
    user?: IUser,
    userList?: IUser[],
    reimbursement?: IReimbursement,
    reimbursementList?: IReimbursement[]
}

const initialUserState: UserSliceState = {
    loading: false,
    error: false,
    isLogged: false,
    isUpdated: false,
    isUpdatefailed: false
}

type Login = {
    username: string,
    password: string
}

type reimburseData = {
    reimburseStatus?: string
}

export const loginUser = createAsyncThunk(
    'user/login',
    async (credentials:Login, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/user/login', credentials);
            console.log(res.data);
            return {
                userId: res.data.userId,
                firstName: res.data.firstName,
                lastName: res.data.lastName,
                username: res.data.username,
                password: res.data.password,
                email: res.data.email,
                role: res.data.role
            }
        } catch(e){
            return thunkAPI.rejectWithValue('something went wrong');
        }
    }
)

export const logoutUser = createAsyncThunk(
    "user/logout",
    async (thunkAPI) => {
        try{
            axios.defaults.withCredentials = true;
            const res = axios.get("http://localhost:8000/user/logout");
        } catch(e){
            console.log(e);
        }
    }
)

export const allUserInfo = createAsyncThunk(
    'user/allUserInfo',
    async (thunkAPI) => {
        try{
            const res = await axios.get('http://localhost:8000/user/allAccountInfo');
            console.log(res.data);
            return res.data;
        } catch(e){
            return 'something went wrong';
        }
    }
)

export const updateUser = createAsyncThunk(
    'user/update',
    async (user:IUser, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/user/updateInfo', user);
            console.log(res.data);
            return res.data;
        } catch(e){
            console.log("user already exists")
            return null;
            //return thunkAPI.rejectWithValue('something went wrong');
            
        }
    }
)

export const submitReimbursement = createAsyncThunk(
    'user/post',
    async (reimburseData: IReimbursement, thunkAPI) => {
        try{
            const res = await axios.post("http://localhost:8000/reimbursement/submit",reimburseData);
            console.log(res.data);
            return {
                reimburseId: res.data.reimburseId,
                amount: res.data.amount,
                submittDate: res.data.submittedDate,
                resolvedDate: res.data.resolvedDate,
                description: res.data.description,
                reimburseAuthor: res.data.reimburseAuthor,
                reimburseResolver: res.data.reimburseResolver, 
                reimburseStatus: res.data.reimburseStatus,
                reimburseType: res.data.reimburseType
            }
        } catch(error){
            console.log(error);
        }
    }
)

export const pendingReimbursement = createAsyncThunk(
    'user/reimbursement',
    async (reimburseData: reimburseData, thunkAPI) => {
        try{
            const res = await axios.post("http://localhost:8000/reimbursement/show",reimburseData);
            return res.data;
        } catch(error){
            console.log(error);
        }
    }
)

export const showOtherReimbursement = createAsyncThunk(
    'user/otherReimbursement',
    async (reimburseData: any, thunkAPI) => {
        try{
            const res = await axios.post("http://localhost:8000/reimbursement/showOther",reimburseData);
            return res.data;
        } catch(error){
            console.log(error);
        }
    }
)

export const allPendingReimbursement = createAsyncThunk(
    'user/allReimbursement',
    async (reimburseData: reimburseData, thunkAPI) => {
        try{
            const res = await axios.post("http://localhost:8000/reimbursement/showAll",reimburseData);
            return res.data;
            
        } catch(error){
            console.log(error);
        }
    }
)

export const actionReimbursement = createAsyncThunk(
    'user/actionReimbursement',
    async (reimburseData: any, thunkAPI) => {
        try{
            const res = await axios.put("http://localhost:8000/reimbursement/updateRequest",reimburseData);
            return res.data;
            
        } catch(error){
            console.log(error);
        }
    }
)

//Create the slice
export const UserSlice = createSlice({
    name: "user",
    initialState: initialUserState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        },
        updateStatus : (state) => {
            state.isUpdatefailed = false;
            state.isUpdated = false;
        }
    },
    extraReducers: (builder) => {
        //This is where we would create our reducer logic
        builder.addCase(loginUser.pending, (state, action)=> {
            state.loading = true;
        });
        builder.addCase(loginUser.fulfilled, (state, action) => {
            //The payload in this case, is the return from our asyncThunk from above
            state.user = action.payload;
            state.error = false;
            state.loading = false;
            state.isLogged = true;
        });
        builder.addCase(loginUser.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
        builder.addCase(logoutUser.fulfilled, (state, action) => {
            state.user = undefined;
            state.isLogged = false;
        });
        builder.addCase(updateUser.fulfilled, (state, action) => {
            state.isUpdatefailed = action.payload ? false : true; 
            state.isUpdated = action.payload ? true : false;
            state.user = action.payload ? action.payload : state.user            
        });
        builder.addCase(allUserInfo.fulfilled, (state, action) => {
            state.userList = action.payload;
        });
        builder.addCase(submitReimbursement.fulfilled, (state, action) => {
            state.reimbursement = action.payload;
        
        });
        builder.addCase(pendingReimbursement.fulfilled, (state, action) => {
            state.reimbursementList = action.payload;
        });
        builder.addCase(allPendingReimbursement.fulfilled, (state, action) => {
            state.reimbursementList = action.payload;
        });
        builder.addCase(showOtherReimbursement.fulfilled, (state, action) => {
            state.reimbursementList = action.payload;
        });
    } 
})

//If we had normal actions and reducers we would export them like this
export const {toggleError, updateStatus } = UserSlice.actions;

export default UserSlice.reducer;