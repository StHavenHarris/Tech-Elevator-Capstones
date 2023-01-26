package com.techelevator.dao;
import com.techelevator.model.RecipeCard;
import com.techelevator.model.RecipeCardNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeCardDao implements RecipeCardDao{

    private JdbcTemplate jdbcTemplate;
    private Logger log = LoggerFactory.getLogger(getClass());

    public JdbcRecipeCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RecipeCard> list() {
        List<RecipeCard> catCards = new ArrayList<>();
        String sql = "SELECT id, imgage, title FROM recipecards ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            RecipeCard card = mapRowToCard(results);
            catCards.add(card);
        }
        return catCards;
    }

    @Override
    public RecipeCard get(long id) {
        RecipeCard card = null;
        String sql = "SELECT id, image, title FROM recipecards WHERE id = ? ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next()) {
            card = mapRowToCard(results);
        } else {
            throw new RecipeCardNotFoundException();
        }

        return card;
    }

    @Override
    public boolean update(long cardId, RecipeCard changedCard) {
        String sql = "UPDATE recipecards SET image = ?, title = ? WHERE id = ? ";
        return jdbcTemplate.update(sql, changedCard.getImage(), changedCard.getTitle(), cardId) == 1;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM recipecards WHERE id = ? ";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public boolean save(RecipeCard card) {
        String sql = "INSERT INTO recipecards (id, image, title) VALUES (DEFAULT, ?, ?)";
        return jdbcTemplate.update(sql,card.getImage(),card.getTitle()) == 1;
    }

    private boolean exists(long id) {
        return jdbcTemplate.queryForObject("select * from recipecards where id = ?", new Object[]{id}, boolean.class);
    }

    private RecipeCard mapRowToCard(SqlRowSet rs) {
        RecipeCard cc = new RecipeCard();
        cc.setId(rs.getLong("id"));
        cc.setTitle(rs.getString("title"));
        cc.setImage(rs.getString("image"));
        return cc;
    };

}
