package ru.ulmc.gwt.sandbox.client.model;

/**
 *
 */
public class SimpleBean {
    private String fieldString;
    private Long fieldLong;

    public SimpleBean() {
    }

    public SimpleBean(String fieldString, Long fieldLong) {
        this.fieldString = fieldString;
        this.fieldLong = fieldLong;
    }

    public String getFieldString() {
        return fieldString;
    }

    public void setFieldString(String fieldString) {
        this.fieldString = fieldString;
    }

    public Long getFieldLong() {
        return fieldLong;
    }

    public void setFieldLong(Long fieldLong) {
        this.fieldLong = fieldLong;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "fieldString='" + fieldString + '\'' +
                ", fieldLong=" + fieldLong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleBean that = (SimpleBean) o;

        if (fieldString != null ? !fieldString.equals(that.fieldString) : that.fieldString != null)
            return false;
        return fieldLong != null ? fieldLong.equals(that.fieldLong) : that.fieldLong == null;

    }

    @Override
    public int hashCode() {
        int result = fieldString != null ? fieldString.hashCode() : 0;
        result = 31 * result + (fieldLong != null ? fieldLong.hashCode() : 0);
        return result;
    }
}
