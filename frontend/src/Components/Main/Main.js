import { Component } from "react";
import { connect } from "react-redux";
import { Link, Redirect, Route, Switch } from "react-router-dom";
import { withRouter } from "react-router-dom";
import { addToken, deleteUser } from "../../Redux/actionCreators";
import { Navbar } from "../Navbar/Navbar.jsx";
import { ContactUs } from "../Page/ContactUsPage/ContactUs.jsx";
import { Home } from "../Page/HomePage/Home.jsx";
import { Login } from "../Page/LoginPage/Login.jsx";
import { MealPlannerPage } from "../Page/MealPlannerPage/MealPlannerPage.jsx";
import { Register } from "../Page/RegisterPage/Register.jsx";

const mapStateToProps = state => {
    return {
        token: state.token,
        user: state.user
    }
}

const mapDispatchToProps = (dispatch) => ({
    addToken: () => { dispatch(addToken()) },
    deleteUser: () => { dispatch(deleteUser())}
});

class Main extends Component {
    constructor(props){
        super(props);
    }

    handleLogout = () => {
        this.props.addToken("")
        this.props.deleteUser()
    }

    render(){
        return(
            <div>
            <Navbar />
                {this.props.token.token !== undefined ?
                        <div>
                            <Link to='/home'></Link>
                            <Link to='/login' onClick={this.handleLogout}>logout</Link> 
                            <Link to='/contactus'></Link> 
                            <Redirect to='/home'/>

                        </div>  
                    : 
                        <Link to='/login'></Link>
                }
                <Switch>
                    <Route path='/login' component={() => <Login/>}/>
                    <Route path='/register'component={() => <Register/>}/>
                    <Route path='/home' component={() => <Home/>}/>
                    <Route path='/mealplanner' component={() => <MealPlannerPage/>}/>
                    <Route path='/contactus' component={() => <ContactUs/>}/>
                    <Redirect to='/login'/>
                </Switch>
            </div>
        )
    }
} 

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));