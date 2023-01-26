import "./navbar.css";
import React, { useState } from "react";
import { SearchMeal } from "../helper/SearchMeal";

export const Navbar = () => {

  const { mealData, getMealData } = SearchMeal()

 

  return (
    
  
    <nav className="navbar navbar-expand-md navbar-light" >
      <div className="container-fluid">
        <div className="branding">
          <div className="logo-container">
            <span className="material-symbols-rounded">menu_book</span>
            <h3 src="logo" alt="logo" className="logo">MME</h3>       
          </div> 
          <div className="brand-name">
            <h1 className="company">Meals Made Easy</h1>            
            <a className="navbar-brand" href="#">MealPrepApp</a>
          </div>
        </div>
        <br/>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="/home">Home</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/mealplanner">Meal Planner</a>
            </li>
            <li className="nav-item">
              <a className="nav-link disabled" href="#" tabIndex="-1" aria-disabled="true">Disabled</a>
            </li>
            <li className="nav-item">
              <a className="nav-link " href="/contactus">Contact Us</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
