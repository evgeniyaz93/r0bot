package ru.r0bot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "recipe")
@Table(name = "recipe")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String ingredients;

    @Column
    private String recipe;

    @Column
    private Date datetime;


    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
