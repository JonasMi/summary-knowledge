package mj.debris.collecting.summary_knowledge_redis;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void filterConditionTest() {
        ConditionFilter filterCondition = new ConditionFilter();
        Person person = new Person();
        person.setName("name");
        person.setAddress("address");
        filterCondition.put("name","name");
        filterCondition.put("address","ddre");
        System.out.println(filterCondition.conditionIsMatch(person));
    }
}
