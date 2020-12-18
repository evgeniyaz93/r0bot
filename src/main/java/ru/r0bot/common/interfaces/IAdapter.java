package ru.r0bot.common.interfaces;

import java.io.IOException;

public interface IAdapter<T, S> {
    T processUpdate(S update) throws Exception;
}
