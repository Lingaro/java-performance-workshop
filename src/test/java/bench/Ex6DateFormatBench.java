package bench;

import common.BenchmarkTest;
import org.openjdk.jmh.annotations.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 3)
@Threads(5)
public class Ex6DateFormatBench extends BenchmarkTest {

    public static final String FORMAT_STRING = "yyyy-MM-dd'T'HH:m:ss.SSS";

    @Benchmark
    public String date() {
        return new SimpleDateFormat(FORMAT_STRING).format(new Date());
    }

    // TODO: 2x faster
}