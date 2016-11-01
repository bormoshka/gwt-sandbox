package ru.ulmc.gwt.sandbox.shared.api;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.MethodCallback;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;
import ru.ulmc.gwt.sandbox.shared.model.actuator.HealthModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface ActuatorServiceAsync extends DirectRestService {
    ActuatorService instance = GWT.create(ActuatorService.class);
    ActuatorServiceAsync client = GWT.create(ActuatorServiceAsync.class);

    void health(MethodCallback<HealthModel> callback);


}
