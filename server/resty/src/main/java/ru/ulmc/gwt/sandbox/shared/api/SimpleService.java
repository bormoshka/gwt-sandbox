package ru.ulmc.gwt.sandbox.shared.api;

import org.fusesource.restygwt.client.DirectRestService;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(SimpleService.CONTROLLER_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SimpleService extends DirectRestService {
    String CONTROLLER_IMPL_URL = "ss";
    String CONTROLLER_URL = "rest/" + CONTROLLER_IMPL_URL;

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    String hello();

    @GET
    @Path("/wait")
    @Produces(MediaType.APPLICATION_JSON)
    String waitForMe() throws Exception;


    @GET
    @Path("/getCities")
    List<CityModel> getCities() throws Exception;

    @GET
    @Path("/stepOne")
    String stepOne() throws Exception;

    @GET
    @Path("/stepTwo/{input}/{throwError}")
    Long stepTwo(@PathParam("throwError")boolean throwError, @PathParam("input") String input) throws Exception;

    @GET
    @Path("/stepThree/{input}")
    List<String> stepThree(@PathParam("input") Long input) throws Exception;
}
