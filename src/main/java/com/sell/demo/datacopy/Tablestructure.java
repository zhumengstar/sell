package com.sell.demo.datacopy;

public class Tablestructure {
    private String  Field;
    private  String type;
    private String Null;
    private String key;
    private String Default;
    private String Extra;

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNull() {
        return Null;
    }

    public void setNull(String aNull) {
        Null = aNull;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefault() {
        return Default;
    }

    public void setDefault(String aDefault) {
        Default = aDefault;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }
}
