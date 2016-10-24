package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleTree;

import java.util.List;

public interface SimpleServiceAsync extends CommonAsyncService {
    SimpleService instance = GWT.create(SimpleService.class);
    SimpleServiceAsync client = GWT.create(SimpleServiceAsync.class);

    void hello(MethodCallback<SimpleBaseModelBean> callback);

    void post(SimpleBaseModelBean bean, MethodCallback<Void> callback);

    void getTree(MethodCallback<List<SimpleTree>> callback);
}
