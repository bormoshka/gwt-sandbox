package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

import ru.ulmc.gwt.sandbox.shared.model.ComplexBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleTree;

public interface SimpleServiceAsync extends CommonAsyncService {
    SimpleService instance = GWT.create(SimpleService.class);
    SimpleServiceAsync client = GWT.create(SimpleServiceAsync.class);

    void hello(MethodCallback<ComplexBaseModelBean> callback);

    void post(ComplexBaseModelBean bean, MethodCallback<Void> callback);

    void getTree(MethodCallback<List<SimpleTree>> callback);
}
