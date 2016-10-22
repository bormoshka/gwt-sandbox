package ru.ulmc.gwt.sandbox.server;

import com.sun.jersey.spi.resource.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ulmc.gwt.sandbox.client.api.Service;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Singleton
@Path("/testc")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GET
    @Path("/ping")
    @Produces("*/*")
    public String ping() {
        logger.debug("hit ping");
        return "Okay";
    }
}
