import { useState } from "react"


export const SearchMeal = () => {

    const [mealData, setMealData] = useState(null)
    const [calories, setCalories] = useState(2000)
    
    // const APIKEY = 'fab23e3a35904d67969a853a774b09a0'
    const APIKEY = '5a3a4703d7c94d118ead7ea7ccf44fea'
    const baseURL = `https://api.spoonacular.com/mealplanner/generate?apiKey=${APIKEY}&timeFrame=day&targetCalories=${calories}&number=10`
    
    const handleChange = (e) => {
        e.preventDefault()
        setCalories(e.target.value)
    }
    const getMealData = () => {
        fetch(baseURL)
        .then((resp) => resp.json())
        .then((data) => {
            setMealData(data)
        })
        .catch(() => {
            console.log("error")
        })

    }

    return {
    
    mealData,
    calories,
    handleChange,
    getMealData
    
    }

}



    