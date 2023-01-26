import "./register.css";
import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";
import { baseUrl } from "../../../Shared/baseUrl";

export const Register = () => {

    const [value, setValues ] = useState({
        username: '',
        password: '',
        confirmPassword: ''
    }) 

    const onInputChange = (e) => {
        e.preventDefault()
        const { name, value } = e.target;
        setValues(prevState => ({
            ...prevState,
            [name]: value
        }))
    }    
    
    const handleSubmit = () => {
        const data = {username: value.username, password: value.password, confirmPassword: value.confirmPassword, role: 'USER'}
        if(value.password === value.confirmPassword){
            axios.post(baseUrl + "/register", data)
        }else{
            alert("Password and Confirm Password must match!!!")
        }
        
    }   
    
    

  return (
    <div className="reg-body">        
        <h1 className="create-account" >Create Account</h1>        
        <label className="sr-only">Username</label>
        <input
            style={{marginLeft: '100px', marginTop: '50px', width: '200px',}}
            type="text"
            id="username"
            name="username"
            className="form-control"
            value={value.username}
            placeholder="Username"
            v-model="user.username"
            onChange={onInputChange}
            required
        />
        <label className="sr-only">Password</label>
        <input
            style={{marginLeft: '100px', marginTop: '10px', width: '200px',}}
            type="password"
            id="password"
            name="password"
            className="form-control"
            value={value.password}
            placeholder="Password"
            v-model="user.password"
            onChange={onInputChange}
            required
        />
        <input
            style={{marginLeft: '100px', marginTop: '10px', width: '200px',}}
            type="password"
            id="password-confirm"
            name="confirmPassword"
            className="form-control"
            value={value.confirmPassword}
            placeholder="Confirm Password"
            v-model="user.password"
            onChange={onInputChange}
            required
        />
        <br/>
        <Link className="have-account" to="/login">Have an account?</Link>
        <br />  
        <button type="submit" onClick={handleSubmit}>Sign In</button>
    </div>
  )
}
