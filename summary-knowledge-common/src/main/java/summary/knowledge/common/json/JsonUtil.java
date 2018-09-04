package summary.knowledge.common.json;

import com.fasterxml.jackson.databind.JavaType;

import java.util.ArrayList;
import java.util.Map;

/**
 * @ Author ：JonasMi
 * @ Date   ：Created in 2018/9/4 21:25
 * @ version: 1.0.0
 */
public interface JsonUtil {

     Jsons INSTANCE = Jsons.DEFAULT;

     JavaType LIST_OBJECT_TYPE = INSTANCE.createCollectionType(ArrayList.class, Object.class);

     JavaType MAP_STR_STR_TYPE = INSTANCE.createCollectionType(Map.class, String.class, String.class);
}
