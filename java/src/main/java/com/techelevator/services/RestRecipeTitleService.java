package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.RecipeTitle;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRecipeTitleService implements RecipeTitleService{

    private final String apiUrl = "https://api.spoonacular.com/recipes";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public RecipeTitle getText() {
        // TODO Auto-generated method stub
        RecipeTitle response = restTemplate.getForObject(apiUrl, RecipeTitle.class);
        return response;
    }
}
