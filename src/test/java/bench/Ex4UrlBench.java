package bench;

import common.BenchmarkTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.net.MalformedURLException;
import java.net.URL;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 5, time = 1)
public class Ex4UrlBench extends BenchmarkTest {

    @Benchmark
    public boolean url() throws MalformedURLException {
        return new URL("https://wp.pl").equals(new URL("https://" + System.currentTimeMillis() + ".wp.pl"));
    }

    // TODO: 90_000x faster

}