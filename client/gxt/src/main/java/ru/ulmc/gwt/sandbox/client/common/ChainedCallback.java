package ru.ulmc.gwt.sandbox.client.common;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.ResultListener;

/**
 * Имплементация колбэка для лоадера грида
 */
public abstract class ChainedCallback<R> implements MethodCallback<R> {

    private final ResultListener<R> listener;

    public ChainedCallback(ResultListener<R> listener) {
        this.listener = listener;
    }

    @Override
    public final void onFailure(Method method, Throwable throwable) {
        listener.onException(throwable);
        onFailure(throwable);
    }

    @Override
    public final void onSuccess(Method method, R result) {
        onSuccess(result);
        listener.onFinish(result);
    }

    public void onFailure(Throwable throwable) {

    }

    public abstract void onSuccess(R result);
}
