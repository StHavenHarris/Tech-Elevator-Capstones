import React from "react";
import { useEffect } from "react";
import { useState } from "react";

// import RecipeInstructions from "../RecipeSteps/RecipeInstructions";

export const FoodItem = ({food}) => {

    // const APIKEY = 'fab23e3a35904d67969a853a774b09a0'
    const APIKEY = '5a3a4703d7c94d118ead7ea7ccf44fea'
    const baseURL = `https://api.spoonacular.com/recipes/${food.id}/information?apiKey=${APIKEY}&includeNutrition=false`
    
    
    
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
    }, [food.id])



  return (
    <>
            <div className="col">
              <div className="card">
                <img src={food.image} className="card-img-top" alt={food.id} />
                <div className="card-body">
                  <h5 className="card-title">{food.name}</h5>
                  {/* <p className="card-text">
                    {food.content}
                  </p> */}
                  <a href='#' class="btn nav-link btn-primary" onClick={"#"}>Go to Recipe</a>
                </div>
              </div>
              </div>
    </>
  )
}
