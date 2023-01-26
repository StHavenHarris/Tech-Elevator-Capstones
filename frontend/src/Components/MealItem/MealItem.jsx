import "./mealItem.css";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";

export const MealItem = ({meal}) => {

    // const APIKEY = 'fab23e3a35904d67969a853a774b09a0'
    const APIKEY = '5a3a4703d7c94d118ead7ea7ccf44fea'
    const baseURL = `https://api.spoonacular.com/recipes/${meal.id}/information?apiKey=${APIKEY}&includeNutrition=false`
    
    
    const [imageUrl, setImageUrl] = useState('')

    useEffect(() => {
        fetch(baseURL)
        .then((resp) => resp.json())
        .then((data) => {
            setImageUrl(data.image)
        })
        .catch(() => {
            console.log('error')
        })
    }, [meal.id])



  return (
    <>
            <div className="body-card col">
              <br/>
              <div className="card">
                <img src={imageUrl} className="card-img-top" alt={meal.id} />
                <div className="card-body">
                  <h5 className="card-title">{meal.title}</h5>
                  <p className="card-text">
                    This is a longer card with supporting text below as a natural lead-in to
                    additional content. This content is a little bit longer.
                  </p>
                  <ul className="list-group list-group-flush">
                    <li className="list-group-item">Preparation Time: {meal.readyInMinutes}</li>
                    <li className="list-group-item">Servings: {meal.servings}</li>
                  </ul>
                  <a href={meal.sourceUrl} className="btn btn-primary">Go to Recipe</a>
                </div>
              </div>
              </div>
    </>
  )
}
