import { useState } from "react"


export const SearchFood = () => {

    const [foodData, setFoodData] = useState('')
    const [food, setFood] = useState('')
    
    // const APIKEY = 'fab23e3a35904d67969a853a774b09a0'

    const APIKEY = '5a3a4703d7c94d118ead7ea7ccf44fea'
    const baseURL = `https://api.spoonacular.com/food/search?apiKey=${APIKEY}&query=${food}&number=8`

    const handleChange = (e) => {
        e.preventDefault()
        setFood(e.target.value)
    }
    const getFoodData = () => {
        fetch(baseURL)
        .then((resp) => resp.json())
        .then((data) => {
            setFoodData(data.searchResults[0].results)
        })
        .catch(() => {
            console.log("error")
        })
    }

    return {
    
    food,
    foodData,
    setFood,
    setFoodData,
    handleChange,
    getFoodData
    
    }

}



    