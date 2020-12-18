package ru.r0bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ListIterator;

@Service
public class ParserService {

    public String recieveRecipeData (String userSearchText) throws Exception {
        String url = "https://povar.ru/xmlsearch?query=";
        String urlFull = url + userSearchText;
        Document doc = null;
        {
            try {
                doc = Jsoup.connect(urlFull)
                        .userAgent("Chrome/4.0.249.0")
                        .referrer("http://www.google.com")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String URL = getURLRecipe(doc);
            String Recipe = getRecipeData(URL);
            return  Recipe;
        }
    }

    private String getRecipeData(String myUrl)
    {
        Document doc = null;
        String fullRecipe = null;
        {
            try {
                doc = Jsoup.connect(myUrl)
                        .userAgent("Chrome/4.0.249.0")
                        .referrer("http://www.google.com")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            fullRecipe = getFullRecipe(getTitle(doc), getIngredients(doc), getRecipe(doc));

        }

        return fullRecipe;
    }
    private String getFullRecipe(String title, String ingredients, String recipe)
    {
        String fullRecipe = title + System.lineSeparator()+System.lineSeparator() + "_Ингредиенты:_" + System.lineSeparator() + ingredients + System.lineSeparator()+System.lineSeparator() + "_Рецепт:_" +System.lineSeparator() + recipe;
        return fullRecipe;
    }
    private String getTitle (Document doc)
    {
        String title = doc.getElementsByAttributeValue("itemprop", "name").text();
        title = "*" + title + "*";

        return title;
    }

    private String getRecipe (Document doc)
    {
        ListIterator<String> recipeList = doc.getElementsByClass("detailed_step_description_big").eachText().listIterator();
        String recipe = new String();
        Integer i = 1;
        while (recipeList.hasNext()) {

            recipe = recipe + recipeList.next() + System.lineSeparator();
            i++;
        }
        return recipe;
    }

    private String getIngredients (Document doc)
    {

        ListIterator<String> ingredientList = doc.getElementsByAttributeValue("itemprop", "recipeIngredient").eachText().listIterator();
        String ingredients = new String();
        Integer i = 1;
        while (ingredientList.hasNext()) {

            ingredients = ingredients + i + ". " + ingredientList.next() + System.lineSeparator();
            i++;
        }
        return ingredients;
    }
    private String getURLRecipe(Document doc) throws Exception {
        String recipeUrl;
        String url = doc.getElementsByAttributeValueMatching("href","html").first().attr("href");
        recipeUrl = "https://povar.ru/" + url;
        System.out.println(recipeUrl);
        return recipeUrl;
    }
}
