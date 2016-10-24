package ru.ulmc.gwt.sandbox.shared.api;

import org.fusesource.restygwt.client.DirectRestService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.ulmc.gwt.sandbox.shared.model.SimpleBaseModelBean;

@Path(SimpleService.CONTROLLER_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SimpleService extends DirectRestService {
    String CONTROLLER_IMPL_URL = "ss";
    String CONTROLLER_URL = "rest/" + CONTROLLER_IMPL_URL;

    @GET
    @Path("/hello")
    SimpleBaseModelBean hello();

    @POST
    @Path("/post")
    void post(SimpleBaseModelBean bean);

}
