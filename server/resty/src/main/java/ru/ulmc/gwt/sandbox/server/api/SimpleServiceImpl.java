package ru.ulmc.gwt.sandbox.server.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ulmc.gwt.sandbox.shared.api.SimpleService;

import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping(SimpleService.CONTROLLER_IMPL_URL)
public class SimpleServiceImpl implements SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);
    Gson gson = new Gson();

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    @ResponseBody
    public String hello() {
        return gson.toJson("Hello!");
    }

    @Override
    @RequestMapping(path = "/wait", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    @ResponseBody
    public String waitForMe() throws Exception {
        wait(5000);
        return gson.toJson("wait!!");
    }
}
