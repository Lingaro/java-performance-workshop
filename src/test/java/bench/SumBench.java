package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

@BenchmarkMode(Mode.SingleShotTime)
@Measurement(iterations = 30)
public class SumBench extends BenchmarkTest {

    public static final int COUNT = Integer.MAX_VALUE / 100;

    @Benchmark
    public Long sumLong() {
        Long sum = 0L;
        for (long i = 0; i < COUNT; i++) {
            sum += i;
        }
        return sum;
    }

    @Benchmark
    public Long sumlong() {
        long sum = 0L;
        for (long i = 0; i < COUNT; i++) {
            sum += i;
        }
        return sum;
    }

}