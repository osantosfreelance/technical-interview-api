package com.ing.be.tia.statemachine.state;

import java.util.Map;

public class Listing {
    private Map<String, Object> fields;

    public Listing(Map<String, Object> fields) {
        this.fields = fields;
    }

    public Object getField(String key) {
        return fields.get(key);
    }

    public String getStringField(String key) {
        Object value = fields.get(key);
        return value != null ? value.toString() : null;
    }

    public Map<String, Object> getFields() {
        return fields;
    }
}

