import { FoodItem } from "../FoodItem/FoodItem";

export const CardFood = ({foodData}) => {

  return (

    <>  
      <div className="card-food row 
        row-cols-2 
        row-cols-sm-4 
        g-4 px-2  
        d-flex 
        justify-content-center"
      >
      {
      foodData.map((food) => {
        return <FoodItem key={food.id} food={food} />
      })
      
      }
    </div>
      </>

  )
}
