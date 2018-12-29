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
// For more see: https://daniel.mitterdorfer.name/articles/2014/benchmarking-digging-deeper/
public class Ex6DateFormatBench extends BenchmarkTest {

    public static final String FORMAT_STRING = "yyyy-MM-dd'T'HH:m:ss.SSS";

    @Benchmark
    public String date() {
        return new SimpleDateFormat(FORMAT_STRING).format(new Date());
    }

    static final SimpleDateFormat simpleFormat = new SimpleDateFormat(FORMAT_STRING);
    @Benchmark
    public String sharedDate() {
        synchronized (simpleFormat) {
            return simpleFormat.format(new Date());
        }
    }

    @Benchmark
    public String local() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_STRING));
    }

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_STRING);
    @Benchmark
    public String shared() {
        return LocalDateTime.now().format(formatter);
    }

    static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(FORMAT_STRING));
    @Benchmark
    public String threadLocalDate() {
        return threadLocal.get().format(new Date());
    }
}