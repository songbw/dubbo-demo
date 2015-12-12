package com.flzc.quartz.system;

/**
 * 属性信息的封装。用于向spring注入对象时，属性配置信息
 * Created by iverson on 2015/9/25.
 */
public class PropertyWrapper {

    private String fieldName;//属性名

    private Object fieldValue;//属性值，如果是引用类型，则表示引用的在spring对象id

    private boolean refType = false;//是否是引用类型

    public PropertyWrapper(){}
    public PropertyWrapper(String fieldName ,Object fieldValue){
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public PropertyWrapper(String fieldName ,Object fieldValue,boolean refType){
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.refType = refType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public boolean isRefType() {
        return refType;
    }

    public void setRefType(boolean refType) {
        this.refType = refType;
    }
}
