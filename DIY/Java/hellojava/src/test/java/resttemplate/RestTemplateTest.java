package resttemplate;

import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
    @Test
    public void test(){
        RestTemplate restTemplate = new RestTemplate();
        String ret = restTemplate.getForObject("http://localhost:8080/gretting",String.class);
        System.out.println(ret);
    }
}
