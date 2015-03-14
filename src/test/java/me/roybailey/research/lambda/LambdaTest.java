package me.roybailey.research.lambda;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by roybailey on 20/01/2014.
 */
public class LambdaTest {


    @Test
    public void lambdaTest() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");
        List<String> filteredNames = names.stream().filter(e -> e.length() >= 4).collect(Collectors.toList());
        System.out.println(filteredNames);
    }

    public static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void lambdaStreamTest() {
        List<Student> students = Arrays.asList(
                new Student("Alice"),
                new Student("Bob"),
                new Student("Charlie"),
                new Student("Dave"));
        List<String> names = students.stream().map(Student::getName).filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(names);
    }

    @Test
    public void lambdaNumericalStreams() {
        // ->12345678910
        IntStream.rangeClosed(1, 10).forEach(num -> System.out.print(num));
        System.out.println();
        // ->123456789
        IntStream.range(1, 10).forEach(num -> System.out.print(num));
        System.out.println();
        // ->246810
        IntStream.rangeClosed(1, 10).filter(it -> it % 2 == 0).forEach(num -> System.out.print(num));
        System.out.println();
    }

    @Test
    public void lambdaBuildingStreams() throws IOException {
        //Creating Stream of hardcoded Strings and printing each String
        Stream.of("This", "is", "Java8", "Stream").forEach(System.out::println);
        //Creating stream of arrays
        String[] stringArray = new String[]{"Streams", "can", "be", "created", "from", "arrays"};
        Arrays.stream(stringArray).forEach(System.out::println);

        // Creating BufferedReader for a file
        System.out.println(new File(".").getAbsolutePath());
        Files.walk(new File(".").toPath())
                .filter(p -> p.getFileName().toString().equals("pom.xml"))
                .forEach(System.out::println);
        //BufferedReader reader = Files.newBufferedReader(pomPath, StandardCharsets.UTF_8);
        //BufferedReader's lines methods returns a stream of all lines
        //reader.lines().filter(it -> it.contains("artifact")).forEach(System.out::println);
    }

    public static class Person {
        final String name;
        final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    final List<String> names = Arrays.asList("Anna", "Bobby", "Colin", "David", "Emma", "Francis");

    public static class PersonIterator implements Iterator<Person> {

        int index = 0;
        List<String> names;

        public PersonIterator(List<String> names) {
            this.names = names;
        }

        @Override
        public boolean hasNext() {
            return index < names.size();
        }

        @Override
        public Person next() {
            return new Person(names.get(index++), (int) Math.abs(Math.random() * 100));
        }
    }

    @Test
    public void lambdaCustomStreamBuilder() throws IOException {

        Iterator<Person> iterator = new PersonIterator(names);
        Stream.Builder<Person> builder = Stream.builder();
        while (iterator.hasNext()) {
            builder.add(iterator.next());
        }
        Stream<Person> builtStream = builder.build();
        builtStream.forEach(System.out::println);
        System.out.println("- Stream ended");
    }

    @Test
    public void lambdaCustomStreamSpliterator() throws IOException {
        Iterator<Person> iterator = new PersonIterator(names);
        Spliterator<Person> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        Stream<Person> iteratorStream = StreamSupport.stream(spliterator, false);
        iteratorStream.forEach(System.out::println);
        System.out.println("- Stream ended");
    }

    @Test
    public void lambdaCustomStreamGenerator() throws IOException {
        Iterator<Person> iterator = new PersonIterator(names);
        try {
            Stream<Person> generatedStream = Stream.<Person>generate(iterator::next);
            generatedStream.forEach(System.out::println);
        } catch (Exception err) {
            System.out.println("Infinite stream ended with error : " + err);
        }
    }
}
