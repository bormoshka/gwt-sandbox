package ru.ulmc.gwt.sandbox.server.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ulmc.gwt.sandbox.shared.model.NotThatSimpleBean;
import ru.ulmc.gwt.sandbox.shared.model.SimpleBean;
import ru.ulmc.gwt.sandbox.shared.api.Service;

/**
 * Конкретно этот пример не работает не смотря на спецификацию. Ошибка в конфигруировании jersey?
 */
public class BrokenServiceImpl implements Service {
    Logger logger = LoggerFactory.getLogger(BrokenServiceImpl.class);

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
