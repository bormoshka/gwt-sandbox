package ru.ulmc.gwt.sandbox.client.common.tasks;

public interface SimpleListener {
    void onStart();

    void onException(Throwable throwable);

    void onFinish();
}
