package ru.ulmc.gwt.sandbox.shared.model;

/**
 *
 */
public class NanoBean extends BaseModelWrapper {

    public static final String VALUE = "v";

    public NanoBean() {
    }

    public NanoBean(String value) {
        set(VALUE, value);
    }

    public String getValue() {
        return get(VALUE);
    }

    public void setValue(String value) {
        set(VALUE, value);
    }

    @Override
    public String toString() {
        return "NanoBean{" + get(VALUE) + "}";
    }
}
