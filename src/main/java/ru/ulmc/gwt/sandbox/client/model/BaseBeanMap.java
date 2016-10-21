package ru.ulmc.gwt.sandbox.client.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BaseBeanMap<T,R> {
    protected Map<T,R> map = new HashMap<>();

    public BaseBeanMap(Map<T, R> map) {
        this.map = map;
    }

    public BaseBeanMap() {
    }

    public R get(T key) {
        return map.get(key);
    }

    public void put(T key, R value) {
        map.put(key, value);
    }
}
