package mj.debris.collecting.summary_knowledge_redis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Author ：JonasMi
 * @ Date   ：Created in 2018/9/21 22:17
 * @ version: 1.0.0
 */

public class ConditionFilter {
    private Map<String,String> conditionMap = new HashMap<>();

    public void put(String key,String value){
        if(value != null && !value.equals("")){
            conditionMap.put(key,value);
        }
    }

    public Boolean conditionIsMatch(Object object){
        Boolean isMatch = false;
        if(conditionMap.size() == 0){
            return true;
        }
        for(String key : conditionMap.keySet()){
            try {
                Field field = object.getClass().getDeclaredField(key);
                field.setAccessible(true);
                if(fuzzyMatched(conditionMap.get(key),field.get(object).toString())){
                   isMatch = true;
                }else{
                    isMatch = false;
                    break;
                }
            } catch (Exception e ) {
                isMatch = false;
                break;
            }
        }
        return isMatch;
    }

    private Boolean fuzzyMatched(String value,String target){
        Pattern pattern = Pattern.compile(value);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

}
