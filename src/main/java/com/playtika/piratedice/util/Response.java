package com.playtika.piratedice.util;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class Response {
    private boolean status = true;
    private final HashMap<String, String> data = new HashMap<>(4);

    public Response() {
    }

    public Response(boolean status) {
        this.status = status;
    }

    public Response add(String key, String value) {
        data.put(key, value);

        return this;
    }

    public Response add(String key, int value) {
        data.put(key, Integer.toString(value));

        return this;
    }

    public Response add(String key, boolean value) {
        data.put(key, Boolean.toString(value));

        return this;
    }

    public String getValue(String key) {
        return data.get(key);
    }
}
