package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

@BenchmarkMode(Mode.SingleShotTime)
@Measurement(iterations = 10)
public class StringBench extends BenchmarkTest {

    public static final int COUNT = 25_000;

    @Benchmark
    public Object simple() {
        String result = "";
        for (int i = 0; i < COUNT; i++) {
            result += String.valueOf(i);
        }
        return result;
    }

    @Benchmark
    public Object builder() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            result.append(i);
        }
        return result.toString();
    }

}