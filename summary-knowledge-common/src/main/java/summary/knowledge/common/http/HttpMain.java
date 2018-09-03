package summary.knowledge.common.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import summary.knowledge.common.model.Person;

public class HttpMain {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://127.0.0.1:8080/test/get/?name= {name}";
        /*
         * RestTemplate restTemplate = new RestTemplate();
         * restTemplate.getForObject("ur", Object.class);
         *
         *
         *
         *
         *
         */
        /* restTemplate.postForObject(url, request, responseType, uriVariables) */
        RestTemplate restTemplate = new RestTemplate();
        RestTemplate restTemplate1 = new RestTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jonasMi");
        map.put("sex", "男");
        Person p = new Person();
        p.setName("jonasMi");
        p.setSex("男");
        Person person = restTemplate.getForObject(url, Person.class, "name1", "sex1");

        /*restTemplate.postForObject(url, request, responseType)*/
        System.out.println(objectMapper.writeValueAsString(person));
		/*URL url = new URL("http://127.0.0.1:8080/test/get/");
		URLConnection connection = url.openConnection();*/
    }
}
