package ru.r0bot.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.w3c.dom.DOMImplementation;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.r0bot.common.interfaces.IAdapter;
import ru.r0bot.entity.Recipe;
import ru.r0bot.entity.User;
import ru.r0bot.repository.RecipeRepository;
import ru.r0bot.repository.UsersRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service

public class TelegramBotService implements IAdapter<SendMessage, Update> {

    private final UsersRepository usersRepository;
    private final RecipeRepository recipeRepository;
    private final ParserService parserService;


    @Autowired
    public TelegramBotService(UsersRepository usersRepository, RecipeRepository recipeRepository, ParserService parserService) {
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
        this.parserService = parserService;
    }

    public void saveUserInfo(User user) {
        usersRepository.save(user);
    }

    public List<Recipe> getRecipe(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public SendMessage processUpdate(Update update) throws Exception {

        SendMessage message = new SendMessage();
        String userTextLC = update.getMessage().getText().toLowerCase();
        String userText = update.getMessage().getText();
        String oneTestRecipe = "сельдь под шубой";
        message.setChatId(update.getMessage().getChatId().toString());
        User us = new User();
        Recipe re = new Recipe();
        if (update.hasMessage() && update.getMessage().hasText()) {
            us.setName(update.getMessage().getChat().getUserName());
            us.setLog(userText);
            us.setDate(new Date());
            this.saveUserInfo(us);
            System.out.println("USER: " + update.getMessage().getChat().getUserName() + " "
                    + update.getMessage().getChat().getPhoto() +
                    "TEXT: " + userText);
            if (userText.equals("/start")) {
                String messageText = String.format("Привет! Напиши мне название блюда.");
                message.setText(messageText);
            } else if
            (userTextLC.contains("спасиб")) {
                String messageText = "*На здоровье!*";
                message.setText(messageText);
                message.setParseMode(ParseMode.MARKDOWN);
            } else {
                try {
                    String recipes = parserService.recieveRecipeData(userTextLC);
                    message.setText(recipes);
                    message.setParseMode(ParseMode.MARKDOWN);
                } catch (Exception e) {
                    message.setText("К сожалению, ничего не нашли.");
                }
            }
        }

        return message;
    }
}

