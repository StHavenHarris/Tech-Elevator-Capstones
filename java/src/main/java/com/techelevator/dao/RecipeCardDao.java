package com.techelevator.dao;
import com.techelevator.model.RecipeCard;

import java.util.List;


public interface RecipeCardDao {

    List<RecipeCard> list();

    RecipeCard get(long id);

    boolean save(RecipeCard cardToSave);

    boolean update(long id, RecipeCard card);

    boolean delete(long id);

}
