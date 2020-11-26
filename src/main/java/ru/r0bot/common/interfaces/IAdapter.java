package ru.r0bot.common.interfaces;

public interface IAdapter<T, S> {
    T processUpdate(S update);
}
