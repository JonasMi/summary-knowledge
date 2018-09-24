package mj.debris.collecting.summary_knowledge_redis;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ Author ：JonasMi
 * @ Date   ：Created in 2018/9/21 23:07
 * @ version: 1.0.0
 */
public class RepeatSubmitController {
    private Map<String,Integer> preventRepeatSubmitMap = new HashMap<>();

    @RequestMapping(value="/api/release/{releaseId}/")
    public void releaseApp(@PathVariable("releaseId") String releaseId){
        try{
            doValidator(releaseId);
            synchronized (this){
                if(preventRepeatSubmitMap.get(releaseId) != null){
                    //重复提交啦，请稍后再试
                    return;
                }
                preventRepeatSubmitMap.put(releaseId,1);
            }
            //doSomeThing(releaseId)
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally {
            preventRepeatSubmitMap.remove(releaseId);
        }
    }

    private void doValidator(String releaseId){

    }





}
