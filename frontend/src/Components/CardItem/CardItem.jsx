import "./cardItem.css";
import { MealItem } from "../MealItem/MealItem";

export const CardItem = ({mealData}) => {

  const nutrients = mealData.nutrients;

  return (

  <>
  <div className="d-flex justify-content-center">
    <h1 className="macros">Macros</h1>
  </div>
    <ul className="nutrient-count ">
    <li className="nutrient"><b> Calories: </b> {nutrients.calories.toFixed(0)}</li>
    <li className="nutrient"><b> Fat: </b>{nutrients.fat.toFixed(0)}</li>
    <li className="nutrient"><b> Protein: </b> {nutrients.protein.toFixed(0)}</li>
    </ul>
    
 
  
  <div className="row row-cols-2 row-cols-sm-4 g-4 px-2  d-flex justify-content-center">
  {
  mealData.meals.map((meal) => {
    return <MealItem key={meal.id} meal={meal} />
  })
  
  }
  </div>
    </>

  )
}
