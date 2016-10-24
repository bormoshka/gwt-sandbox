package ru.ulmc.gwt.sandbox.shared.model;

import com.extjs.gxt.ui.client.data.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTreeModelWrapper extends BaseTreeModel {

    public RpcMap map = new RpcMap();
    public BaseTreeModelWrapper parent = null;
    public List<BeanModel> children = new ArrayList<>();
    public Map<String, Object> transferMap = new HashMap<>();

    public BaseTreeModelWrapper() {
        super.map = map;
        super.parent = parent;
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
