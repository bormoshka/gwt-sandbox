package ru.ulmc.gwt.sandbox.client.common.tasks;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 *
 */
public abstract class SimpleCallback<T> implements MethodCallback<T> {
    /*
    private Listener listener;

    public SimpleCallback(Listener listener) {
        this.listener = listener;
    }
    */

    @Override
    public void onFailure(Method method, Throwable throwable) {
        onFailure(throwable);
    }

    @Override
    public void onSuccess(Method method, T t) {
        onSuccess(t);
    }

    public abstract void onFailure(Throwable throwable);

    public abstract void onSuccess(T t);

}
