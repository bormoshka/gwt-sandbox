package ru.ulmc.gwt.sandbox.client.model;

import java.util.Map;

/**
 *
 */
public class NotThatSimpleBean extends BaseBeanMap<String, Long> {

    private String beanField = "NotThatSimpleBeanField";

    public NotThatSimpleBean(Map<String, Long> map) {
        super(map);
    }

    public NotThatSimpleBean() {

    }
}
