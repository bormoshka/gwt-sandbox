package ru.ulmc.gwt.sandbox.server.api;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import ru.ulmc.gwt.sandbox.shared.api.SimpleService;
import ru.ulmc.gwt.sandbox.shared.model.ComplexBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBaseModelBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleTree;

@RestController
@RequestMapping(SimpleService.CONTROLLER_IMPL_URL)
class SimpleServiceImpl implements SimpleService {
    private Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);
    Gson gson = new Gson();

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public ComplexBaseModelBean hello() {
        return new ComplexBaseModelBean(new SimpleBaseModelBean("You are", "awesome!"));
    }

    @Override
    @RequestMapping(path = "/post", method = RequestMethod.POST)
    public void post(@RequestBody ComplexBaseModelBean bean) {
        logger.debug("Hit PUT method: {}", bean);
    }


    @Override
    @RequestMapping(path = "/getTree", method = RequestMethod.GET)
    @ResponseBody
    public List<SimpleTree> getTree() {

        List<SimpleTree> list = new LinkedList<>();

        list.add(new SimpleTree("Child", "4", "2"));
        list.add(new SimpleTree("Child 2", "5", "3"));

        //StackOverflowError
        /*list.add(new SimpleTree("Parent 1",
                new SimpleTree[]{
                        new SimpleTree("Child", "6", "2"),
                        new SimpleTree("Child 2", "7", "3"),
                        new SimpleTree("Child 3", new SimpleTree[]{new SimpleTree("GrandChild", "8", "5")}
                        )
                })
        );*/

        return list;
    }
}
