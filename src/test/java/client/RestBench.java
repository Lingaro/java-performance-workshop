package client;

import com.lingaro.web.person.PersonService;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestBench {
    static final int REPEAT = 3;

    final static RestTemplate rest = new RestTemplate();

    static {
        rest.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
    }

    @RepeatedTest(REPEAT)
    public void v1() {
        get("v1");
    }

    @RepeatedTest(REPEAT)
    public void v2() {
        get("v2");
    }

    @RepeatedTest(REPEAT)
    public void v3() {
        get("v3");
    }

    @RepeatedTest(REPEAT)
    public void v4() {
        get("v4");
    }

    private void get(String v1) {
        int size = rest.getForObject("/person/" + v1, List.class).size();
        assertEquals(PersonService.PERSON_COUNT, size);
    }
}
