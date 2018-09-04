package summary.knowledge.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import summary.knowledge.common.exception.JsonException;

import java.io.IOException;

/**
 * @ Author ：JonasMi
 * @ Date   ：Created in 2018/9/4 21:25
 * @ version: 1.0.0
 */
public class Jsons {

    public static final Jsons EXCLUDE_EMPTY = new Jsons(JsonInclude.Include.NON_EMPTY);


    public static final Jsons EXCLUDE_DEFAULT = new Jsons(JsonInclude.Include.NON_DEFAULT);

    public static final Jsons DEFAULT = new Jsons();

    private ObjectMapper mapper;

    private Jsons() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private Jsons(JsonInclude.Include include) {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(include);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public String toJson(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }


    public <T> T fromJson(String json, Class<T> target) {

        try {
            return mapper.readValue(json, target);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromJson(String jsonString, JavaType javaType) {
        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
