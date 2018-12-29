package bench;

import common.BenchmarkTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.util.Random;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 1)
public class Ex3RegexBench extends BenchmarkTest {

    public static final int COUNT = 500_000;
    public static final String REGEX = ".*aaaaa.*";

    @Benchmark
    public int compiled() {
        Random random = new Random(0);
        Pattern regex = Pattern.compile(REGEX);
        for (int i = 0; i < COUNT; i++) {
            String hex = Integer.toHexString(random.nextInt());
            if (regex.matcher(hex).matches()) {
                return i;
            }
        }
        return -1;
    }

    @Benchmark
    public int matches() {
        Random random = new Random(0);
        for (int i = 0; i < COUNT; i++) {
            String hex = Integer.toHexString(random.nextInt());
            if (hex.matches(REGEX)) {
                return i;
            }
        }
        return -1;
    }


}