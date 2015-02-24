package research.lambda;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by roybailey on 20/01/2014.
 */
public class LambdaStreamsTest {

    public static class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFirstName() {
            return name.substring(0, 1);
        }

        public String getLastName() {
            return name.substring(name.length() - 1, name.length());
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static List<Student> students = Arrays.asList(
            new Student("Anna", 18),
            new Student("Bob", 33),
            new Student("Fran", 18),
            new Student("George", 24),
            new Student("Colin", 24),
            new Student("David", 27),
            new Student("Eric", 18)
    );

    @Test
    public void testMatching() {
        Boolean hasMatureStudent, allOverAged;

        hasMatureStudent = students.stream().anyMatch(student -> student.getAge() > 40);
        assertFalse(hasMatureStudent);

        hasMatureStudent = students.stream().anyMatch(student -> student.getAge() > 30);
        assertTrue(hasMatureStudent);

        allOverAged = students.stream().allMatch(student -> student.getAge() > 40);
        assertFalse(allOverAged);

        allOverAged = students.stream().allMatch(student -> student.getAge() > 25);
        assertFalse(allOverAged);

        allOverAged = students.stream().allMatch(student -> student.getAge() >= 18);
        assertTrue(allOverAged);
    }

    @Test
    public void testTerminalOperations() {
        Student found = students.stream().filter(student -> student.getAge() > 30).findAny().get();
        assertNotNull(found);

        int reduce1 = IntStream.range(1, 10).reduce(0, (x, y) -> x + y);
        assertEquals(45, reduce1);

        OptionalInt reduce2 = IntStream.range(1, 10).reduce((x, y) -> x + y);
        assertEquals(45, reduce2.getAsInt());

        Student added = students.stream().reduce(new Student("", 0), (incoming, todo) -> new Student("", incoming.getAge() + todo.getAge()));
        assertEquals(162, added.getAge());

        Integer minAge = students.stream().map(student -> student.getAge()).reduce(100, Integer::min);
        Integer maxAge = students.stream().map(student -> student.getAge()).reduce(0, Integer::max);
        assertEquals(Integer.valueOf(18), minAge);
        assertEquals(Integer.valueOf(33), maxAge);

        students.stream()
                .filter(student -> student.getAge() > 30) // filter
                .findAny()         //Any student matching to the filter
                .map(Student::getName)    // mapping to students name
                .ifPresent(System.out::println);  // print if name of the student is not Null

        // fibonacci number generator
        AtomicReference<Integer> prev1 = new AtomicReference<>(1);
        AtomicReference<Integer> prev2 = new AtomicReference<>(0);
        IntStream.range(1, 10).forEach(value -> {
            int next = prev1.get() + prev2.get();
            prev2.set(prev1.get());
            prev1.set(next);
            System.out.print(next + " ");
        });
        System.out.println();
    }

    @Test
    public void testIntermediateOperations() {
        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);

        // flatMap
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<List<Integer>> mapped =
                numbers.stream()
                        .map(number -> Arrays.asList(number - 1, number, number + 1))
                        .collect(Collectors.toList());
        System.out.println(mapped); //:> [[0, 1, 2], [1, 2, 3], [2, 3, 4], [3, 4, 5]]

        List<Integer> flattened =
                numbers.stream()
                        .flatMap(number -> Arrays.asList(number - 1, number, number + 1).stream())
                        .collect(Collectors.toList());
        System.out.println(flattened);  //:> [0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5]

        // filtering
        System.out.print(students.stream()
                .filter(student -> student.getAge() >= 20)
                .collect(Collectors.toList()));
        System.out.println();

        //Get distinct list of names of the students
        System.out.print(students.stream()
                .map(Student::getAge)
                .distinct()
                .collect(Collectors.toList()));
        System.out.println();

        //All studentnames
        students.stream()
                .filter(s -> s.getAge() > 20)
                .map(Student::getName)
                .collect(Collectors.toList())
                .forEach(name -> System.out.print(name + ", "));
        System.out.println();

        //List of first 3 students who have age > 20
        System.out.print(students.stream()
                .filter(s -> s.getAge() > 20)
                .map(Student::getName)
                .limit(2)
                .collect(Collectors.toList()));
        System.out.println();

        //List of all the students who have age > 20 except the first 3
        System.out.print(students.stream()
                .filter(s -> s.getAge() > 20)
                .map(Student::getName)
                .skip(2)
                .collect(Collectors.toList()));
        System.out.println();

        //Sorting
        System.out.print(students.stream()
                .map(Student::getName)
                .sorted()
                .collect(Collectors.toList()));
        System.out.println();

        // same as above but shows custom comparator
        System.out.print(students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .map(Student::getName)
                .collect(Collectors.toList()));
        System.out.println();

        //Sorting names if the Students in descending order
        System.out.print(students.stream()
                .map(Student::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
        System.out.println();

        //Sorting names if the Students in descending order
        System.out.print(students.stream()
                .map(Student::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
        System.out.println();

        //Sorting students by First Name and Last Name both
        System.out.print(students.stream()
                .sorted(Comparator.comparing(Student::getFirstName).
                        thenComparing(Student::getLastName))
                .map(Student::getName)
                .collect(Collectors.toList()));
        System.out.println();

        //Sorting students by First Name Descending and Last Name Ascending
        System.out.print(students.stream()
                .sorted(Comparator.comparing(Student::getFirstName)
                        .reversed()
                        .thenComparing(Student::getLastName))
                .map(Student::getName)
                .collect(Collectors.toList()));
        System.out.println();
    }
}
