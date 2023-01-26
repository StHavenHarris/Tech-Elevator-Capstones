import "./home.css";
import axios from "axios";
import meal from "../../../Shared/images/tomahawk-broccoli.png";
import { useEffect } from "react";
import { useState } from "react";
import { CardFood } from "../../CardFood/CardFood";
import { CardMeal } from "../../CardMeal/CardMeal";
import { FoodItem } from "../../FoodItem/FoodItem";
import { Navbar } from "../../Navbar/Navbar.jsx";
import { SearchFood } from "../../helper/SearchFood";
import { MealOfMonth } from "../../MealOfMonth/MealOfMonth";

export const Home = () => {
  
  const { food, foodData, handleChange, getFoodData } = SearchFood();
  
  const [show, setShow] = useState(false)
  
  const onClick = () => {
    if(show == false) setShow(true)
    if(show == true) setShow(false)
  }
  


  
  return (
    <>
      <div className="home-body">
        <div className='search-area mt-2 d-flex justify-content-center mb-2'>
          <input className="search-input form-control me-2" type="search" placeholder="Search" style={{'width':25 + 'rem', 'height': '40px'}} onChange={handleChange}/>
          <button className="search-btn " type="submit" onClick={getFoodData}>Search</button>
          <br/>
          <div className='px-4' id="ideas">
            { foodData && <CardFood foodData={foodData} />}
          </div>
          <div className="show">
          <h1 className="monthly-meal mt-2" onClick={onClick}>December Meal of the Month</h1> 
          {show && <MealOfMonth />}
          </div>
        </div>
      </div>
    </>
  )
}