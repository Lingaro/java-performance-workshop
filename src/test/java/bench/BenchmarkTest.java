package bench;

import org.junit.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;
import java.util.regex.Pattern;

abstract class BenchmarkTest {

    @Test
    public void run() throws RunnerException {
        File dir = new File("target/csv/");
        dir.mkdirs();
        Options options = new OptionsBuilder()
                .include(Pattern.quote(getClass().getName()))
                .forks(2)
                .warmupIterations(1)
                .warmupTime(TimeValue.seconds(1))
                .resultFormat(ResultFormatType.CSV)
                .result(dir + "/" + getClass().getSimpleName() + ".csv")
                .build();
        new Runner(options).run();
    }
}
