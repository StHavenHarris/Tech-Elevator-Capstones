import "./login.css";
import axios from "axios";
import { useState } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { withRouter } from "react-router-dom";
import { addToken, addUser } from "../../../Redux/actionCreators";
import { baseUrl } from "../../../Shared/baseUrl";

// import { compose } from "redux";

const mapDispatchToProps = (dispatch) => ({
    addToken: () =>  dispatch(addToken()),
    addUser: () => dispatch(addUser()) 
});

export const Login = () => {
    
    const [ values, setValues ] = useState({
        username: '',
        password: ''
    })    
    
    const handleLogin = async (dispatch) => {
        const data = { username: values.username, password: values.password };
        
        const userWithToken = await axios.post(baseUrl + '/login', data)
        
        await addUser(userWithToken.data.token);
        await addToken(userWithToken.data.user);
        console.log(userWithToken.data.token)       
        console.log("UserLogged in!")
        
    }
    
    const onInputChange = (e) => {
        e.preventDefault()
        const { name, value } = e.target;
        setValues(prevState => ({
            ...prevState,
            [name]: value
            
        }))
        
    }  

  return (
    <div className="login-body">
        <h1 className="sign-in">Please Sign In</h1>
        <label className="sr-only">Username</label>
        <input
            type="text"
            className="username"
            id="username"
            name="username"
            placeholder="Username"
            v-model="user.username"
            value={values.name}
            onChange={onInputChange}
            required
        />
        <label className="sr-only">Password</label>
        <input
            type="password"
            className="password"
            id="password"
            name="password"
            placeholder="Password"
            v-model="user.password"
            value={values.password}
            onChange={onInputChange}
            required
        />
        <br />  
        <Link className="need-account" to="/register">Need an account?</Link>
        <br /> 
        <button className="button" type="submit" onClick={handleLogin} href="/home">Sign In</button>
    </div>
  )
}


export default withRouter(connect(mapDispatchToProps)(Login));
