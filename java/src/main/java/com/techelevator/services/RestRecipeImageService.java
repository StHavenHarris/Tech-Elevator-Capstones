package com.techelevator.services;
import org.springframework.stereotype.Component;

import com.techelevator.model.RecipeImage;

import org.springframework.web.client.RestTemplate;

@Component
public class RestRecipeImageService implements RecipeImageService{

    private final String apiUrl = "https://api.spoonacular.com/recipes";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public RecipeImage getFile() {
        // TODO Auto-generated method stub
        RecipeImage response = restTemplate.getForObject(apiUrl, RecipeImage.class);
        return response;
    }

}
