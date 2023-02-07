package org.example.interfaces;

public interface ICommandHandler<T> {
    public void execute(T arg);
}
