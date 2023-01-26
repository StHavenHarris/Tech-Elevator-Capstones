import { useState } from "react";
import { SearchMeal } from "../helper/SearchMeal";

export const SearchItem = () => {

    const { mealData, handleChange, getMealData } = SearchMeal()

  return (
  <div className="area-search">
  
<input className="input form-control me-2" type="search" placeholder="Search" onChange={handleChange}/>
<button className="btn " type="submit" onClick={getMealData}>Get Recipe</button>
  </div>
    
  )
}
