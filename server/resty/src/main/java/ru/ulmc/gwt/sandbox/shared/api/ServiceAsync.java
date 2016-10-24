package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

public interface ServiceAsync extends CommonAsyncService{
    Service instance = GWT.create(Service.class);
    ServiceAsync client = GWT.create(ServiceAsync.class);

    void getBean(SimpleBean bean, MethodCallback<NotThatSimpleBean> callback);

    void ping(MethodCallback<String> callback);
}
