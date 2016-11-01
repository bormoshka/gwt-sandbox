package ru.ulmc.gwt.sandbox.client.common.tasks;

public interface QueuedExecutable<T> extends Executable {
    void execute(Listener listener);

    void execute(Listener listener, T result);

    void next(QueuedExecutable next);
}
