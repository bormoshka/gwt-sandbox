package ru.ulmc.gwt.sandbox.client.common.tasks;

public interface ResultListener<T> {
    void onException(Throwable throwable);

    //how about
    //void abort();
    //?

    void onFinish(T result);
}
