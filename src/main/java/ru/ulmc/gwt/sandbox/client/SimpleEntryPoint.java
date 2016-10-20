package ru.ulmc.gwt.sandbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import ru.ulmc.gwt.sandbox.client.api.ServiceAsync;

/**
 * Created by 45 on 20.10.2016.
 */
public class SimpleEntryPoint implements EntryPoint {

    public SimpleEntryPoint() {
    }

    public void onModuleLoad() {

        final Button button = new Button("Click me!");
        ServiceAsync.client.getString(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {

            }
        });
        RootPanel.get().add(button);
    }
}