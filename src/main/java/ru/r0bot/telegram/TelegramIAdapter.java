package ru.r0bot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.r0bot.common.interfaces.IAdapter;

@Component
public class TelegramIAdapter implements IAdapter<SendMessage, Update> {

    @Override
    public SendMessage processUpdate(Update update) {
        System.out.println("Adapter");
        return null;
    }
}
