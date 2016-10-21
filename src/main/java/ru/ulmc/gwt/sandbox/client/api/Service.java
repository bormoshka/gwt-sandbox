package ru.ulmc.gwt.sandbox.client.api;


import com.google.gwt.core.client.GWT;

import org.fusesource.restygwt.client.DirectRestService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;

@Path(Service.CONTROLLER_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Service extends DirectRestService {
    Service instance = GWT.create(Service.class);

    String CONTROLLER_URL = "/test";
    String TEST_REQUEST = "/ACSWSFRBS/"; //todo: придумать вариант получше

    @GET
    @Path(TEST_REQUEST)
    NotThatSimpleBean getBean(SimpleBean bean);

}
