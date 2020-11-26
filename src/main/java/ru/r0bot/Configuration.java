package ru.r0bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.r0bot.telegram.TelegramR0bot;

@org.springframework.context.annotation.Configuration
public class Configuration {

    TelegramR0bot telegramR0bot;

    @Autowired
    Configuration(TelegramR0bot telegramR0bot){
        this.telegramR0bot = telegramR0bot;
    }

    @Bean
    public BotSession registerTelegramBot(){
        BotSession session = null;
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            session = botsApi.registerBot(telegramR0bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return session;
    }
}