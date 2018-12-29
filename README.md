### [Slides](https://slides.com/rzymek/java-perf)
        
## VisualVM

### Find the hot spot

1. Start webapp
1. Start **VisualVM** and connect to webapp
1. Start **CPU Sampler**
1. Run `RestTest.v1` test
1. **Snapshot** in VisualVM
    1. Filter by `lingaro`
    1. `PersonController.list1()` -> `Find in call tree`
    1. `Person.toString()` takes 8s out of 10s
    1. `Person.toString()` calls hibernate load (jpa n+1 problem)
    1. Check sources and find `@ManyToOne(fetch = LAZY)`

#### Mitigation
* join fetch
* `@EntityGraph`

```java
@Entity
@NamedEntityGraph(name = Person.FETCH_ADDRESS, attributeNodes = @NamedAttributeNode("address"))
public class Person { /* ... */ } 
```

```java
@EntityGraph(value = Person.FETCH_ADDRESS, type = EntityGraph.EntityGraphType.FETCH)
Stream<Person> findAllBy();

@Query("select person from Person person join fetch person.address")
Stream<Person> selectWithAddress();

@Query("select concat(person.name, ' ', person.surname, ' @ ', address.name) " +
 " from Person person left join person.address address")
Set<String> queryNameAtAddress();
```

## Common Java Performance pitfals
But first, how to measure & compare.

### Java Microbenchmark Harness (JMH)

    <dependency>
      <groupId>org.openjdk.jmh</groupId>
      <artifactId>jmh-generator-annprocess</artifactId>
      <version>1.21</version>
    </dependency> 
  
#### Complete simple benchmark. 
1. code to test (maybe just run from main and measure)
2. minimal benchmark on defaults. 
3. defaults take too long here. customize
4. compare 
5. compare more

```java
@Warmup(time = 3, iterations = 1)  // 3
@Measurement(time = 1)             // 3
public class SimpleBenchmark {

  @Benchmark                  //  2
  public double random() {    // 1
    return Math.random();     // 1
  }                           // 1

  @Benchmark
  public double threadLocalRandom() {                             // 4
    return ThreadLocalRandom.current().nextDouble();              // 4
  }                                                               // 4


  static Random random = new Random();                            // 5
  @Benchmark                                                      // 5
  public double shared() { return random.nextDouble(); }          // 5
  @Benchmark                                                      // 5
  public double newRandom() { return new Random().nextDouble(); } // 5

  public static void main(String[] args) throws RunnerException { // 2
    Options options = new OptionsBuilder()                        // 2
            .include(SimpleBenchmark.class.getName())             // 2
            .forks(3)                                             //  3
            .build();                                             // 2
    new Runner(options).run();                                    // 2
  }
}
```

### Exercises 
Ex1StringBench - Note on modes: `@BenchmarkMode(Mode.SingleShotTime)`

    @Benchmark
    public Object builder() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            result.append(i);
        }
        return result.toString();
    }
   
Ex2SumBench

    long sum = 0L;
    
Ex3RegexBench

    @Benchmark
    public int compiled() {
        Random random = new Random(0);
        Pattern regex = Pattern.compile(REGEX);   // *
        for (int i = 0; i < COUNT; i++) {
            String hex = Integer.toHexString(random.nextInt());
            if (regex.matcher(hex).matches()) {   // *
                return i;
            }
        }
        return -1;
    }
    
Ex4UrlBench

    @Benchmark   // * -  URI
    public boolean uri() throws URISyntaxException {
        return new URI("https://wp.pl").equals(new URI("https://www.wp.pl"));
    }
    
    @Test
    public void uriTest() throws URISyntaxException {
        assertFalse(uri());    // * - false
    }
    
Ex5OutputBench 

    @Benchmark
    public void buffered() throws IOException {
        File file = File.createTempFile("buf", ".bin");
        write(new BufferedOutputStream(new FileOutputStream(file)));
        file.delete();
    }

Ex6DateFormatBench

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

