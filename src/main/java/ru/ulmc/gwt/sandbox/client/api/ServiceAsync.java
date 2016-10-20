package ru.ulmc.gwt.sandbox.client.api;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface ServiceAsync {
    Client client = GWT.create(Client.class);

    void getString(AsyncCallback<String> async);

    interface Client {

        void getString(AsyncCallback<String> async);
    }
}
