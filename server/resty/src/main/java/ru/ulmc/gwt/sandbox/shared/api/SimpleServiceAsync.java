package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import java.util.List;

public interface SimpleServiceAsync extends CommonAsyncService {
    SimpleService instance = GWT.create(SimpleService.class);
    SimpleServiceAsync client = GWT.create(SimpleServiceAsync.class);

    void hello(MethodCallback<String> callback);

    void waitForMe(MethodCallback<String> callback);

    void getCities(MethodCallback<List<CityModel>> callback);

}
