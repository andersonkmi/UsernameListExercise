package org.techstuff.auth.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil<T> {
    public String toJson(T item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return json;
    }

    public T fromJson(String json, Class<T> cls) {
        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }
}
