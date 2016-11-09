package ru.ulmc.gwt.sandbox.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplexBaseModelBean extends BaseModelWrapper {

    public static final String STRING = "string";
    public static final String DOUBLE = "double";
    public static final String LONG = "long";
    public static final String INTEGER = "int";
    public static final String DATE = "date";

    public static final String BEAN = "bean";
    public static final String LIST = "list";
    public static final String ENUM_A = "enumA";
    public static final String ENUM_B = "enumB";


    public ComplexBaseModelBean() {
    }

    public ComplexBaseModelBean(SimpleBaseModelBean baseBean) {
        List<NanoBean> list = new ArrayList<>();
        list.add(new NanoBean("one"));
        list.add(new NanoBean("two"));
        list.add(new NanoBean("three"));
        list.add(new NanoBean("four"));

        set(STRING, "RandomString");
        set(DOUBLE, 22.22D);
        set(LONG, 1337L);
        set(INTEGER, 42);
        set(DATE, new Date());
        set(BEAN, baseBean);
        set(LIST, list);

        set(ENUM_A, NanoEnum.VALUE_A);
        set(ENUM_B, NanoEnum.VALUE_B);
    }

    @Override
    public String toString() {
        return "ComplexBaseModelBean{"
                + get(STRING) + ", "
                + get(DOUBLE) + ", "
                + get(LONG) + ", "
                + get(INTEGER) + ", "
                + get(DATE) + ", "
                + get(BEAN) + ", "
                + get(LIST) + ", "
                + get(ENUM_A) + ", "
                + get(ENUM_B)  + ", "
                + "}";
    }
}
