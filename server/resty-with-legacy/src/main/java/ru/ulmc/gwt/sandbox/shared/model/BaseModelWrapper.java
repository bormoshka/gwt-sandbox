package ru.ulmc.gwt.sandbox.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.RpcMap;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class BaseModelWrapper extends BaseModel {

    /**
     * Грязный хак. Но работает.
     */
    public RpcMap map = new RpcMap();

    public BaseModelWrapper() {
        super.map = map;
    }

    @Override
    public void setProperties(Map<String, Object> properties) {
        super.setProperties(properties);
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }

    @Override
    public <X> X set(String name, X value) {
        return super.set(name, value);
    }

    @Override
    public <X> X remove(String name) {
        return super.remove(name);
    }


}
