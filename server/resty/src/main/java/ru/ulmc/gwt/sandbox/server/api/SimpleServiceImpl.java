package ru.ulmc.gwt.sandbox.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ulmc.gwt.sandbox.shared.api.SimpleService;

@Controller
@RequestMapping(SimpleService.CONTROLLER_IMPL_URL)
public class SimpleServiceImpl implements SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "{\"value\":\"gotcha!\"}";
    }
}
