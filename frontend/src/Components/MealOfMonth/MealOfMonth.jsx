import React from 'react'
import meal from "../../../src/Shared/images/tomahawk-broccoli.png";

export const MealOfMonth = () => {
  return (
    <div className='show'>       
          <br/>
          <h2 className="monthly-meal-name" >Tomahawk Steak with Broccoli</h2>
            <div className="overview"> 
              <img src={ meal } className="monthly-image" alt="meal-of-the-month" />
              <div className="monthly-overview"> 
                <div className="overview-text">
                  <p className="meal-description">After eating turkey and ham for consecutive days, it is time to change up you next meal. Mouths will water as you serve this delectable burbon infused tomahawk steak, broccoli and couscous.</p>
                  <ul className="prep-time">
                    <li className="time"><strong>Preperation time: </strong> 30 minutes</li>
                    <li className="time2"><strong>Cooking time: </strong> 1 hour</li> 
                    <li className="time2"><strong>Total time: </strong>  3:30 to 13:30 hours</li>
                    <li className="time2"><strong>Serves: </strong>  4</li>
                  </ul>
                </div>
                <div className="overview-area">                
                  <h3 className="ingredients-title">Ingredients</h3>
                  <div className="ingredients-list"> 
                    <ul className="ingredients">
                      <h5>Tomahawk Steaks</h5>
                      <li><strong>2 Tbls</strong> -   Butter</li>
                      <li><strong>1 Tbls</strong> -  Olive oil</li>
                      <li><strong>1 Tbls</strong> -   Garlic</li>
                      <li><strong>1/2 cup</strong> - Onions</li>
                      <li><strong>1 cup</strong> -   Burbon</li> 
                      <li><strong>1 cup</strong> -   Beef broth</li>
                      <li><strong>2 tsp</strong> -    Salt</li>
                      <li><strong>1 tsp</strong> -    Pepper</li>
                      <li><strong>4 each</strong> -   Tomahawk beef steaks</li>
                      <br/>
                      <h5>Broccoli</h5>
                      <li><strong>1 lb</strong>  -    Baby broccoli</li>
                      <li><strong>1 Tbl</strong> -    Olive oil</li>
                      <li><strong>1 tsp</strong> -    Salt</li>                    
                      <li><strong>1/2 tsp</strong> -  Pepper</li>
                      <br/>
                      <h5>Couscous</h5>
                      <li><strong>1 cups</strong>  - Couscous</li>
                      <li><strong>1 cup</strong> -   Water</li>
                      <li><strong>1 tbls</strong> -  Butter or Olive oil</li>
                      <li>Salt and Oregano to taste</li>
                      <br/>
                      <li>*Rice can be substituded for couscous</li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
        </div> 
  )
}
