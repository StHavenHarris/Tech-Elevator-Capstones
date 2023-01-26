package com.techelevator.controller;
import com.techelevator.dao.RecipeCardDao;
import com.techelevator.dao.JdbcRecipeCardDao;
import com.techelevator.model.RecipeCard;
import com.techelevator.model.RecipeTitle;
import com.techelevator.model.RecipeImage;
import com.techelevator.model.RecipeImage;
import com.techelevator.model.RecipeTitle;
import com.techelevator.services.RecipeTitleService;
import com.techelevator.services.RecipeImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cards")
public class RecipeController {

    private RecipeCardDao recipeCardDao;
    private RecipeTitleService recipeTitleService;
    private RecipeImageService recipeImageService;

    public RecipeController(RecipeCardDao recipeCardDao, RecipeTitleService recipeTitleService, RecipeImageService recipeImageService) {
        this.recipeCardDao = recipeCardDao;
        this.recipeTitleService = recipeTitleService;
        this.recipeImageService = recipeImageService;
    }

    @RequestMapping(path = "/random?apiKey=5a3a4703d7c94d118ead7ea7ccf44fea" ,method = RequestMethod.GET)
    public RecipeCard makeNewCard(){
        RecipeTitle title = recipeTitleService.getText();
        RecipeImage image = recipeImageService.getFile();
        RecipeCard card = new RecipeCard();
        card.setTitle(title.getText());
        card.setImage(image.getFile());
        return card;
    }
    @RequestMapping(path = "",method = RequestMethod.GET)
    public List<RecipeCard> list() {
        return recipeCardDao.list();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public RecipeCard get(@PathVariable long id){
        return recipeCardDao.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean save(@RequestBody RecipeCard recipeCard){
        return recipeCardDao.save(recipeCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public boolean update(@RequestBody RecipeCard recipeCard, @PathVariable long id){
        return recipeCardDao.update(id,recipeCard);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long id){
        return recipeCardDao.delete(id);
    }

}
