import "./mealPlannerPage.css";
import { useState } from "react";
import { CardItem } from "../../CardItem/CardItem";
import { Navbar } from "../../Navbar/Navbar";
import { SearchMeal } from "../../helper/SearchMeal";

  export const MealPlannerPage = () => {

    const { mealData, handleChange, getMealData } = SearchMeal()
    

  return (
    <div className="search-get">
      <div className="search">
        <input 
        className="search-input"
        type="number" 
        placeholder='Calories'
        onChange={handleChange}
        />
        <button className="get-meal" onClick={getMealData}>Get Daily Meals</button>
      </div>
      <br/>
      <div className="meal-planner">
        {mealData && <CardItem mealData={mealData} />}
      </div>
    </div>
  )
}
