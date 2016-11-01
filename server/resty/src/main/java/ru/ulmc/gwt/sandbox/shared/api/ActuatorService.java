package ru.ulmc.gwt.sandbox.shared.api;

import org.fusesource.restygwt.client.DirectRestService;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;
import ru.ulmc.gwt.sandbox.shared.model.actuator.HealthModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ActuatorService extends DirectRestService {

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    HealthModel health();


}
