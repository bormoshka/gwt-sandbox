package ru.ulmc.gwt.sandbox.client.common.tasks;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 *
 */
public abstract class SimpleCallback<T> implements MethodCallback<T> {

    private SimpleListener listener;

    public SimpleCallback(SimpleListener listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        listener.onException(throwable);
        onFailure(throwable);
    }

    @Override
    public void onSuccess(Method method, T t) {
        listener.onFinish();
        onSuccess(t);
    }

    public abstract void onFailure(Throwable throwable);

    public abstract void onSuccess(T t);

}
