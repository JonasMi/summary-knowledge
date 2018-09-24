package summary.knowledge.springframework;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext parentContext = SpringApplication.run(App.class, args);
        ClassPathXmlApplicationContext childContext = new ClassPathXmlApplicationContext("MATE-INF/appName-conf.xml");
        childContext.setParent(parentContext);
        childContext.refresh();
        Map<String, ApplicationContext> contextMap = new HashMap<>();
        contextMap.put("appNameConf",childContext);
        contextMap.put("appNameConf",parentContext);
    }
}
