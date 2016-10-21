package ru.ulmc.gwt.sandbox.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.ulmc.gwt.sandbox.client.api.Service;
import ru.ulmc.gwt.sandbox.client.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.client.model.SimpleBean;

/**
 * Created by 45 on 20.10.2016.
 */
@RestController
@RequestMapping(value = Service.CONTROLLER_URL)
public class ServiceImpl extends RemoteServiceServlet implements Service {
    @Override
    public NotThatSimpleBean getBean(SimpleBean bean) {
        NotThatSimpleBean notThatSimpleBean =  new NotThatSimpleBean();
        notThatSimpleBean.put(bean.getFieldString(), bean.getFieldLong());
        return notThatSimpleBean;
    }
}
