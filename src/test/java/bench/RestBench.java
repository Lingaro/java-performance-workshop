package bench;

import com.lingaro.web.person.PersonService;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

@BenchmarkMode(Mode.SingleShotTime)
@Measurement(iterations = 3)
public class RestBench extends BenchmarkTest {

    final static RestTemplate rest = new RestTemplate();

    static {
        rest.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"));
    }

    @Test
    @Benchmark
    public void v1() {
        get("v1");
    }

    @Test
    @Benchmark
    public void v2() {
        get("v2");
    }

    @Test
    @Benchmark
    public void v3() {
        get("v3");
    }

    @Test
    @Benchmark
    public void v4() {
        get("v4");
    }

    private void get(String v1) {
        int size = rest.getForObject("/person/" + v1, List.class).size();
        assertEquals(PersonService.PERSON_COUNT, size);
    }
}
