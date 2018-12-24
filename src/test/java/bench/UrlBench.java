package bench;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 1)
public class UrlBench extends BenchmarkTest {

    @Benchmark
    public boolean url() throws MalformedURLException {
        return new URL("https://wp.pl").equals(new URL("https://www.wp.pl"));
    }

    @Benchmark
    public boolean uri() throws URISyntaxException {
        return new URI("https://wp.pl").equals(new URI("https://www.wp.pl"));
    }

    @Test
    public void urlTest() throws MalformedURLException {
        assertTrue(url());
    }

    @Test
    public void uriTest() throws URISyntaxException {
        assertFalse(uri());
    }

}