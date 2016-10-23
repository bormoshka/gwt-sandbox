package ru.ulmc.gwt.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
