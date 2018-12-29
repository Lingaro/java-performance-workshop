package bench;

import common.BenchmarkTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

@BenchmarkMode(Mode.SingleShotTime)
@Measurement(iterations = 30)
public class Ex2SumBench extends BenchmarkTest {

    public static final int COUNT = Integer.MAX_VALUE / 100;

    @Benchmark
    public Long sumLong() {
        Long sum = 0L;
        for (long i = 0; i < COUNT; i++) {
            sum += i;
        }
        return sum;
    }

    // TODO: 10x faster (single char change)

}