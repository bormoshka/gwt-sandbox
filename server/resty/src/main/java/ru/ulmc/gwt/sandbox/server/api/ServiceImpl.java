package ru.ulmc.gwt.sandbox.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ulmc.gwt.sandbox.shared.api.Service;
import ru.ulmc.gwt.sandbox.shared.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(Service.CONTROLLER_URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceImpl implements Service {
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    @Override
    @GET
    @Path("/wtf/{bean}")
    public NotThatSimpleBean getBean(SimpleBean bean) {
        logger.debug("hit getBean");
        NotThatSimpleBean notThatSimpleBean =  new NotThatSimpleBean();
        notThatSimpleBean.put(bean.getFieldString(), bean.getFieldLong());
        return notThatSimpleBean;
    }

    @Override
    @GET
    @Path("/ping")
    public String ping() {
        logger.debug("hit ping");
        return "Okay";
    }
}
