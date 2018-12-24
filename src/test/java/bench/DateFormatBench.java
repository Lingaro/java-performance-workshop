package bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 1)
public class DateFormatBench extends BenchmarkTest {

    public static final String FORMAT_STRING = "yyyy-MM-dd'T'HH:m:ss.SSS";


    @Benchmark
    public String local() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_STRING));
    }

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(FORMAT_STRING);

    @Benchmark
    public String shared() {
        return LocalDateTime.now().format(FORMAT);
    }

}