package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 1)
public class RandomBench extends BenchmarkTest {

    static Random random = new Random();

    @Benchmark
    public double random() {
        return random.nextDouble();
    }

    @Benchmark
    public double newRandom() {
        return new Random().nextDouble();
    }

    @Benchmark
    public double math() {
        return Math.random();
    }

    @Benchmark
    public double threadLocalRandom() {
        return ThreadLocalRandom.current().nextDouble();
    }

}
