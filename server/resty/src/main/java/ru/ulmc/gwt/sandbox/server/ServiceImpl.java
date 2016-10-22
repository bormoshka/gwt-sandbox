package ru.ulmc.gwt.sandbox.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ulmc.gwt.sandbox.shared.api.Service;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
public class ServiceImpl implements Service {
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    @Override
    public NotThatSimpleBean getBean(SimpleBean bean) {
        logger.debug("hit getBean");
        NotThatSimpleBean notThatSimpleBean =  new NotThatSimpleBean();
        notThatSimpleBean.put(bean.getFieldString(), bean.getFieldLong());
        return notThatSimpleBean;
    }

    @Override
    public String ping() {
        logger.debug("hit ping");
        return "Okay";
    }
}
