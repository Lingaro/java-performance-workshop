package workshop;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Warmup(time = 3, iterations = 1)  // 3
@Measurement(time = 1)             // 3
public class SimpleBenchmark {

    @Benchmark                  //  2
    public double random() {    // 1
        return Math.random();   // 1
    }                           // 1

    @Benchmark
    public double threadLocalRandom() {                             // 4
        return ThreadLocalRandom.current().nextDouble();            // 4
    }                                                               // 4


    static Random random = new Random();                            // 5
    @Benchmark                                                      // 5
    public double shared() { return random.nextDouble(); }          // 5
    @Benchmark                                                      // 5
    public double newRandom() { return new Random().nextDouble(); } // 5

    public static void main(String[] args) throws RunnerException { // 2
        Options options = new OptionsBuilder()                      // 2
                .include(SimpleBenchmark.class.getName())           // 2
                .forks(3)                                           //  3
                .build();                                           // 2
        new Runner(options).run();                                  // 2
    }
}
