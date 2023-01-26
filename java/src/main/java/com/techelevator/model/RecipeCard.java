package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class RecipeCard {

    public Long Id;
    @NotEmpty
    public String Title;
    @NotEmpty
    public String image;
    @NotEmpty
    public String imageType;

    public Long getId() {
        return Id;
    }
    public void setId(Long ID) { this.Id = Id;}

    public String getTitle() {return Title;    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


}