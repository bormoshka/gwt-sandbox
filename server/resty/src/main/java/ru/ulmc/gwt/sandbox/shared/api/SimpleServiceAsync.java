package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

public interface SimpleServiceAsync extends CommonAsyncService {
    SimpleService instance = GWT.create(SimpleService.class);
    SimpleServiceAsync client = GWT.create(SimpleServiceAsync.class);

    void hello(MethodCallback<String> callback);

    void waitForMe(MethodCallback<String> callback);

    void getCities(MethodCallback<List<CityModel>> callback);

    void stepOne(MethodCallback<String> callback);

    void stepTwo(boolean throwError, String input, MethodCallback<Long> callback);

    void stepThree(Long input, MethodCallback<List<String>> callback);

}
