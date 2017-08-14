package net.wepla.library_common;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by seminhong on 07/07/2017.
 */

public class ClassUtil {
    public static HashMap<String, Object> classToMap(Object target) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Integer.class, new JsonSerializer<Integer>() {
                    @Override
                    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
                        if(src == src.longValue())
                            return new JsonPrimitive(src.toString());
                        return new JsonPrimitive(src);                    }
                }).create();

        String str = gson.toJson(target);
        return (HashMap<String, Object>) gson.fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getRawType());
    }
}
