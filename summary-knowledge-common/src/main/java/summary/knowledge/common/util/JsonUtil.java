package summary.knowledge.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import summary.knowledge.common.exception.JsonException;

public class JsonUtil {

	public static final ObjectMapper mapper = new ObjectMapper();
	
	public static String toJson(Object target) {
		try {
			return mapper.writeValueAsString(target);
		} catch (JsonProcessingException e) {
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
	
	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	public static <T> T convertValue(Object fromValue,Class<T>  toValueType) {
		return mapper.convertValue(fromValue, toValueType);
	}
	
	public static <T> T convertValue(Object fromValue,JavaType javaType) {
		return mapper.convertValue(fromValue, javaType);
	}
}
