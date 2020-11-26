package ru.r0bot.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.r0bot.common.interfaces.IAdapter;

@Component
@PropertySource("classpath:integration.properties")
public class TelegramR0bot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    private final IAdapter<SendMessage, Update> adapter;

    @Autowired
    public TelegramR0bot(TelegramIAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        adapter.processUpdate(update);
    }
}
