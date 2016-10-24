package ru.ulmc.gwt.sandbox.shared.model;

public class SimpleBaseModelBean extends BaseModelWrapper {

    public static final String FIELD_A = "FIELD_A_KEY";
    public static final String FIELD_B = "FIELD_B_KEY";

    private String testField = "Простое тестовое поле";


    public SimpleBaseModelBean() {
    }

    public SimpleBaseModelBean(String aField, String bField) {
        set(FIELD_A, aField);
        set(FIELD_B, bField);
    }

    public String getA() {
        return get(FIELD_A);
    }

    public  String getB() {
        return get(FIELD_B);
    }

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }
}
