package bench;

import common.BenchmarkTest;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;

import java.io.*;

@BenchmarkMode(Mode.SingleShotTime)
@Measurement(iterations = 5)
public class Ex5OutputBench extends BenchmarkTest {

    public static final int COUNT = 1_000_000;

    @Benchmark
    public void direct() throws IOException {
        File file = File.createTempFile("direct", ".bin");
        write(new FileOutputStream(file));
        file.delete();
    }

    @Benchmark
    public void buffered() throws IOException {
        File file = File.createTempFile("buf", ".bin");
        write(new BufferedOutputStream(new FileOutputStream(file)));
        file.delete();
    }

    private void write(OutputStream stream) throws IOException {
        try (OutputStream out = stream) {
            for (int i = 0; i < COUNT; i++) {
                out.write(i);
            }
        }
    }

}