package ru.ulmc.gwt.sandbox.client.common;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.client.common.tasks.Listener;
import ru.ulmc.gwt.sandbox.client.common.tasks.SimpleCallback;

import java.util.List;

/**
 * Имплементация колбэка для лоадера грида
 */
public class LoaderCallback<R extends List<T>, T> extends SimpleCallback<R> {

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
