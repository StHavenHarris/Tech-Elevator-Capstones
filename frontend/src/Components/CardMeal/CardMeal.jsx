import "./cardMeal.css";
import { useState } from "react";
import { CardItem } from "../CardItem/CardItem";
import { SearchMeal } from "../helper/SearchMeal";

export const CardMeal = () => {

    const { mealData, handleChange, getMealData } = SearchMeal()
    

  return (
    <>
    
    <input
    className="search-input"
    type="number" 
    placeholder='Calories'
    onChange={handleChange}
    />
    
    <button onClick={getMealData}>Get Daily Meal</button>
    {mealData && <CardItem mealData={mealData} />}
    </>
  )
}
