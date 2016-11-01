package ru.ulmc.gwt.sandbox.client.common;

import com.sencha.gxt.data.shared.ListStore;
import org.fusesource.restygwt.client.Method;
import ru.ulmc.gwt.sandbox.client.common.tasks.Listener;
import ru.ulmc.gwt.sandbox.client.common.tasks.BaseCallback;

import java.util.List;

/**
 * Имплементация колбэка для лоадера грида
 */
public class LoaderCallback<R extends List<T>, T> extends BaseCallback<R> {

    private final Listener listener;
    private final ListStore<T> store;

    public LoaderCallback(Listener listener, ListStore<T> store) {
        super(listener);
        this.listener = listener;
        this.store = store;
    }

    @Override
    public void onFailure(Method method, Throwable throwable) {
        listener.onException(throwable);
        onFailure(throwable);
    }

    public void onFailure(Throwable throwable) {
        store.clear();
    }

    @Override
    public void onSuccess(R r) {
        store.clear();
        store.addAll(r);
    }
}
