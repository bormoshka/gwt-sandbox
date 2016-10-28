package ru.ulmc.gwt.sandbox.client.common.tasks;

public interface Listener {
    void onStart();

    void onException(Throwable throwable);

    void onFinish();
}
