package ru.ulmc.gwt.sandbox.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.RpcMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class BaseModelWrapper extends BaseModel {

    /**
     * Грязный хак. Но работает.
     */
    public RpcMap map = new RpcMap();
    public Map<String, Object> transferMap = new HashMap<>();

    public BaseModelWrapper() {
        super.map = map;
    }

    /*
    @JsonProperty(required = true)
    public void setMap(RpcMap map) {
        super.map = this.map = map;
    }

    @JsonProperty(required = true)
    public RpcMap getMap() {
        return this.map;
    }
    */

    @Override
    @JsonProperty
    public void setProperties(Map<String, Object> properties) {
        super.setProperties(properties);
    }

    @Override
    @JsonProperty
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }

    public Map<String, Object> getTransferMap() {
        return transferMap;
    }

    /**
     * грязный хак #2
     * @param transferMap
     */
    public void setTransferMap(Map<String, Object> transferMap) {
        this.transferMap = transferMap;
        map.putAll(transferMap);
    }

    @Override
    public <X> X set(String name, X value) {
        transferMap.put(name, value);
        return super.set(name, value);
    }

    @Override
    public <X> X remove(String name) {
        transferMap.remove(name);
        return super.remove(name);
    }

    /**
     * Попробуем отучит гонять лишние данные.
     * Возможно поломает работу стандартных виджетов.
     * @return ничего =Р
     */
    /*@Override
    public Map<String, Object> getProperties() {
        return new HashMap<String, Object>;
    }*/

}
