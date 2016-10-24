package ru.ulmc.gwt.sandbox.server.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.ulmc.gwt.sandbox.shared.api.SimpleService;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleTree;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(SimpleService.CONTROLLER_IMPL_URL)
public class SimpleServiceImpl implements SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);
    Gson gson = new Gson();

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public SimpleBaseModelBean hello() {
        SimpleBaseModelBean bean = new SimpleBaseModelBean("You are", "awesome!");
        return bean;
    }

    @Override
    @RequestMapping(path = "/post", method = RequestMethod.POST)
    public void post(@RequestBody SimpleBaseModelBean bean) {
        logger.debug("Hit PUT method A: {}, B: {}", bean.getA(), bean.getB());
    }

    @Override
    @ResponseBody
    public List<SimpleTree> getTree() {

        List<SimpleTree> list = new LinkedList<>();
        /*list.add(new SimpleTree("Parent 1",
                new SimpleTree[]{
                        new SimpleTree("Child", "1", "2"),
                        new SimpleTree("Child 2", "1", "3"),
                        new SimpleTree("Child 3", new SimpleTree[]{new SimpleTree("GrandChild", "1", "5")}
                        )
                })
        );
        */
        return list;
    }
}
