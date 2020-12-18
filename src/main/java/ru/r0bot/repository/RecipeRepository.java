package ru.r0bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.r0bot.entity.Recipe;


import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query("select r from recipe r where r.name = :name")
    List<Recipe> findByName (@Param("name") String name);
}
