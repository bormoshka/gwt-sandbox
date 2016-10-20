package ru.ulmc.gwt.sandbox.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.ulmc.gwt.sandbox.client.api.Service;

/**
 * Created by 45 on 20.10.2016.
 */
public class ServiceImpl extends RemoteServiceServlet implements Service {
    @Override
    public String getString() {
        return null;
    }
}
