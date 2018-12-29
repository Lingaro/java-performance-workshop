### [Slides](https://slides.com/rzymek/java-perf)

### JPA n+1

##### VisualVM
1. Start webapp
1. Start **VisualVM** and connect to webapp
1. Start **CPU Sampler**
1. Run `RestTest.v1` test
1. **Snapshot** in VisualVM
    1. Filter by `lingaro`
    1. `PersonController.list1()` -> `Find in call tree`
    1. `Person.toString()` takes 8s out of 10s
    1. `Person.toString()` calls hibernate load
    1. Check sources and find `@ManyToOne(fetch = LAZY)`

##### Mitigation
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

### Java Microbenchmark Harness (JMH)

    <dependency>
        <groupId>org.openjdk.jmh</groupId>
        <artifactId>jmh-generator-annprocess</artifactId>
        <version>1.21</version>
    </dependency> 