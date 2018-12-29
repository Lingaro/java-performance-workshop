package bench;

import common.BenchmarkTest;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 1)
public class Ex4UrlBench extends BenchmarkTest {

    @Benchmark
    public boolean url() throws MalformedURLException {
        return new URL("https://wp.pl").equals(new URL("https://" + System.currentTimeMillis() + ".wp.pl"));
    }

    @Benchmark
    public boolean uri() throws URISyntaxException {
        return new URI("https://wp.pl").equals(new URI("https://" + System.currentTimeMillis() + ".wp.pl"));
    }

    @Test
    public void test() throws Exception {
        assertEquals(
                new URL("https://wp.pl"),
                new URL("https://www.wp.pl")
        );
        assertNotEquals(
                new URI("https://wp.pl"),
                new URI("https://www.wp.pl")
        );
    }

}